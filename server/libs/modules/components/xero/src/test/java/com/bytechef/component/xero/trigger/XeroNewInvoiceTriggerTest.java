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

package com.bytechef.component.xero.trigger;

import static com.bytechef.component.xero.constant.XeroConstants.ACCREC;
import static com.bytechef.component.xero.constant.XeroConstants.INVOICE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.bytechef.component.xero.util.XeroUtils;
import org.junit.jupiter.api.Test;

/**
 * @author Monika Domiter
 */
class XeroNewInvoiceTriggerTest extends AbstractXeroTriggerTest {

    @Test
    void testWebhookRequest() {
        xeroUtilsMockedStatic
            .when(() -> XeroUtils.getCreatedObject(mockedBody, mockedTriggerContext, INVOICE, ACCREC))
            .thenReturn(mockedObject);

        Object result = XeroNewInvoiceTrigger.webhookRequest(
            mockedParameters, mockedParameters, mockedHttpHeaders, mockedHttpParameters, mockedBody, mockedMethod,
            mockedWebhookEnableOutput, mockedTriggerContext);

        assertEquals(mockedObject, result);
    }

}
