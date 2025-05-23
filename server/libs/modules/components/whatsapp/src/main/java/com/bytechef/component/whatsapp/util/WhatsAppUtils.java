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

package com.bytechef.component.whatsapp.util;

import static com.bytechef.component.definition.Authorization.AUTHORIZATION;

import com.bytechef.component.definition.Context;
import com.bytechef.component.definition.Context.Http;
import com.bytechef.component.definition.TypeReference;
import java.util.Map;

public class WhatsAppUtils {

    public static String getWhatsappServer(String accessToken, Context context) {
        Map<?, ?> response = context.http(http -> http.get("/metadata")
            .configuration(Http.responseType(Http.ResponseType.JSON))
            .header(AUTHORIZATION, "OAuth " + accessToken)
            .execute()
            .getBody(new TypeReference<>() {}));

        if (!response.containsKey("dc")) {
            throw new IllegalStateException(
                "%s: %s".formatted(response.get("error"), response.get("error_description")));
        }

        return (String) response.get("dc");
    }

    private WhatsAppUtils() {
    }
}
