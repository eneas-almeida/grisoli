import { IRegisterUserDTO } from '../dtos/register.user.dto';
import { IUser } from '@modules/user/models/user';

interface IUserRepository {
    create(data: IRegisterUserDTO): Promise<IUser>;

    findOneByEmail(email: string): Promise<IUser | undefined>;
}

export { IUserRepository };
