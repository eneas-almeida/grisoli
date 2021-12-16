type IRole = 'ADMIN' | 'USER';

interface IUser {
    id: string;
    name: string;
    email: string;
    password: string;
    role: IRole;
    activated: boolean;
    createdAt: Date;
}

export { IUser, IRole };
