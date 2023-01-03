import {Expose} from "class-transformer";
import {AbstractRequestDto} from "./abstract/abstract-request.dto";

export class UserDto{
    @Expose()
    id?: number;

    @Expose()
    username?: string;

    @Expose()
    name?: string;

    @Expose()
    lastname?: string;

    @Expose()
    birthdate?: string;

}
