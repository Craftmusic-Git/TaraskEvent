import { NextApiRequest, NextApiResponse } from 'next'
import { getSession } from 'next-auth/react'
import { FavorService } from '../../../services/favor-service'
import { EventService } from '../../../services/event-service'

const handler = async (req: NextApiRequest, res: NextApiResponse) => {
  const session = await getSession({ req });

  // @ts-ignore
  const eventService = new EventService(session?.accessToken);

  const query = req.query;

  const { id } = query;

  console.log(req.body);

  if (id == undefined) {
    res.status(500);
    return;
  }

  const rep = eventService.updateEventOrganisation(id, req.body);

  res.status(200).json(rep);
  return;

}

export default handler;
