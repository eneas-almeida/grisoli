import { injectable, inject } from 'tsyringe';

import { IUserRepository } from '@modules/user/repositories/user.repository';
import { ITokenProvider } from '@modules/user/providers/token.provider/models/token.provider';
import { IHashProvider } from '@modules/user/providers/hash.provider/models/hash.provider';
import { IAuthenticateUserDTO } from '@modules/user/dtos/auth.user.dto';
import { IUser } from '@modules/user/models/user';
import { AppException } from '@shared/exceptions/app.exception';
import * as httpStatus from '@shared/helpers/status.code';

@injectable()
class AuthenticateUserService {
    constructor(
        @inject('UserRepository') private _userRepository: IUserRepository,
        @inject('TokenProvider') private _tokenProvider: ITokenProvider,
        @inject('HashProvider') private _hashProvider: IHashProvider
    ) {}

    public async execute(data: IAuthenticateUserDTO): Promise<IUser> {
        /* Destructuring object */

        const { email, password } = data;

        /* Find user by email */

        const existsUser = await this._userRepository.findOneByEmail(email);

        /* Strategy guard */

        if (!existsUser) {
            throw new AppException('Email or password invalid!', httpStatus.FORBIDDEN);
        }

        /* Check if password is equals */

        const isPasswordEquals = await this._hashProvider.compareHash(password, existsUser.password);

        /* Strategy guard */

        if (!isPasswordEquals) {
            throw new AppException('Email or password invalid!', httpStatus.FORBIDDEN);
        }

        /* Destructuring object */

        const { id, role, activated } = existsUser;

        /* User */

        const user = {
            id,
            role,
            activated,
        };

        /* Generate token by provider */

        const token = await this._tokenProvider.generate(user);

        /* End generate token by provider */

        Object.assign(existsUser, { token });

        /* Return user */

        return existsUser;
    }
}

export { AuthenticateUserService };
