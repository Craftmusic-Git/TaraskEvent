import {Expose} from "class-transformer";

export class UserRegistrationDto {

    @Expose()
    username?: string;

    @Expose()
    name?: string;

    @Expose()
    lastName?: string;

    @Expose()
    birthDate?: number;

    @Expose()
    password?: string;

}
