import { container } from 'tsyringe';

import { IHashProvider } from './models/hash.provider';
import { BcryptHashProvider } from './services/bcrypt.hash.provider';

class HashProviderStrategy {
    private _strategies: any = {};

    constructor() {
        this._strategies['bcrypt'] = () => BcryptHashProvider;
    }

    public setStrategy(service: string): void {
        const existsStrategy = this._strategies.hasOwnProperty(service);

        if (!existsStrategy) {
            throw new Error('Service provider not found in strategies!');
        }

        container.registerSingleton<IHashProvider>('HashProvider', this._strategies[service]());
    }
}

export { HashProviderStrategy };
