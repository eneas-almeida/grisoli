import { IRoleDTO } from '@modules/user/dtos/role.dto';

interface ICreatePayloadDTO {
    id: string;
    role: IRoleDTO;
    activated: boolean;
}

export { ICreatePayloadDTO };
