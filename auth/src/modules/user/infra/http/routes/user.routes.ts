import { Router } from 'express';

import { AuthenticateUserMiddleware } from '@modules/user/use.cases/authenticate.user/authenticate.user.middleware';
import { RegisterUserMiddleware } from '@modules/user/use.cases/register.user/register.user.middleware';

class UserRoutes {
    constructor() {
        console.log(`UserRoutes instance!`);
    }

    register(router: Router) {
        new AuthenticateUserMiddleware().register(router, 'post', '/login');

        new RegisterUserMiddleware().register(router, 'post', '/register');
    }
}

export { UserRoutes };
