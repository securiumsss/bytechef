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

package com.bytechef.component.dropbox.action;

import static com.bytechef.component.definition.ComponentDsl.action;
import static com.bytechef.component.definition.ComponentDsl.object;
import static com.bytechef.component.definition.ComponentDsl.outputSchema;
import static com.bytechef.component.definition.ComponentDsl.string;
import static com.bytechef.component.dropbox.constant.DropboxConstants.FILENAME;
import static com.bytechef.component.dropbox.constant.DropboxConstants.PATH;
import static com.bytechef.component.dropbox.util.DropboxUtils.getFullPath;

import com.bytechef.component.definition.ComponentDsl.ModifiableActionDefinition;
import com.bytechef.component.definition.Context;
import com.bytechef.component.definition.Context.ContextFunction;
import com.bytechef.component.definition.Context.Http;
import com.bytechef.component.definition.Parameters;
import com.bytechef.component.definition.TypeReference;
import java.util.Map;

/**
 * @author Mario Cvjetojevic
 * @author Monika Kušter
 */
public class DropboxGetFileLinkAction {

    public static final ModifiableActionDefinition ACTION_DEFINITION = action("getFileLink")
        .title("Get File Link")
        .description(
            "Get a temporary link to stream content of a file. This link will expire in four hours and afterwards " +
                "you will get 410 Gone. This URL should not be used to display content directly in the browser. " +
                "The Content-Type of the link is determined automatically by the file's mime type.")
        .properties(
            string(PATH)
                .label("Namepath to the File")
                .description("The path to the file you want a temporary link to.  Root is /.")
                .required(true),
            string(FILENAME)
                .label("Filename")
                .description(
                    "Name of the file with the extension. Needs to have a streamable extension (.mp4, .mov, .webm, ect)")
                .required(true))
        .output(
            outputSchema(
                object()
                    .properties(
                        object("metadata")
                            .properties(
                                string("name")
                                    .description(
                                        "The name of the file, including its extension. This is the last component " +
                                            "of the path."),
                                string("path_lower")
                                    .description(
                                        "The complete path to the file in lowercase, as stored in the user's Dropbox " +
                                            "account."),
                                string("path_display")
                                    .description(
                                        "A user-friendly version of the file's path, preserving the original casing " +
                                            "for better readability."),
                                string("id")
                                    .description("ID of the file within Dropbox.")),
                        string("link")
                            .description(
                                "A temporary URL that can be used to stream the content of the file. This link " +
                                    "expires after four hours."))))
        .perform(DropboxGetFileLinkAction::perform);

    protected static final ContextFunction<Http, Http.Executor> POST_TEMPORARY_LINK_CONTEXT_FUNCTION =
        http -> http.post("https://api.dropboxapi.com/2/files/get_temporary_link");

    private DropboxGetFileLinkAction() {
    }

    public static Object perform(Parameters inputParameters, Parameters connectionParameters, Context context) {
        return context.http(POST_TEMPORARY_LINK_CONTEXT_FUNCTION)
            .body(
                Http.Body.of(
                    Map.of(
                        PATH,
                        getFullPath(
                            inputParameters.getRequiredString(PATH), inputParameters.getRequiredString(FILENAME)))))
            .configuration(Http.responseType(Http.ResponseType.JSON))
            .execute()
            .getBody(new TypeReference<>() {});
    }
}
