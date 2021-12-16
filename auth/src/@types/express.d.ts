interface IAuth {
    user_token_id: string;
    user_token_role: string;
    user_token_activated: boolean;
}

declare namespace Express {
    export interface Request {
        auth: IAuth;
    }
}
