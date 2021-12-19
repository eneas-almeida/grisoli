import { IRoleDTO } from '@modules/user/dtos/role.dto';

interface IPayloadDTO {
    id: string;
    role: IRoleDTO;
    activated: boolean;
}

export { IPayloadDTO };
