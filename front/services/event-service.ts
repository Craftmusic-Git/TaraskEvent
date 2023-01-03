import {BackSecurizedService} from "./back-securized-service";
import {UserEventsResponseDto} from "../src/shared/dtos/response/user-events-response.dto";
import {EventReducedDto} from "../src/shared/dtos/event-reduced.dto";
import {CreateEventRequestDto} from "../src/shared/dtos/request/create-event-request.dto";
import {CreateEventResponseDto} from "../src/shared/dtos/response/create-event-response.dto";

export class EventService extends BackSecurizedService {

    static readonly GET_ALL_EVENTS_OF_USER = "/api/auth/event/me"

    static readonly CRETE_EVENT = "/api/auth/event"

    constructor(token: string) {
        super(token);
    }

    getAllEvent = async () : Promise<UserEventsResponseDto> => {
        const response = await this.doGetSecurizedBackRequest<UserEventsResponseDto>(EventService.GET_ALL_EVENTS_OF_USER, null);

        return response.data;
    }

    createEvent = async (event: EventReducedDto) => {
        const request = new CreateEventRequestDto();
        request.event = event;

        const response = await this.doPostSecurizedBackRequest<CreateEventResponseDto>(EventService.CRETE_EVENT, request);

        return response.data;
    }

}
