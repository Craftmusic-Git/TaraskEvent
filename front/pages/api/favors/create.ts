import { NextApiRequest, NextApiResponse } from 'next'
import { getSession } from 'next-auth/react'
import { FavorService } from '../../../services/favor-service'

const handler = async (req: NextApiRequest, res: NextApiResponse) => {
  const session = await getSession({req});

  // @ts-ignore
  const favorService = new FavorService(session?.accessToken)

  const favor = await favorService.createFavor(req.body);

  res.status(200).json(favor.data)
}
export default handler
