/* tslint:disable */
/* eslint-disable */
/**
 * The Platform Internal API
 * No description provided (generated by Openapi Generator https://github.com/openapitools/openapi-generator)
 *
 * The version of the OpenAPI document: 1
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


import * as runtime from '../runtime';
import type {
  GitConfiguration,
} from '../models/index';
import {
    GitConfigurationFromJSON,
    GitConfigurationToJSON,
} from '../models/index';

export interface GetGitConfigurationRequest {
    id: number;
}

export interface UpdateGitConfigurationRequest {
    id: number;
    gitConfiguration: GitConfiguration;
}

/**
 * 
 */
export class GitConfigurationApi extends runtime.BaseAPI {

    /**
     * Get git configuration of a workspace.
     * Get git configuration of a workspace.
     */
    async getGitConfigurationRaw(requestParameters: GetGitConfigurationRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<GitConfiguration>> {
        if (requestParameters['id'] == null) {
            throw new runtime.RequiredError(
                'id',
                'Required parameter "id" was null or undefined when calling getGitConfiguration().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        const response = await this.request({
            path: `/workspaces/{id}/git-configuration`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters['id']))),
            method: 'GET',
            headers: headerParameters,
            query: queryParameters,
        }, initOverrides);

        return new runtime.JSONApiResponse(response, (jsonValue) => GitConfigurationFromJSON(jsonValue));
    }

    /**
     * Get git configuration of a workspace.
     * Get git configuration of a workspace.
     */
    async getGitConfiguration(requestParameters: GetGitConfigurationRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<GitConfiguration> {
        const response = await this.getGitConfigurationRaw(requestParameters, initOverrides);
        return await response.value();
    }

    /**
     * Update git configuration.
     * Update git configuration.
     */
    async updateGitConfigurationRaw(requestParameters: UpdateGitConfigurationRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<runtime.ApiResponse<void>> {
        if (requestParameters['id'] == null) {
            throw new runtime.RequiredError(
                'id',
                'Required parameter "id" was null or undefined when calling updateGitConfiguration().'
            );
        }

        if (requestParameters['gitConfiguration'] == null) {
            throw new runtime.RequiredError(
                'gitConfiguration',
                'Required parameter "gitConfiguration" was null or undefined when calling updateGitConfiguration().'
            );
        }

        const queryParameters: any = {};

        const headerParameters: runtime.HTTPHeaders = {};

        headerParameters['Content-Type'] = 'application/json';

        const response = await this.request({
            path: `/workspaces/{id}/git-configuration`.replace(`{${"id"}}`, encodeURIComponent(String(requestParameters['id']))),
            method: 'PUT',
            headers: headerParameters,
            query: queryParameters,
            body: GitConfigurationToJSON(requestParameters['gitConfiguration']),
        }, initOverrides);

        return new runtime.VoidApiResponse(response);
    }

    /**
     * Update git configuration.
     * Update git configuration.
     */
    async updateGitConfiguration(requestParameters: UpdateGitConfigurationRequest, initOverrides?: RequestInit | runtime.InitOverrideFunction): Promise<void> {
        await this.updateGitConfigurationRaw(requestParameters, initOverrides);
    }

}
