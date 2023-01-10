import { Fetcher } from 'swr'
import { GetEventResponseDto } from '../shared/dtos/response/get-event-response.dto'
import axios from 'axios'

export const GET_EVENT = 'http://localhost:3000/api/events/get'

export const fetcher : Fetcher<GetEventResponseDto> = (url:string) => axios.get(url).then(res => res.data);
