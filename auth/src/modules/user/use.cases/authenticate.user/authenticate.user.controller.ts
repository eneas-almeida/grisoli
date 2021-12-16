import { container } from 'tsyringe';
import { Request, Response } from 'express';

import { AuthenticateUserService } from './authenticate.user.service';
import * as httpStatus from '@shared/helpers/status.code';

class AuthenticateUserController {
    constructor() {
        console.log(`AuthenticateUserController instance!`);
    }

    public async handle(req: Request, res: Response): Promise<Response> {
        const { email, password } = req.body;

        const service = container.resolve(AuthenticateUserService);

        const data = {
            email,
            password,
        };

        const user = await service.execute(data);

        return res.status(httpStatus.SUCESS).json(user);
    }
}

export { AuthenticateUserController };
