import {NextApiRequest, NextApiResponse} from "next";
import {getSession} from "next-auth/react";
import {EventService} from "../../../services/event-service";
import {EventReducedDto} from "../../../src/shared/dtos/event-reduced.dto";

const handler = async (req: NextApiRequest, res: NextApiResponse) => {
    const session = await getSession({req});

    // @ts-ignore
    const eventService = new EventService(session?.accessToken);

    const event = new EventReducedDto();

    event.name = req.body.name;

    const rep = await eventService.createEvent(event);

    res.status(200).json(rep);
    return;
}

export default handler
