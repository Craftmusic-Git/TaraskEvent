import {BackSecurizedService} from "./back-securized-service";
import {User} from "../core/interfaces/User";
import {UserDto} from "../src/shared/dtos/user.dto";

export class UserService extends BackSecurizedService {
    static readonly USER_INFO_PATH = 'api/auth/user/me'

    constructor(token: string) {
        super(token);
    }

    async getUserInfo() : Promise<User> {

        const response = await this.doGetSecurizedBackRequest<UserDto>(UserService.USER_INFO_PATH, null);

        const userData : User = response.data;
        if (userData.birthdate) {
            userData.birthdate = new Date(userData.birthdate);
        }
        return userData;
    }
}
