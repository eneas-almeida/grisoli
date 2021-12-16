import { sign, verify } from 'jsonwebtoken';

import { ICreatePayloadDTO } from '@modules/user/dtos/create.payload.dto';
import { IPayloadDTO } from '@modules/user/dtos/payload.dto';
import { ITokenProvider } from '../models/token-provider';
import { tokenSecret, tokenExpires } from '@configs/token.config';
import { AppException } from '@shared/exceptions/app.exception';

class JWTTokenProvider implements ITokenProvider {
    public async generate(data: ICreatePayloadDTO): Promise<string> {
        const { id, role, activated } = data;

        try {
            const payload: IPayloadDTO = {
                user: {
                    user_token_id: id,
                    user_token_role: role,
                    user_token_activated: activated,
                },
            };

            const token = sign(payload, tokenSecret, { expiresIn: tokenExpires });

            return token;
        } catch {
            throw new Error('Token not generated!');
        }
    }

    public validate(token: string): IPayloadDTO {
        try {
            const decoded: object | string = verify(token, tokenSecret);

            const { user } = decoded as IPayloadDTO;

            return { user };
        } catch {
            throw new AppException('Token expired or invalid!', 403);
        }
    }
}

export { JWTTokenProvider };
