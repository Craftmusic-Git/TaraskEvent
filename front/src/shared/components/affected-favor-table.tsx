import { GuestDto } from '../dtos/guest.dto'
import { Button } from 'flowbite-react'
import React from 'react'
import { FavorDto } from '../dtos/favor.dto'

type AffectedFavorTableProps = {
  users?: GuestDto[]
}

const firstLetters = (user : GuestDto) : string => {
  if (user.name && user.lastname) {
    return user.name.charAt(0).concat(user.lastname.charAt(0)).toUpperCase();
  } else {
    return 'XX';
  }
}

const removeUser = (user: GuestDto) => {

}

const removeFavorFromUser = (user: GuestDto, favor: FavorDto) => {

}

const progressCell = (favor: FavorDto) => {

  const progressStyle = 'witdh: '+favor.progress+'%';

  return (
      <div className="w-full bg-gray-200 rounded-full dark:bg-gray-700">
        <div className='bg-blue-600 text-xs font-medium text-blue-100 text-center p-0.5 leading-none rounded-full w-["+{favor.progress}+"]' > {favor.progress}%
        </div>
      </div>
    )
}

const AffectedFavorTable = ({ users } : AffectedFavorTableProps) => {
  return (
    <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
      <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
        <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
        <tr>
          <th scope="col" className="p-4">
            Îcone
          </th>
          <th scope="col" className="px-6 py-3">
            Name
          </th>
          <th scope="col" className="px-6 py-3">
            Service
          </th>
          <th scope="col" className="px-6 py-3">
            Status
          </th>
          <th scope="col" className="px-6 py-3">
            Action
          </th>
        </tr>
        </thead>
        <tbody>
        {
          users?.map(user => (
            <>
              <tr key={user.id}>
                <td rowSpan={user.favors?.length ? user.favors?.length + 1 : 1}>
                  <div className="inline-flex mb-2 overflow-hidden relative justify-center items-center w-10 h-10 bg-gray-100 rounded-full dark:bg-gray-600">
                    <span className="font-medium text-gray-600 dark:text-gray-300">{firstLetters(user)}</span>
                  </div>
                </td>
                <td>
                  {user.username}
                </td>
                <td>

                </td>
                <td>

                </td>
                <td>
                  <Button onClick={() => {removeUser(user)}} color={'failure'}>
                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                         stroke="currentColor" className="w-6 h-6">
                      <path stroke-linecap="round" stroke-linejoin="round"
                            d="M15.75 9V5.25A2.25 2.25 0 0013.5 3h-6a2.25 2.25 0 00-2.25 2.25v13.5A2.25 2.25 0 007.5 21h6a2.25 2.25 0 002.25-2.25V15M12 9l-3 3m0 0l3 3m-3-3h12.75"/>
                    </svg>
                    Bannir
                  </Button>
                </td>
              </tr>
              {user.favors?.map(favor => (
                <tr key={favor.id}>
                  <td>

                  </td>
                  <td>
                    {favor.title}
                  </td>
                  <td>
                    {progressCell(favor)}
                  </td>
                  <td>
                    <Button onClick={() => {removeFavorFromUser(user, favor)}} color={'warning'}>
                      <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                           stroke="currentColor" className="w-6 h-6">
                        <path stroke-linecap="round" stroke-linejoin="round"
                              d="M9.75 9.75l4.5 4.5m0-4.5l-4.5 4.5M21 12a9 9 0 11-18 0 9 9 0 0118 0z"/>
                      </svg>
                      Retirer
                    </Button>
                  </td>
                </tr>
              ))}
            </>
          ))
        }
        </tbody>
      </table>
    </div>
  )
}

export default AffectedFavorTable;
