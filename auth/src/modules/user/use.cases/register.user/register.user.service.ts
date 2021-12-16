import { injectable, inject } from 'tsyringe';

import { IUserRepository } from '@modules/user/repositories/user.repository';
import { ITokenProvider } from '@modules/user/providers/token.provider/models/token-provider';
import { IHashProvider } from '@modules/user/providers/hash.provider/models/hash.provider';
import { IRegisterUserDTO } from '@modules/user/dtos/register.user.dto';
import { IUser } from '@modules/user/models/user';

@injectable()
class RegisterUserService {
    constructor(
        @inject('UserRepository') public _userRepository: IUserRepository,
        @inject('TokenProvider') private _tokenProvider: ITokenProvider,
        @inject('HashProvider') private _hashProvider: IHashProvider
    ) {}

    async execute(data: IRegisterUserDTO): Promise<IUser> {
        /* Destructuring data */

        const { email, password } = data;

        /* Find user by email */

        const existsUser = await this._userRepository.findOneByEmail(email);

        /* Guard strategy */

        if (existsUser) {
            throw new Error(`User email ${email} already exists in repository!`);
        }

        /* Generate hash password by provider */

        const generateHashPassword = await this._hashProvider.gererateHash(password);

        /* Change password to hash */

        data.password = generateHashPassword;

        /* Create user */

        const userCreated = await this._userRepository.create(data);

        /* Destructuring object */

        const { id, role, activated } = userCreated;

        /* Payload generated */

        const payload = {
            id,
            role,
            activated,
        };

        /* Token generated */

        const token = await this._tokenProvider.generate(payload);

        /* User object assign */

        Object.assign(userCreated, { token });

        /* Return user created */

        return userCreated;
    }
}

export { RegisterUserService };
