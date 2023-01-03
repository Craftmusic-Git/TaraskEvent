import {Expose, Type} from "class-transformer";
import {EventReducedDto} from "../event-reduced.dto";

export class CreateEventRequestDto {
    @Expose()
    @Type(() => EventReducedDto)
    event?: EventReducedDto;
}
