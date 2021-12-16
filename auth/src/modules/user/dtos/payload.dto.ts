import { IRoleDTO } from '@modules/user/dtos/role.dto';

interface IPayloadDTO {
    user: {
        user_token_id: string;
        user_token_role: IRoleDTO;
        user_token_activated: boolean;
    };
}

export { IPayloadDTO };
