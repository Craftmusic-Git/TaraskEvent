import { NextApiRequest, NextApiResponse } from 'next'
import { getSession } from 'next-auth/react'
import { EventService } from '../../../services/event-service'

const handler = async (req: NextApiRequest, res: NextApiResponse) => {
  const session = await getSession({req});

  // @ts-ignore
  const eventService = new EventService(session?.accessToken);

  const query = req.query;

  const { id } = query;

  if (id == undefined) {
    res.status(500);
    return;
  }

  const event = await eventService.getEvent(id);

  res.status(200).json(event);
  return;
}

export default handler;
