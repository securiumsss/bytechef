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

package com.bytechef.component.salesflare.action;

import static com.bytechef.component.OpenApiComponentHandler.PropertyType;
import static com.bytechef.component.definition.ComponentDsl.action;
import static com.bytechef.component.definition.ComponentDsl.array;
import static com.bytechef.component.definition.ComponentDsl.date;
import static com.bytechef.component.definition.ComponentDsl.integer;
import static com.bytechef.component.definition.ComponentDsl.object;
import static com.bytechef.component.definition.ComponentDsl.outputSchema;
import static com.bytechef.component.definition.ComponentDsl.string;
import static com.bytechef.component.definition.Context.Http.BodyContentType;
import static com.bytechef.component.definition.Context.Http.ResponseType;

import com.bytechef.component.definition.ComponentDsl;
import java.util.Map;

/**
 * Provides a list of the component actions.
 *
 * @generated
 */
public class SalesflareCreateTasksAction {
    public static final ComponentDsl.ModifiableActionDefinition ACTION_DEFINITION = action("createTasks")
        .title("Create Tasks")
        .description("Creates new tasks.")
        .metadata(
            Map.of(
                "method", "POST",
                "path", "/tasks", "bodyContentType", BodyContentType.JSON, "mimeType", "application/json"

            ))
        .properties(array("tasks").items(object().properties(string("description").label("Description")
            .description("Description of new task.")
            .required(true),
            date("reminder_date").label("Reminder Data")
                .required(false)))
            .placeholder("Add to Tasks")
            .metadata(
                Map.of(
                    "type", PropertyType.BODY))
            .label("Tasks")
            .required(true))
        .output(outputSchema(array().items(object().properties(integer("id").description("ID of the task.")
            .required(false))
            .description("List of created tasks."))
            .description("List of created tasks.")
            .metadata(
                Map.of(
                    "responseType", ResponseType.JSON))));

    private SalesflareCreateTasksAction() {
    }
}
