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

package com.bytechef.component.ai.universal.text.task.handler;

import static com.bytechef.component.ai.universal.text.constant.AiTextConstants.CLASSIFY_TEXT;
import static com.bytechef.platform.component.definition.AiUniversalComponentDefinition.AI_TEXT;

import com.bytechef.platform.component.facade.ActionDefinitionFacade;
import com.bytechef.platform.workflow.worker.task.handler.AbstractTaskHandler;
import org.springframework.stereotype.Component;

/**
 * @author Ivica Cardic
 */
@Component(AI_TEXT + "/v1/" + CLASSIFY_TEXT)
public class AiTextClassifyTextTaskHandler extends AbstractTaskHandler {

    public AiTextClassifyTextTaskHandler(ActionDefinitionFacade actionDefinitionFacade) {
        super(AI_TEXT, 1, CLASSIFY_TEXT, actionDefinitionFacade);
    }
}
