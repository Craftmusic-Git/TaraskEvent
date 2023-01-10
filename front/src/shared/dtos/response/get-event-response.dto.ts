import { Expose, Type } from 'class-transformer'
import { OrganisationDto } from '../organisation.dto'
import { GuestDto } from '../guest.dto'
import { FavorDto } from '../favor.dto'

export class GetEventResponseDto {

  @Expose()
  id?: number;

  @Expose()
  name?: string;

  @Expose()
  statut?: string;

  @Expose()
  @Type(() => OrganisationDto)
  organisation?: OrganisationDto;

  @Expose()
  @Type(() => GuestDto)
  guests?: GuestDto[];

  @Expose()
  @Type(() => FavorDto)
  emptyFavors?: FavorDto[];
}
