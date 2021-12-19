import { container } from 'tsyringe';
import { Request, Response, NextFunction } from 'express';

import { ITokenProvider } from '../providers/token.provider/models/token.provider';
import { AppException } from '@shared/exceptions/app.exception';

class AuthorizeUserMiddleware {
    public authorize(req: Request, _: Response, next: NextFunction): any {
        const schemaToken: string | undefined = req.headers.authorization;

        if (!schemaToken) {
            throw new AppException('Token not provided!', 404);
        }

        const parts: string[] = schemaToken.split(' ');

        if (parts.length !== 2) {
            throw new AppException('Token parts invalid!', 403);
        }

        const [schema, token] = parts;

        if (schema !== 'Bearer') {
            throw new AppException('Token parts invalid!', 403);
        }

        const tokenProvider = container.resolve<ITokenProvider>('TokenProvider');

        const payload = tokenProvider.validate(token);

        req.auth = payload.user;

        return next();
    }
}

export { AuthorizeUserMiddleware };
