import { Expose } from 'class-transformer'

export class FavorDto {

  @Expose()
  id?: number;

  @Expose()
  title?: string;

  @Expose()
  description?: string;

  @Expose()
  progress?: number;
}
