/*
 * Author veni.vidi.dev (veni.vidi.dev@gmail.com)
 */
export interface UserBasic {
    username: string;
    password: string;
}

export type User = UserBasic & {
    id: number;
    firstName: string;
    lastName: string;
    roles: Role[];
}

export interface Role {
    id: number;
    name: string;
}

export interface LoginResponse {
    token: string;
    user: User;
}

export interface Activity {
    id: number;
    name: string;
    startDate: Date;
    endDate: Date;
}

export interface UserActivity {
    firstName: string,
    lastName: string,
    activityName: string,
    start: Date,
    end: Date,
    added: Date
}