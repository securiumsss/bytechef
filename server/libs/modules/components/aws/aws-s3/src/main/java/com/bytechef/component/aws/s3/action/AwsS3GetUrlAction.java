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

package com.bytechef.component.aws.s3.action;

import static com.bytechef.component.aws.s3.constant.AwsS3Constants.BUCKET_NAME;
import static com.bytechef.component.aws.s3.constant.AwsS3Constants.KEY;
import static com.bytechef.component.definition.ComponentDsl.action;
import static com.bytechef.component.definition.ComponentDsl.outputSchema;
import static com.bytechef.component.definition.ComponentDsl.sampleOutput;
import static com.bytechef.component.definition.ComponentDsl.string;

import com.bytechef.component.aws.s3.util.AwsS3Utils;
import com.bytechef.component.definition.ComponentDsl.ModifiableActionDefinition;
import com.bytechef.component.definition.Context;
import com.bytechef.component.definition.Parameters;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;

/**
 * @author Ivica Cardic
 */
public class AwsS3GetUrlAction {

    public static final ModifiableActionDefinition ACTION_DEFINITION = action("getUrl")
        .title("Get URL")
        .description("Get the url of an AWS S3 object.")
        .properties(
            string(KEY)
                .label("Key or Entity Tag (Etag)")
                .description("Key is most likely the name of the file.")
                .placeholder("file.txt")
                .required(true))
        .output(outputSchema(string()), sampleOutput("https://s3.amazonaws.com/bucket-name/key"))
        .perform(AwsS3GetUrlAction::perform);

    protected static String perform(
        Parameters inputParameters, Parameters connectionParameters, Context context) {

        try (S3Client s3Client = AwsS3Utils.buildS3Client(connectionParameters)) {
            return s3Client
                .utilities()
                .getUrl(GetUrlRequest.builder()
                    .bucket(connectionParameters.getRequiredString(BUCKET_NAME))
                    .key(inputParameters.getRequiredString(KEY))
                    .build())
                .toString();
        }
    }
}
