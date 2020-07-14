/*
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
import {
    Activity,
    LoginResponse,
    User,
    UserActivity,
    UserBasic
} from "../types/Types";
import Cookies from 'js-cookie';

class RestService {

    static url: string = 'http://localhost:8080/api/rest/';

    static async http<T>(url: string, requestOptions?: RequestInit): Promise<T> {
        const response = requestOptions ? await fetch(url, requestOptions) : await fetch(url);
        const data = await response.json();
        return data;
    }

    private static options = (method: string, token?: string): RequestInit => {
        return {
            method: method,
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer ' + token ? token : Cookies.get('token')
                }
        } as RequestInit;
    }

    static async get<R>(url: string, token?: string): Promise<R> {
        return await RestService.http<R>(url, this.options('GET', token));
    }

    static async post<T, R>(url: string, body: T): Promise<R> {
        const requestOptions = this.options('POST');
        requestOptions.body = body === null ? null : (typeof body === 'string' ? body : JSON.stringify(body))
        return await RestService.http<R>(url, requestOptions);
    }

    static async getActivities(token?: string): Promise<UserActivity[]> {
        const requestInfo: string = this.url+'activities/';
        return await RestService.get<UserActivity[]>(requestInfo, token);
    }

    static async addUserToActivity(activityId: number, userId: number): Promise<string> {
        return await RestService.post<null, string>(this.url+`activity/${activityId}/user/${userId}`, null);
    }

    static async getAllActivities(): Promise<Activity[]> {
        const requestInfo: string = this.url+'allactivities';
        return await RestService.get<Activity[]>(requestInfo);
    }

    static async getAllUsers(): Promise<User[]> {
        const requestInfo: string = this.url+'allusers';
        return await RestService.get<User[]>(requestInfo);
    }

    static async login(user: UserBasic): Promise<User> {
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        };
        const response: LoginResponse = await RestService.http<LoginResponse>(this.url+'login', requestOptions);
        Cookies.set('token', response.token, { expires: 7 });
        return response.user;
    }

}

export default RestService;