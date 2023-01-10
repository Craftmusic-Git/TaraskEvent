import { Expose } from 'class-transformer'

export class UpdateDto {
  @Expose()
  property?: string;

  @Expose()
  information?: string | number | boolean;
}
