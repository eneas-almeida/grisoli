import Youch from 'youch';
import { Errback, Request, Response, NextFunction } from 'express';

import { environment, email } from '@configs/geral.config';
import { generateStatus } from '../../shared/helpers/status';
import { AppException } from './app.exception';
import * as httpStatus from '@shared/helpers/status.code';

class Exception {
    static async execute(err: Errback, req: Request, res: Response, _: NextFunction): Promise<Response> {
        if (err instanceof AppException) {
            const status = generateStatus(true, err.code, err.message, err.payload);

            return res.status(err.code).json({ status });
        }

        if (environment === 'development') {
            return res.status(httpStatus.INTERNAL_SERVER_ERROR).json(await new Youch(err, req).toJSON());
        }

        const status = generateStatus(true, httpStatus.INTERNAL_SERVER_ERROR, `Error in system, contact admin: ${email}`);

        return res.status(httpStatus.INTERNAL_SERVER_ERROR).json({ status });
    }
}

const exception = Exception.execute;

export { exception };
