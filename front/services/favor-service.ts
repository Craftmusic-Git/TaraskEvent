import { BackSecurizedService } from './back-securized-service'
import { FavorDto } from '../src/shared/dtos/favor.dto'
import { FavorCreateRequestDto } from '../src/shared/dtos/request/favor-create-request.dto'
import { AxiosResponse } from 'axios'

export class FavorService extends BackSecurizedService {
  static readonly DELETE_FAVOR = "/api/auth/favor";

  static readonly CREATE_FAVOR = "/api/auth/favor"

  constructor (token: string) {
    super(token);
  }

  createFavor = async (creationCommand: FavorCreateRequestDto) : Promise<AxiosResponse<FavorDto>> => {
    const rep = await this.doPostSecurizedBackRequest<FavorDto>(FavorService.CREATE_FAVOR, creationCommand);
    return rep;
  }

  deleteFavor = async (id: any) => {
    await this.doDeleteSecurizedBackRequest(FavorService.DELETE_FAVOR+"?id="+id, null);
  }
}
