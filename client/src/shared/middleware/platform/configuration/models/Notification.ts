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
import type { NotificationEvent } from './NotificationEvent';
import {
    NotificationEventFromJSON,
    NotificationEventFromJSONTyped,
    NotificationEventToJSON,
    NotificationEventToJSONTyped,
} from './NotificationEvent';

/**
 * A Notification definition.
 * @export
 * @interface Notification
 */
export interface Notification {
    /**
     * Notification Id
     * @type {number}
     * @memberof Notification
     */
    readonly id?: number;
    /**
     * The created by.
     * @type {string}
     * @memberof Notification
     */
    readonly createdBy?: string;
    /**
     * The created date.
     * @type {Date}
     * @memberof Notification
     */
    readonly createdDate?: Date;
    /**
     * The last modified by.
     * @type {string}
     * @memberof Notification
     */
    readonly lastModifiedBy?: string;
    /**
     * The last modified date.
     * @type {Date}
     * @memberof Notification
     */
    readonly lastModifiedDate?: Date;
    /**
     * Notification name
     * @type {string}
     * @memberof Notification
     */
    name: string;
    /**
     * Type of the notification
     * @type {string}
     * @memberof Notification
     */
    type: NotificationTypeEnum;
    /**
     * Notification type related settings
     * @type {{ [key: string]: object; }}
     * @memberof Notification
     */
    settings: { [key: string]: object; };
    /**
     * List of events for which notification will be triggered
     * @type {Array<NotificationEvent>}
     * @memberof Notification
     */
    readonly notificationEvents?: Array<NotificationEvent>;
    /**
     * List of event ids for which notification will be triggered
     * @type {Array<number>}
     * @memberof Notification
     */
    notificationEventIds?: Array<number>;
    /**
     * 
     * @type {number}
     * @memberof Notification
     */
    version?: number;
}


/**
 * @export
 */
export const NotificationTypeEnum = {
    Email: 'EMAIL',
    Webhook: 'WEBHOOK'
} as const;
export type NotificationTypeEnum = typeof NotificationTypeEnum[keyof typeof NotificationTypeEnum];


/**
 * Check if a given object implements the Notification interface.
 */
export function instanceOfNotification(value: object): value is Notification {
    if (!('name' in value) || value['name'] === undefined) return false;
    if (!('type' in value) || value['type'] === undefined) return false;
    if (!('settings' in value) || value['settings'] === undefined) return false;
    return true;
}

export function NotificationFromJSON(json: any): Notification {
    return NotificationFromJSONTyped(json, false);
}

export function NotificationFromJSONTyped(json: any, ignoreDiscriminator: boolean): Notification {
    if (json == null) {
        return json;
    }
    return {
        
        'id': json['id'] == null ? undefined : json['id'],
        'createdBy': json['createdBy'] == null ? undefined : json['createdBy'],
        'createdDate': json['createdDate'] == null ? undefined : (new Date(json['createdDate'])),
        'lastModifiedBy': json['lastModifiedBy'] == null ? undefined : json['lastModifiedBy'],
        'lastModifiedDate': json['lastModifiedDate'] == null ? undefined : (new Date(json['lastModifiedDate'])),
        'name': json['name'],
        'type': json['type'],
        'settings': json['settings'],
        'notificationEvents': json['notificationEvents'] == null ? undefined : ((json['notificationEvents'] as Array<any>).map(NotificationEventFromJSON)),
        'notificationEventIds': json['notificationEventIds'] == null ? undefined : json['notificationEventIds'],
        'version': json['__version'] == null ? undefined : json['__version'],
    };
}

export function NotificationToJSON(json: any): Notification {
    return NotificationToJSONTyped(json, false);
}

export function NotificationToJSONTyped(value?: Omit<Notification, 'id'|'createdBy'|'createdDate'|'lastModifiedBy'|'lastModifiedDate'|'notificationEvents'> | null, ignoreDiscriminator: boolean = false): any {
    if (value == null) {
        return value;
    }

    return {
        
        'name': value['name'],
        'type': value['type'],
        'settings': value['settings'],
        'notificationEventIds': value['notificationEventIds'],
        '__version': value['version'],
    };
}

