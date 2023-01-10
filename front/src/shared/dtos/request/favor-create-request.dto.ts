import { Expose, Type } from 'class-transformer'
import { FavorDto } from '../favor.dto'

export class FavorCreateRequestDto {
  @Expose()
  eventId?: number;

  @Expose()
  @Type(() => FavorDto)
  favor?: FavorDto;
}
