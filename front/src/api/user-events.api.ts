import {Fetcher} from "swr";
import axios from "axios";
import {UserEventsResponseDto} from "../shared/dtos/response/user-events-response.dto";

export const GET_EVENTS_OF_USER = "http://localhost:3000/api/events/user-event";

export const fetcher : Fetcher<UserEventsResponseDto> = (url:string) => axios.get(url).then(res => res.data);
