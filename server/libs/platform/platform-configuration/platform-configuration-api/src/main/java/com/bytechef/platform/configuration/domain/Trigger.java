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

package com.bytechef.platform.configuration.domain;

/**
 * @author Ivica Cardic
 */
public interface Trigger {
    /**
     * Get the type of the trigger. Type strings are mapped to
     * {@link com.bytechef.platform.worker.trigger.handler.TriggerHandler} implementations designed to handle that type
     * of trigger.
     *
     * @return String
     */
    String getType();
}
