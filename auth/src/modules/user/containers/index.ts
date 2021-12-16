import { container } from 'tsyringe';

import '@modules/user/providers';

import { IUserRepository } from '@modules/user/repositories/user.repository';
import { UserRepositoryPrisma } from '../infra/prisma/repositories/user.repository.prisma';

container.registerSingleton<IUserRepository>('UserRepository', UserRepositoryPrisma);
