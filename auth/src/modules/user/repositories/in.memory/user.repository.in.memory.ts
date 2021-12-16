import 'reflect-metadata';

import { IRegisterUserDTO } from '@modules/user/dtos/register.user.dto';
import { IUser } from '@modules/user/models/user';
import { UserInMemory } from '@modules/user/models/in-memory/user.in.memory';
import { IUserRepository } from '../user.repository';

class UserRepositoryInMemory implements IUserRepository {
    private _userRepository: IUser[];

    constructor() {
        this._userRepository = [];
    }

    async create(data: IRegisterUserDTO): Promise<IUser> {
        const { name, email, password } = data;

        const user = new UserInMemory();

        Object.assign(user, { name, email, password });

        this._userRepository.push(user);

        return user;
    }

    async findOneByEmail(email: string): Promise<IUser | undefined> {
        return this._userRepository.find((e) => e.email === email);
    }
}

export { UserRepositoryInMemory };
