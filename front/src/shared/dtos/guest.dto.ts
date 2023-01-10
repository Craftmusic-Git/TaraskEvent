import { Expose, Type } from 'class-transformer'
import { FavorDto } from './favor.dto'

export class GuestDto {

  @Expose()
  id?: number

  @Expose()
  username?: string;

  @Expose()
  name?: string;

  @Expose()
  lastname?:string;

  @Expose()
  birthdate?: string;

  @Expose()
  @Type(() => FavorDto)
  favors?: FavorDto[];
}
