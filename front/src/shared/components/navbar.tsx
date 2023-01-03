import Logo from './logo'
import Link from 'next/link'
import UserNavSession from './userNavSession'
import {signOut} from "next-auth/react";

const Navbar = () => {

  return (
    <div>
      <nav className="bg-white border-gray-200 dark:bg-gray-900">
        <div className="flex flex-wrap justify-between items-center mx-auto max-w-screen-xl px-4 md:px-6 py-2.5">
          <div>
            <img
                src="/android-chrome-512x512.png"
                className="inline mb-2 mr-3 h-6 sm:h-9 "
                alt="Tarask-Event Logo"
            />
            <Link href={"/"}>
              <Logo />
            </Link>
          </div>
          <div className="flex items-center">
            <button id="theme-toggle" type="button" className="inline-flex items-center p-0.5 mb-2 mr-2 text-gray-500 dark:text-gray-400 hover:bg-gray-100 dark:hover:bg-gray-700 focus:outline-none focus:ring-4 focus:ring-gray-200 dark:focus:ring-gray-700 rounded-lg text-sm p-2.5">
              <svg id="theme-toggle-dark-icon" className="hidden w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                <path d="M17.293 13.293A8 8 0 016.707 2.707a8.001 8.001 0 1010.586 10.586z"></path>
              </svg>
              <svg id="theme-toggle-light-icon" className="hidden w-5 h-5" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg">
                <path d="M10 2a1 1 0 011 1v1a1 1 0 11-2 0V3a1 1 0 011-1zm4 8a4 4 0 11-8 0 4 4 0 018 0zm-.464 4.95l.707.707a1 1 0 001.414-1.414l-.707-.707a1 1 0 00-1.414 1.414zm2.12-10.607a1 1 0 010 1.414l-.706.707a1 1 0 11-1.414-1.414l.707-.707a1 1 0 011.414 0zM17 11a1 1 0 100-2h-1a1 1 0 100 2h1zm-7 4a1 1 0 011 1v1a1 1 0 11-2 0v-1a1 1 0 011-1zM5.05 6.464A1 1 0 106.465 5.05l-.708-.707a1 1 0 00-1.414 1.414l.707.707zm1.414 8.486l-.707.707a1 1 0 01-1.414-1.414l.707-.707a1 1 0 011.414 1.414zM4 11a1 1 0 100-2H3a1 1 0 000 2h1z" fillRule="evenodd" clipRule="evenodd"></path>
              </svg>
            </button>
            <UserNavSession />
          </div>
        </div>
      </nav>
      <nav className={"bg-gray-50 dark:bg-gray-700"}>
        <div className="max-w-screen-xl px-4 py-3 mx-auto md:px-6">
          <div className="flex items-center">
            <ul className="flex flex-row mt-0 mr-6 space-x-8 text-sm font-medium">
              <li>
                <Link href="/" className="text-gray-900 dark:text-white font-semibold hover:underline" aria-current="page">Home</Link>
              </li>
              <li>
                <Link href="/events" className="text-gray-900 dark:text-white font-semibold hover:underline">Événements</Link>
              </li>
              <li>
                <Link href="/tips" className="text-gray-900 dark:text-white font-semibold hover:underline">Services</Link>
              </li>
              <li>
                <Link href="/organisation" className="text-gray-900 dark:text-white font-semibold hover:underline">Organisation</Link>
              </li>
            </ul>
          </div>
        </div>
      </nav>
    </div>
  )
}

export default Navbar;
