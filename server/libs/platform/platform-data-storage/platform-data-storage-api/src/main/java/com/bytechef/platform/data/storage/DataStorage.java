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

package com.bytechef.platform.data.storage;

import com.bytechef.platform.constant.ModeType;
import com.bytechef.platform.data.storage.domain.DataStorageScope;
import java.util.Map;
import java.util.Optional;
import org.springframework.lang.NonNull;

/**
 * @author Ivica Cardic
 */
public interface DataStorage {

    void delete(
        @NonNull String componentName, @NonNull DataStorageScope scope, @NonNull String scopeId, @NonNull String key,
        @NonNull ModeType type);

    <T> Optional<T> fetch(
        @NonNull String componentName, @NonNull DataStorageScope scope, @NonNull String scopeId, @NonNull String key,
        @NonNull ModeType type);

    <T> T get(
        @NonNull String componentName, @NonNull DataStorageScope scope, @NonNull String scopeId, @NonNull String key,
        @NonNull ModeType type);

    <T> Map<String, T> getAll(
        @NonNull String componentName, @NonNull DataStorageScope scope, @NonNull String scopeId,
        @NonNull ModeType type);

    void put(
        @NonNull String componentName, @NonNull DataStorageScope scope, @NonNull String scopeId, @NonNull String key,
        @NonNull ModeType type, @NonNull Object value);
}
