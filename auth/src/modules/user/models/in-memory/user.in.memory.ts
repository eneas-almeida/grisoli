import { IUser, IRole } from '@modules/user/models/user';

class UserInMemory implements IUser {
    id: string;
    name: string;
    email: string;
    password: string;
    role: IRole;
    activated: boolean;
    createdAt: Date;
}

export { UserInMemory };
