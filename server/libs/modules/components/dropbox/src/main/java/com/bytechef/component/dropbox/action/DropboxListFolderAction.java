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
import static com.bytechef.component.definition.ComponentDsl.array;
import static com.bytechef.component.definition.ComponentDsl.object;
import static com.bytechef.component.definition.ComponentDsl.outputSchema;
import static com.bytechef.component.definition.ComponentDsl.string;
import static com.bytechef.component.dropbox.constant.DropboxConstants.PATH;

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
public class DropboxListFolderAction {

    public static final ModifiableActionDefinition ACTION_DEFINITION = action("listFolder")
        .title("List Folder")
        .description("List the contents of a folder.")
        .properties(
            string(PATH)
                .label("Path")
                .description("Path of the filename. Inputting nothing searches root.")
                .required(false))
        .output(
            outputSchema(
                object()
                    .properties(
                        array("entries")
                            .items(
                                object()
                                    .properties(
                                        string("name")
                                            .description(
                                                "The name of the file or folder, including its extension. This is " +
                                                    "the last component of the path."),
                                        string("path_lower")
                                            .description(
                                                "The full path to the file or folder in lowercase, as stored in the " +
                                                    "user's Dropbox."),
                                        string("path_display")
                                            .description(
                                                "The display-friendly version of the path to the file or folder, " +
                                                    "preserving original casing."),
                                        string("id")
                                            .description("ID of the file or folder."))))))
        .perform(DropboxListFolderAction::perform);

    protected static final ContextFunction<Http, Http.Executor> POST_LIST_FOLDER_CONTEXT_FUNCTION =
        http -> http.post("https://api.dropboxapi.com/2/files/list_folder");

    private DropboxListFolderAction() {
    }

    public static Object perform(Parameters inputParameters, Parameters connectionParameters, Context context) {
        return context.http(POST_LIST_FOLDER_CONTEXT_FUNCTION)
            .body(
                Http.Body.of(
                    Map.of(
                        PATH, inputParameters.getString(PATH, ""))))
            .configuration(Http.responseType(Http.ResponseType.JSON))
            .execute()
            .getBody(new TypeReference<>() {});
    }
}
