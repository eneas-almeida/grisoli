import { Router } from 'express';

import { UserRoutes } from '@modules/user/infra/http/routes/user.routes';

const routes = (): Router => {
    const router = Router();

    // USER
    new UserRoutes().register(router);

    return router;
};

export { routes };
