/*
 * Copyright 2025 ByteChef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bytechef.component.xml.file.action;

import static com.bytechef.component.definition.ComponentDsl.action;
import static com.bytechef.component.definition.ComponentDsl.array;
import static com.bytechef.component.definition.ComponentDsl.fileEntry;
import static com.bytechef.component.definition.ComponentDsl.object;
import static com.bytechef.component.definition.ComponentDsl.option;
import static com.bytechef.component.definition.ComponentDsl.outputSchema;
import static com.bytechef.component.definition.ComponentDsl.string;
import static com.bytechef.component.xml.file.constant.XmlFileConstants.FILENAME;
import static com.bytechef.component.xml.file.constant.XmlFileConstants.SOURCE;
import static com.bytechef.component.xml.file.constant.XmlFileConstants.TYPE;

import com.bytechef.component.definition.ActionContext;
import com.bytechef.component.definition.ComponentDsl.ModifiableActionDefinition;
import com.bytechef.component.definition.FileEntry;
import com.bytechef.component.definition.Parameters;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author Ivica Cardic
 */
public class XmlFileWriteAction {

    private enum ValueType {

        OBJECT, ARRAY;
    }

    public static final ModifiableActionDefinition ACTION_DEFINITION = action("write")
        .title("Write to File")
        .description("Writes the data to a XML file.")
        .properties(
            string(TYPE)
                .label("Type")
                .description("The value type.")
                .options(
                    option("Object", ValueType.OBJECT.name()),
                    option("Array", ValueType.ARRAY.name())),
            object(SOURCE)
                .label("Source")
                .description("The object to write to the file.")
                .displayCondition("type == '%s'".formatted(ValueType.OBJECT.name()))
                .required(true),
            array(SOURCE)
                .label("Source")
                .description("The aray to write to the file.")
                .displayCondition("type == '%s'".formatted(ValueType.ARRAY.name()))
                .required(true),
            string(FILENAME)
                .label("Filename")
                .description("Filename to set for binary data. By default, \"file.xml\" will be used.")
                .required(true)
                .defaultValue("file.xml")
                .advancedOption(true))
        .output(outputSchema(fileEntry()))
        .perform(XmlFileWriteAction::perform);

    protected static FileEntry perform(
        Parameters inputParameters, Parameters connectionParameters, ActionContext context) throws IOException {

        Object source = inputParameters.getRequired(SOURCE);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (PrintWriter printWriter = new PrintWriter(byteArrayOutputStream, false, StandardCharsets.UTF_8)) {
            printWriter.println((String) context.xml(xml -> xml.write(source)));
        }

        try (InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray())) {
            return context.file(file -> file.storeContent(
                inputParameters.getString(FILENAME, "file.xml"), inputStream));
        }
    }
}
