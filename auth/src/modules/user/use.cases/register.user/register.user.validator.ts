import { Request, Response, NextFunction } from 'express';

class RegisterUserValidator {
    constructor() {
        console.log(`RegisterUserValidator instance!`);
    }

    validate(req: Request, _: Response, next: NextFunction): any {
        const { name, email, password } = req.body;

        if (!name || !email || !password) {
            throw new Error('Invalid!');
        }

        return next();
    }
}

export { RegisterUserValidator };
