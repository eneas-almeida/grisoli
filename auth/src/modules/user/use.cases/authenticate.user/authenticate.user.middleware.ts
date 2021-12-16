import { Router } from 'express';

import { ITypeMethod } from '@shared/helpers/type.method';
import { AuthenticateUserValidator } from './authenticate.user.validator';
import { AuthenticateUserController } from './authenticate.user.controller';

class AuthenticateUserMiddleware {
    public register(router: Router, typeMethod: ITypeMethod, path: string): void {
        const { validate } = new AuthenticateUserValidator();
        const { handle } = new AuthenticateUserController();

        router[typeMethod](path, validate, handle);
    }
}

export { AuthenticateUserMiddleware };
