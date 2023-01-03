import {NextApiRequest, NextApiResponse} from "next";
import {getSession} from "next-auth/react";
import {EventService} from "../../../services/event-service";

const handler = async (req: NextApiRequest, res: NextApiResponse) => {

    const session = await getSession({req});

    // @ts-ignore
    const eventService = new EventService(session?.accessToken);

    const events = await eventService.getAllEvent();

    res.status(200).json(events);
    return
}

export default handler;
