import {Expose} from "class-transformer";

export class EventReducedDto {

    @Expose()
    id?: number;

    @Expose()
    name?: string;

    @Expose()
    nbGuest?: number;

    @Expose()
    maxGuest?: number;

    @Expose()
    statut?: string;

}
