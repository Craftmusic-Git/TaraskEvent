import { signIn, useSession } from 'next-auth/react'
import Link from 'next/link'
import UserAvatar from "./user-avatar";

const UserNavSession = () => {

  const { data: session, status  } = useSession();

  if (status === "authenticated") {

    return (
        <>
            <UserAvatar />
        </>

    )
  }

  return (
    <>
      <Link href="/register">
        <button className="relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-purple-600 to-blue-500 group-hover:from-purple-600 group-hover:to-blue-500 hover:text-white dark:text-white focus:ring-4 focus:outline-none focus:ring-blue-300 dark:focus:ring-blue-800">
          <span className="relative px-5 py-2.5 w-32 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
            Inscription
          </span>
        </button>
      </Link>

      <button onClick={() => signIn("keycloak", {callbackUrl: 'http://localhost:3000/'})} className="relative inline-flex items-center justify-center p-0.5 mb-2 mr-2 overflow-hidden text-sm font-medium text-gray-900 rounded-lg group bg-gradient-to-br from-cyan-500 to-blue-500 group-hover:from-cyan-500 group-hover:to-blue-500 hover:text-white dark:text-white focus:ring-4 focus:outline-none focus:ring-cyan-200 dark:focus:ring-cyan-800">
        <span className="relative px-5 py-2.5 w-32 transition-all ease-in duration-75 bg-white dark:bg-gray-900 rounded-md group-hover:bg-opacity-0">
          Connexion
        </span>
      </button>
    </>
  )
}

export default UserNavSession;
