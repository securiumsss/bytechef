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

package com.bytechef.platform.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthorityAsserts {

    /**
     * Asserts that the entity has all properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual   the actual entity
     */
    public static void assertAuthorityAllPropertiesEquals(Authority expected, Authority actual) {
        assertAuthorityAutoGeneratedPropertiesEquals(expected, actual);
        assertAuthorityAllUpdatablePropertiesEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all updatable properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual   the actual entity
     */
    public static void assertAuthorityAllUpdatablePropertiesEquals(Authority expected, Authority actual) {
        assertAuthorityUpdatableFieldsEquals(expected, actual);
        assertAuthorityUpdatableRelationshipsEquals(expected, actual);
    }

    /**
     * Asserts that the entity has all the auto generated properties (fields/relationships) set.
     *
     * @param expected the expected entity
     * @param actual   the actual entity
     */
    public static void assertAuthorityAutoGeneratedPropertiesEquals(Authority expected, Authority actual) {
    }

    /**
     * Asserts that the entity has all the updatable fields set.
     *
     * @param expected the expected entity
     * @param actual   the actual entity
     */
    public static void assertAuthorityUpdatableFieldsEquals(Authority expected, Authority actual) {
        assertThat(expected)
            .as("Verify Authority relevant properties")
            .satisfies(e -> assertThat(e.getName()).as("check name")
                .isEqualTo(actual.getName()));
    }

    /**
     * Asserts that the entity has all the updatable relationships set.
     *
     * @param expected the expected entity
     * @param actual   the actual entity
     */
    public static void assertAuthorityUpdatableRelationshipsEquals(Authority expected, Authority actual) {
    }
}
