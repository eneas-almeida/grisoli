import { Request, Response, NextFunction } from 'express';

import { AppException } from '@shared/exceptions/app.exception';
import * as httpStatus from '@shared/helpers/status.code';

class AuthenticateUserValidator {
    constructor() {
        console.log(`AuthenticateUserValidator instance!`);
    }

    public validate(req: Request, _: Response, next: NextFunction): any {
        const { email, password } = req.body;

        if (!email) {
            throw new AppException('Email invalid!', httpStatus.FORBIDDEN);
        }

        if (!password) {
            throw new AppException('Password invalid!', httpStatus.FORBIDDEN);
        }

        return next();
    }
}

export { AuthenticateUserValidator };
