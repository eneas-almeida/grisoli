import { PrismaClient } from '@prisma/client';

import { IUserRepository } from '@modules/user/repositories/user.repository';
import { prisma } from '@database';
import { IRegisterUserDTO } from '@modules/user/dtos/register.user.dto';
import { IUser } from '@modules/user/models/user';

class UserRepositoryPrisma implements IUserRepository {
    private _repository: PrismaClient;

    constructor() {
        this._repository = prisma;
    }

    async create(data: IRegisterUserDTO): Promise<IUser> {
        const { name, email, password } = data;

        const user = {
            name,
            email,
            password,
        };

        const userCreated = await this._repository.user.create({
            data: user,
        });

        return userCreated;
    }

    async findOneByEmail(email: string): Promise<IUser | undefined> {
        const user = await this._repository.user.findUnique({
            where: {
                email,
            },
        });

        return user || undefined;
    }
}

export { UserRepositoryPrisma };
