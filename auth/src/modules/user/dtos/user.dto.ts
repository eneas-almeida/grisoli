import { IRoleDTO } from '@modules/user/dtos/role.dto';

interface IUserDTO {
    name: string;
    email: string;
    role: IRoleDTO;
    activated: boolean;
    token?: string;
}

export { IUserDTO };
