import { hash, compare } from 'bcryptjs';

import { IHashProvider } from '../models/hash.provider';

class BcryptHashProvider implements IHashProvider {
    public async gererateHash(payload: string): Promise<string> {
        return await hash(payload, 10);
    }

    public async compareHash(payload: string, hashed: string): Promise<boolean> {
        return await compare(payload, hashed);
    }
}

export { BcryptHashProvider };
