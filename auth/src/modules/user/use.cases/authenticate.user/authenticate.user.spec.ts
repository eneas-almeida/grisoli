import { UserRepositoryInMemory } from '@modules/user/repositories/in.memory/user.repository.in.memory';
import { ITokenProvider } from '@modules/user/providers/token.provider/models/token-provider';
import { IHashProvider } from '@modules/user/providers/hash.provider/models/hash.provider';
import { AuthenticateUserService } from './authenticate.user.service';
import { TokenProviderInMemory } from '@modules/user/providers/token.provider/in.memory/token.provider.in.memory';
import { HashProviderInMemory } from '@modules/user/providers/hash.provider/in.memory/hash.provider.in.memory';
import { AppException } from '@shared/exceptions/app.exception';

let userRepositoryInMemory: UserRepositoryInMemory;
let tokenProviderInMemory: ITokenProvider;
let hashProviderInMemory: IHashProvider;
let authenticateUserService: AuthenticateUserService;

describe('AuthenticateUserService', () => {
    beforeEach(() => {
        userRepositoryInMemory = new UserRepositoryInMemory();
        tokenProviderInMemory = new TokenProviderInMemory();
        hashProviderInMemory = new HashProviderInMemory();

        authenticateUserService = new AuthenticateUserService(userRepositoryInMemory, tokenProviderInMemory, hashProviderInMemory);
    });

    // TEST 1

    it('should be authenticate', async () => {
        const compareHash = jest.spyOn(hashProviderInMemory, 'compareHash');
        const generateToken = jest.spyOn(tokenProviderInMemory, 'generate');

        await userRepositoryInMemory.create({
            name: 'Tiago',
            email: 'tiago@gmail.com',
            password: 'tiago',
        });

        const user = await authenticateUserService.execute({
            email: 'tiago@gmail.com',
            password: 'tiago',
        });

        const { id, role, activated } = user;

        expect(compareHash).toHaveBeenCalledWith('tiago', 'tiago');
        expect(generateToken).toHaveBeenCalledWith({
            id,
            role,
            activated,
        });
    });

    // TEST 2

    it('should not be authenticate', async () => {
        await expect(
            authenticateUserService.execute({
                email: 'tiago@gmail.com',
                password: '00x00x00',
            })
        ).rejects.toBeInstanceOf(AppException);
    });

    // TESTE 3

    it('should not be authenticate', async () => {
        await userRepositoryInMemory.create({
            name: 'Tiago',
            email: 'tiago@gmail.com',
            password: 'tiago',
        });

        await expect(
            authenticateUserService.execute({
                email: 'tiago@gmail.com',
                password: '00x00x00',
            })
        ).rejects.toBeInstanceOf(AppException);
    });
});
