import {Fetcher} from "swr";
import {User} from "../../core/interfaces/User";
import axios from "axios";

export const GET_ACTUAL_USER = "http://localhost:3000/api/user/information";

export const fetcher : Fetcher<User> = (url:string) => axios.get(url).then(res => res.data);
