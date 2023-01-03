import {BackService} from "./back-service";
import {UserRegistrationDto} from "../src/shared/dtos/user-registration.dto";
import {RegistrationRequestDto} from "../src/shared/dtos/request/registration-request.dto";

export class RegisterService extends BackService {

    static readonly USER_REGISTRATION_PATH = "/api/public/register"

    constructor() {
        super();
    }

    async registerUser (user: RegistrationRequestDto) {
        const response = await this.doPostBackRequest(RegisterService.USER_REGISTRATION_PATH, user)
    }
}
