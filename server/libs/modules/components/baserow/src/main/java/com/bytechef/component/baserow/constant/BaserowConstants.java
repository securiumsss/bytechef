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

package com.bytechef.component.baserow.constant;

import static com.bytechef.component.definition.ComponentDsl.bool;
import static com.bytechef.component.definition.ComponentDsl.dynamicProperties;

import com.bytechef.component.baserow.util.BaserowPropertiesUtils;
import com.bytechef.component.definition.ComponentDsl.ModifiableBooleanProperty;
import com.bytechef.component.definition.ComponentDsl.ModifiableDynamicPropertiesProperty;

/**
 * @author Monika Kušter
 */
public class BaserowConstants {

    public static final String FIELDS = "fields";
    public static final String NAME = "name";
    public static final String ORDER_BY = "order_by";
    public static final String READ_ONLY = "read_only";
    public static final String ROW_ID = "rowId";
    public static final String SIZE = "size";
    public static final String TABLE_ID = "tableId";
    public static final String TYPE = "type";
    public static final String USER_FIELD_NAMES = "user_field_names";

    public static final ModifiableDynamicPropertiesProperty FIELDS_DYNAMIC_PROPERTY = dynamicProperties(FIELDS)
        .propertiesLookupDependsOn(TABLE_ID)
        .properties(BaserowPropertiesUtils::createPropertiesForRow)
        .required(true);

    public static final ModifiableBooleanProperty USER_FIELD_NAMES_PROPERTY = bool(USER_FIELD_NAMES)
        .label("User Field Names")
        .description("The field names returned by this endpoint will be the actual names of the fields.")
        .defaultValue(true)
        .required(false);

    private BaserowConstants() {
    }
}
