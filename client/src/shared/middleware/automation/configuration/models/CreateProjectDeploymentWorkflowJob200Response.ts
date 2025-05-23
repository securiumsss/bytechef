/* tslint:disable */
/* eslint-disable */
/**
 * The Automation Configuration Internal API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */

import { mapValues } from '../runtime';
/**
 * 
 * @export
 * @interface CreateProjectDeploymentWorkflowJob200Response
 */
export interface CreateProjectDeploymentWorkflowJob200Response {
    /**
     * The id of an executed job.
     * @type {number}
     * @memberof CreateProjectDeploymentWorkflowJob200Response
     */
    jobId?: number;
}

/**
 * Check if a given object implements the CreateProjectDeploymentWorkflowJob200Response interface.
 */
export function instanceOfCreateProjectDeploymentWorkflowJob200Response(value: object): value is CreateProjectDeploymentWorkflowJob200Response {
    return true;
}

export function CreateProjectDeploymentWorkflowJob200ResponseFromJSON(json: any): CreateProjectDeploymentWorkflowJob200Response {
    return CreateProjectDeploymentWorkflowJob200ResponseFromJSONTyped(json, false);
}

export function CreateProjectDeploymentWorkflowJob200ResponseFromJSONTyped(json: any, ignoreDiscriminator: boolean): CreateProjectDeploymentWorkflowJob200Response {
    if (json == null) {
        return json;
    }
    return {
        
        'jobId': json['jobId'] == null ? undefined : json['jobId'],
    };
}

export function CreateProjectDeploymentWorkflowJob200ResponseToJSON(json: any): CreateProjectDeploymentWorkflowJob200Response {
    return CreateProjectDeploymentWorkflowJob200ResponseToJSONTyped(json, false);
}

export function CreateProjectDeploymentWorkflowJob200ResponseToJSONTyped(value?: CreateProjectDeploymentWorkflowJob200Response | null, ignoreDiscriminator: boolean = false): any {
    if (value == null) {
        return value;
    }

    return {
        
        'jobId': value['jobId'],
    };
}

