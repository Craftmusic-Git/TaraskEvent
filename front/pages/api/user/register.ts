import {NextApiRequest, NextApiResponse} from "next";
import {RegisterService} from "../../../services/register-service";
import {UserRegistrationDto} from "../../../src/shared/dtos/user-registration.dto";
import {RegistrationRequestDto} from "../../../src/shared/dtos/request/registration-request.dto";

const handler = async (req: NextApiRequest, res: NextApiResponse) => {

    const body = req.body;

    if (!body) {
        return res.status(500).json({msg: 'User not found'});
    }

    const registerService = new RegisterService();

    let user = new RegistrationRequestDto();
    user.user = body;
    const rep = await registerService.registerUser(user);

    return res.status(200);
}

export default handler;
