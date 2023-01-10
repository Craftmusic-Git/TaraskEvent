import {BackSecurizedService} from "./back-securized-service";
import {UserEventsResponseDto} from "../src/shared/dtos/response/user-events-response.dto";
import {EventReducedDto} from "../src/shared/dtos/event-reduced.dto";
import {CreateEventRequestDto} from "../src/shared/dtos/request/create-event-request.dto";
import {CreateEventResponseDto} from "../src/shared/dtos/response/create-event-response.dto";
import { GetEventResponseDto } from '../src/shared/dtos/response/get-event-response.dto'
import { UpdateDto } from '../src/shared/dtos/update.dto'

export class EventService extends BackSecurizedService {

    static readonly GET_ALL_EVENTS_OF_USER = "/api/auth/event/me"

    static readonly EVENT = "/api/auth/event"

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

        const response = await this.doPostSecurizedBackRequest<CreateEventResponseDto>(EventService.EVENT, request);

        return response.data;
    }

    getEvent = async (id: any) : Promise<GetEventResponseDto> => {
        const response = await this.doGetSecurizedBackRequest<GetEventResponseDto>(EventService.EVENT+'?id='+id, null);

        return response.data;
    }

    updateEventOrganisation = async (id: any, update: UpdateDto) : Promise<GetEventResponseDto> => {
        if (update.property == "EXTERN") {
            update.information = !!update.information;
        }

        const response = await this.doPatchSecurizedBackRequest<GetEventResponseDto>(EventService.EVENT+'?id='+id, update);

        return response.data;
    }

}
