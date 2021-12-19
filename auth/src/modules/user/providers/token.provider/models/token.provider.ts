import { ICreatePayloadDTO } from '@modules/user/dtos/create.payload.dto';
import { IPayloadDTO } from '@modules/user/dtos/payload.dto';

interface ITokenProvider {
    generate(data: ICreatePayloadDTO): Promise<string>;

    validate(token: string): IPayloadDTO;
}

export { ITokenProvider };
