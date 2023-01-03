import {Expose, Type} from "class-transformer";
import {UserRegistrationDto} from "../user-registration.dto";

export class RegistrationRequestDto{

    @Expose()
    @Type(() => UserRegistrationDto)
    user?: UserRegistrationDto;
}
