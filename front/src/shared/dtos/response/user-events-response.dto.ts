import {Expose, Type} from "class-transformer";
import {EventReducedDto} from "../event-reduced.dto";

export class UserEventsResponseDto {

    @Expose()
    @Type(() => EventReducedDto)
    eventsOrganized?: EventReducedDto[];

    @Expose()
    @Type(() => EventReducedDto)
    events?: EventReducedDto[];

}
