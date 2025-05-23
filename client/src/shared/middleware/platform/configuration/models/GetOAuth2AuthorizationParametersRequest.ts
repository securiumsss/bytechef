/* tslint:disable */
/* eslint-disable */
/**
 * The Platform Configuration Internal API
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
 * Contains all required information to open a connection to a service defined by componentName parameter.
 * @export
 * @interface GetOAuth2AuthorizationParametersRequest
 */
export interface GetOAuth2AuthorizationParametersRequest {
    /**
     * The name of an authorization used by this connection. Used for HTTP based services.
     * @type {string}
     * @memberof GetOAuth2AuthorizationParametersRequest
     */
    authorizationName?: string;
    /**
     * The name of a component that uses this connection.
     * @type {string}
     * @memberof GetOAuth2AuthorizationParametersRequest
     */
    componentName: string;
    /**
     * The version of a connection.
     * @type {number}
     * @memberof GetOAuth2AuthorizationParametersRequest
     */
    connectionVersion?: number;
    /**
     * The parameters of a connection.
     * @type {{ [key: string]: any; }}
     * @memberof GetOAuth2AuthorizationParametersRequest
     */
    parameters: { [key: string]: any; };
}

/**
 * Check if a given object implements the GetOAuth2AuthorizationParametersRequest interface.
 */
export function instanceOfGetOAuth2AuthorizationParametersRequest(value: object): value is GetOAuth2AuthorizationParametersRequest {
    if (!('componentName' in value) || value['componentName'] === undefined) return false;
    if (!('parameters' in value) || value['parameters'] === undefined) return false;
    return true;
}

export function GetOAuth2AuthorizationParametersRequestFromJSON(json: any): GetOAuth2AuthorizationParametersRequest {
    return GetOAuth2AuthorizationParametersRequestFromJSONTyped(json, false);
}

export function GetOAuth2AuthorizationParametersRequestFromJSONTyped(json: any, ignoreDiscriminator: boolean): GetOAuth2AuthorizationParametersRequest {
    if (json == null) {
        return json;
    }
    return {
        
        'authorizationName': json['authorizationName'] == null ? undefined : json['authorizationName'],
        'componentName': json['componentName'],
        'connectionVersion': json['connectionVersion'] == null ? undefined : json['connectionVersion'],
        'parameters': json['parameters'],
    };
}

export function GetOAuth2AuthorizationParametersRequestToJSON(json: any): GetOAuth2AuthorizationParametersRequest {
    return GetOAuth2AuthorizationParametersRequestToJSONTyped(json, false);
}

export function GetOAuth2AuthorizationParametersRequestToJSONTyped(value?: GetOAuth2AuthorizationParametersRequest | null, ignoreDiscriminator: boolean = false): any {
    if (value == null) {
        return value;
    }

    return {
        
        'authorizationName': value['authorizationName'],
        'componentName': value['componentName'],
        'connectionVersion': value['connectionVersion'],
        'parameters': value['parameters'],
    };
}

