import { Expose } from 'class-transformer'

export class OrganisationDto {
  @Expose()
  id? : number;

  @Expose()
  day?: string;

  @Expose()
  weather?: string;

  @Expose()
  capacity?: number;

  @Expose()
  isOutside?: boolean;

  @Expose()
  date?: string;

  @Expose()
  ageLimit?: number;
}
