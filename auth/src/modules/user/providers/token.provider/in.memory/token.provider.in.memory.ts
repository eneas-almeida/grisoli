import { randomBytes } from 'crypto';

import { ITokenProvider } from '../models/token-provider';
import { ICreatePayloadDTO } from '@modules/user/dtos/create.payload.dto';
import { IPayloadDTO } from '@modules/user/dtos/payload.dto';

class TokenProviderInMemory implements ITokenProvider {
    public async generate(_: ICreatePayloadDTO): Promise<string> {
        return randomBytes(8).toString('hex');
    }

    public validate(_: string): IPayloadDTO {
        return {
            user: {
                user_token_id: randomBytes(2).toString('hex'),
                user_token_role: 'USER',
                user_token_activated: true,
            },
        };
    }
}

export { TokenProviderInMemory };
