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

package com.bytechef.component.google.drive.action;

import static com.bytechef.component.definition.ComponentDsl.action;
import static com.bytechef.component.definition.ComponentDsl.outputSchema;
import static com.bytechef.component.definition.ComponentDsl.sampleOutput;
import static com.bytechef.component.definition.ComponentDsl.string;
import static com.bytechef.component.google.drive.constant.GoogleDriveConstants.APPLICATION_VND_GOOGLE_APPS_FOLDER;
import static com.bytechef.component.google.drive.constant.GoogleDriveConstants.FOLDER_NAME;
import static com.bytechef.component.google.drive.constant.GoogleDriveConstants.GOOGLE_FILE_OUTPUT_PROPERTY;
import static com.bytechef.component.google.drive.constant.GoogleDriveConstants.GOOGLE_FILE_SAMPLE_OUTPUT;
import static com.bytechef.google.commons.constant.GoogleCommonsContants.FOLDER_ID;

import com.bytechef.component.definition.ComponentDsl.ModifiableActionDefinition;
import com.bytechef.component.definition.Context;
import com.bytechef.component.definition.Parameters;
import com.bytechef.google.commons.GoogleServices;
import com.bytechef.google.commons.GoogleUtils;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Mario Cvjetojevic
 * @author Monika Kušter
 */
public class GoogleDriveCreateNewFolderAction {

    public static final ModifiableActionDefinition ACTION_DEFINITION = action("createNewFolder")
        .title("Create New Folder")
        .description("Creates a new empty folder in Google Drive.")
        .properties(
            string(FOLDER_NAME)
                .label("Folder Name")
                .description("The name of the new folder.")
                .required(true),
            string(FOLDER_ID)
                .label("Parent Folder ID")
                .description(
                    "ID of the folder where the new folder will be created; if no folder is selected, the folder " +
                        "will be created in the root folder.")
                .options(GoogleUtils.getFileOptionsByMimeType(APPLICATION_VND_GOOGLE_APPS_FOLDER, true))
                .required(false))
        .output(outputSchema(GOOGLE_FILE_OUTPUT_PROPERTY), sampleOutput(GOOGLE_FILE_SAMPLE_OUTPUT))
        .perform(GoogleDriveCreateNewFolderAction::perform);

    private GoogleDriveCreateNewFolderAction() {
    }

    public static File perform(Parameters inputParameters, Parameters connectionParameters, Context context)
        throws IOException {

        Drive drive = GoogleServices.getDrive(connectionParameters);
        String parentFolder = inputParameters.getString(FOLDER_ID);

        File folderFile = new File()
            .setName(inputParameters.getRequiredString(FOLDER_NAME))
            .setMimeType(APPLICATION_VND_GOOGLE_APPS_FOLDER)
            .setParents(parentFolder == null ? null : List.of(parentFolder));

        return drive
            .files()
            .create(folderFile)
            .execute();
    }
}
