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

package com.bytechef.component.calendly;

import com.bytechef.component.OpenApiComponentHandler;
import com.bytechef.component.calendly.trigger.CalendlyInviteeCanceledTrigger;
import com.bytechef.component.calendly.trigger.CalendlyInviteeCreatedTrigger;
import com.bytechef.component.definition.ComponentCategory;
import com.bytechef.component.definition.ComponentDsl;
import com.bytechef.component.definition.ComponentDsl.ModifiableComponentDefinition;
import com.google.auto.service.AutoService;
import java.util.List;

/**
 * @author Monika Kušter
 */
@AutoService(OpenApiComponentHandler.class)
public class CalendlyComponentHandler extends AbstractCalendlyComponentHandler {

    @Override
    public List<ComponentDsl.ModifiableTriggerDefinition> getTriggers() {
        return List.of(
            CalendlyInviteeCanceledTrigger.TRIGGER_DEFINITION, CalendlyInviteeCreatedTrigger.TRIGGER_DEFINITION);
    }

    @Override
    public ModifiableComponentDefinition modifyComponent(ModifiableComponentDefinition modifiableComponentDefinition) {
        return modifiableComponentDefinition
            .customAction(true)
            .icon("path:assets/calendly.svg")
            .categories(ComponentCategory.PRODUCTIVITY_AND_COLLABORATION);
    }
}
