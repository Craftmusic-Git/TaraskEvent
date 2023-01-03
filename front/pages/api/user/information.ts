import {NextApiRequest, NextApiResponse} from "next";

import {UserService} from "../../../services/user-service";
import {getSession} from "next-auth/react";

const handler = async (req: NextApiRequest, res: NextApiResponse) => {

    const session = await getSession({req});

    // @ts-ignore
    const userService = new UserService(session?.accessToken);

    const promiseUser = await userService.getUserInfo();

    res.status(200).json(promiseUser);
    return
}

export default handler;
