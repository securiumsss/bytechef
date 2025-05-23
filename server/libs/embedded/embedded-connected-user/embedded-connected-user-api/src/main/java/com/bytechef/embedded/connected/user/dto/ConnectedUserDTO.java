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

package com.bytechef.embedded.connected.user.dto;

import com.bytechef.embedded.connected.user.domain.ConnectedUser;
import com.bytechef.platform.connection.domain.Connection.CredentialStatus;
import com.bytechef.platform.constant.Environment;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * @author Ivica Cardic
 */
@SuppressFBWarnings("EI")
public record ConnectedUserDTO(
    String createdBy, Instant createdDate, String email, boolean enabled, Environment environment,
    String externalId, Long id, List<IntegrationInstance> integrationInstances, Map<String, String> metadata,
    String lastModifiedBy, Instant lastModifiedDate, String name, int version) {

    public ConnectedUserDTO(ConnectedUser connectedUser, List<IntegrationInstance> integrationInstances) {
        this(
            connectedUser.getCreatedBy(), connectedUser.getCreatedDate(), connectedUser.getEmail(),
            connectedUser.isEnabled(), connectedUser.getEnvironment(), connectedUser.getExternalId(),
            connectedUser.getId(), integrationInstances,
            connectedUser.getMetadata(), connectedUser.getLastModifiedBy(), connectedUser.getLastModifiedDate(),
            connectedUser.getName(), connectedUser.getVersion());
    }

    public record IntegrationInstance(
        String componentName, CredentialStatus credentialStatus, boolean enabled, long id, long integrationId,
        long integrationInstanceConfigurationId, int integrationVersion, Long connectionId) {
    }
}
