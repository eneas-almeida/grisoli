import { UserRepositoryInMemory } from '@modules/user/repositories/in.memory/user.repository.in.memory';
import { RegisterUserService } from './register.user.service';
import { ITokenProvider } from '@modules/user/providers/token.provider/models/token.provider';
import { IHashProvider } from '@modules/user/providers/hash.provider/models/hash.provider';
import { HashProviderInMemory } from '@modules/user/providers/hash.provider/in.memory/hash.provider.in.memory';
import { TokenProviderInMemory } from '@modules/user/providers/token.provider/in.memory/token.provider.in.memory';

let userRepositoryInMemory: UserRepositoryInMemory;
let registerUserService: RegisterUserService;
let tokenProviderInMemory: ITokenProvider;
let hashProviderInMemory: IHashProvider;

describe('RegisterUserService', () => {
    beforeEach(() => {
        userRepositoryInMemory = new UserRepositoryInMemory();
        tokenProviderInMemory = new TokenProviderInMemory();
        hashProviderInMemory = new HashProviderInMemory();

        registerUserService = new RegisterUserService(userRepositoryInMemory, tokenProviderInMemory, hashProviderInMemory);
    });

    // TEST 1

    it('should be register a new user', async () => {
        const user = await registerUserService.execute({
            name: 'tiago',
            email: 'tiago@gmail.com',
            password: 'penadepato',
        });

        expect(user).toHaveProperty('name');
        expect(user).toHaveProperty('email');
        expect(user).toHaveProperty('password');
    });

    // TEST 2

    it('should be not register a new user', async () => {
        await registerUserService.execute({
            name: 'tiago',
            email: 'tiago@gmail.com',
            password: 'penadepato',
        });

        await expect(
            registerUserService.execute({
                name: 'tiago',
                email: 'tiago@gmail.com',
                password: 'penadepato',
            })
        ).rejects.toBeInstanceOf(Error);
    });
});
