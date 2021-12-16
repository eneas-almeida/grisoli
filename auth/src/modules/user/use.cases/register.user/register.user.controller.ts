import { container } from 'tsyringe';
import { Request, Response } from 'express';

import { RegisterUserService } from './register.user.service';
import * as httpStatus from '@shared/helpers/status.code';

class RegisterUserController {
    constructor() {
        console.log(`RegisterUserController instance!`);
    }

    async handle(req: Request, res: Response): Promise<Response> {
        const { name, email, password } = req.body;

        const data = {
            name,
            email,
            password,
        };

        const service = container.resolve(RegisterUserService);

        const user = await service.execute(data);

        const doc = {
            user,
        };

        return res.status(httpStatus.CREATED).json({ doc });
    }
}

export { RegisterUserController };
