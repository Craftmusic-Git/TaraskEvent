import {Alert, Card} from "flowbite-react";
import {HiInformationCircle} from "react-icons/all";
import { className } from 'postcss-selector-parser'

type EventBaselineProp = {
    statut?: string;
}

const EventStatutBaseline = ({statut} : EventBaselineProp) => {

    const OrganisationCell = (lvl : number) => {

        let iconBgClassName = "z-10 flex items-center justify-center w-6 h-6 rounded-full ring-0 ring-white sm:ring-8 dark:ring-gray-900 shrink-0"
        let iconClassName = 'w-4 h-4'

        if (lvl >= 1) {
            iconBgClassName += ' dark:bg-blue-900'
            iconClassName += ' text-blue-600 dark:text-blue-300'
        }
        else {
            iconBgClassName += ' dark:bg-gray-500'
            iconClassName += ' text-gray-400 dark:text-gray-400'
        }

        return (
          <li className="relative mb-6 sm:mb-0">
              <div className="flex items-center">
                  <div className= {iconBgClassName}>
                      <svg className={iconClassName} fill="none" stroke="currentColor" viewBox="0 0 24 24"
                           xmlns="http://www.w3.org/2000/svg">
                          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                                d="M10.325 4.317c.426-1.756 2.924-1.756 3.35 0a1.724 1.724 0 002.573 1.066c1.543-.94 3.31.826 2.37 2.37a1.724 1.724 0 001.065 2.572c1.756.426 1.756 2.924 0 3.35a1.724 1.724 0 00-1.066 2.573c.94 1.543-.826 3.31-2.37 2.37a1.724 1.724 0 00-2.572 1.065c-.426 1.756-2.924 1.756-3.35 0a1.724 1.724 0 00-2.573-1.066c-1.543.94-3.31-.826-2.37-2.37a1.724 1.724 0 00-1.065-2.572c-1.756-.426-1.756-2.924 0-3.35a1.724 1.724 0 001.066-2.573c-.94-1.543.826-3.31 2.37-2.37.996.608 2.296.07 2.572-1.065z"
                          ></path>
                          <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                                d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"
                          ></path>
                      </svg>
                  </div>
                  <div className="hidden sm:flex w-full bg-gray-200 h-0.5 dark:bg-gray-700"></div>
              </div>
              <div className="mt-3 sm:pr-8">
                  <h3 className="text-lg font-semibold text-gray-900 dark:text-white">Configuration</h3>
                  <time className="block mb-2 text-sm font-normal leading-none text-gray-400 dark:text-gray-500">Organisation de l'événement
                  </time>
                  <p className="text-base font-normal text-gray-500 dark:text-gray-400"> Configuration des paramêtres globaux de l'événement</p>
              </div>
          </li>
        )
    }

    const WaitingCell = (lvl : number) => {
        let iconBgClassName = "z-10 flex items-center justify-center w-6 h-6 rounded-full ring-0 ring-white sm:ring-8 dark:ring-gray-900 shrink-0"
        let iconClassName = 'w-4 h-4'

        if (lvl >= 2) {
            iconBgClassName += ' dark:bg-blue-900'
            iconClassName += ' text-blue-600 dark:text-blue-300'
        }
        else {
            iconBgClassName += ' dark:bg-gray-500'
            iconClassName += ' text-gray-400 dark:text-gray-400'
        }

        return (
          <li className="relative mb-6 sm:mb-0">
              <div className="flex items-center">
                  <div className= {iconBgClassName}>
                      <svg className={iconClassName} fill="none" stroke="currentColor" viewBox="0 0 24 24"
                           xmlns="http://www.w3.org/2000/svg"> <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                                                                     d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                      </svg>
                  </div>
                  <div className="hidden sm:flex w-full bg-gray-200 h-0.5 dark:bg-gray-700"></div>
              </div>
              <div className="mt-3 sm:pr-8">
                  <h3 className="text-lg font-semibold text-gray-900 dark:text-white">Attente</h3>
                  <time className="block mb-2 text-sm font-normal leading-none text-gray-400 dark:text-gray-500">Mise en place des services
                  </time>
                  <p className="text-base font-normal text-gray-500 dark:text-gray-400"> Configurations/distributions et complétion des services</p>
              </div>
          </li>
        )
    }

    const ReadyCell = (lvl : number) => {
        let iconBgClassName = "z-10 flex items-center justify-center w-6 h-6 rounded-full ring-0 ring-white sm:ring-8 dark:ring-gray-900 shrink-0"
        let iconClassName = 'w-4 h-4'

        if (lvl >= 3) {
            iconBgClassName += ' dark:bg-blue-900'
            iconClassName += ' text-blue-600 dark:text-blue-300'
        }
        else {
            iconBgClassName += ' dark:bg-gray-500'
            iconClassName += ' text-gray-400 dark:text-gray-400'
        }

        return (
          <li className="relative mb-6 sm:mb-0">
              <div className="flex items-center">
                  <div className= {iconBgClassName}>
                      <svg className={iconClassName} fill="none" stroke="currentColor" viewBox="0 0 24 24"
                           xmlns="http://www.w3.org/2000/svg"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M5 13l4 4L19 7"></path>
                      </svg>
                  </div>
                  <div className="hidden sm:flex w-full bg-gray-200 h-0.5 dark:bg-gray-700"></div>
              </div>
              <div className="mt-3 sm:pr-8">
                  <h3 className="text-lg font-semibold text-gray-900 dark:text-white">Prêt</h3>
                  <time className="block mb-2 text-sm font-normal leading-none text-gray-400 dark:text-gray-500">l'événement est prêt
                  </time>
                  <p className="text-base font-normal text-gray-500 dark:text-gray-400"> Il ne reste plus qu'à lancer l'événement</p>
              </div>
          </li>
        )
    }

    const InProgressCell = (lvl : number) => {
        let iconBgClassName = "z-10 flex items-center justify-center w-6 h-6 rounded-full ring-0 ring-white sm:ring-8 dark:ring-gray-900 shrink-0"
        let iconClassName = 'w-4 h-4'

        if (lvl >= 3) {
            iconBgClassName += ' dark:bg-blue-900'
            iconClassName += ' text-blue-600 dark:text-blue-300'
        }
        else {
            iconBgClassName += ' dark:bg-gray-500'
            iconClassName += ' text-gray-400 dark:text-gray-400'
        }

        return (
          <li className="relative mb-6 sm:mb-0">
              <div className="flex items-center">
                  <div className= {iconBgClassName}>
                      <svg className={iconClassName} fill="none" stroke="currentColor" viewBox="0 0 24 24"
                           xmlns="http://www.w3.org/2000/svg"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                                                                    d="M9 12l2 2 4-4M7.835 4.697a3.42 3.42 0 001.946-.806 3.42 3.42 0 014.438 0 3.42 3.42 0 001.946.806 3.42 3.42 0 013.138 3.138 3.42 3.42 0 00.806 1.946 3.42 3.42 0 010 4.438 3.42 3.42 0 00-.806 1.946 3.42 3.42 0 01-3.138 3.138 3.42 3.42 0 00-1.946.806 3.42 3.42 0 01-4.438 0 3.42 3.42 0 00-1.946-.806 3.42 3.42 0 01-3.138-3.138 3.42 3.42 0 00-.806-1.946 3.42 3.42 0 010-4.438 3.42 3.42 0 00.806-1.946 3.42 3.42 0 013.138-3.138z"></path></svg>
                  </div>
                  <div className="hidden sm:flex w-full bg-gray-200 h-0.5 dark:bg-gray-700"></div>
              </div>
              <div className="mt-3 sm:pr-8">
                  <h3 className="text-lg font-semibold text-gray-900 dark:text-white">En cours</h3>
                  <time className="block mb-2 text-sm font-normal leading-none text-gray-400 dark:text-gray-500">l'événement à lieu en ce moment
                  </time>
                  <p className="text-base font-normal text-gray-500 dark:text-gray-400">Profitez de l'événement</p>
              </div>
          </li>
        )
    }

    if (statut == "CONFIGURATION" || statut == "WAITING" || statut == "READY" || statut == "IN_PROGRESS") {
        let lvl : number = 0;

        if (statut == "CONFIGURATION")
            lvl = 1;
        if (statut == "WAITING")
            lvl = 2;
        if (statut == "READY")
            lvl = 3;
        if (statut == "IN_PROGRESS")
            lvl = 4;

        return (
            <ol className="items-center sm:flex">
                {OrganisationCell(lvl)}
                {WaitingCell(lvl)}
                {ReadyCell(lvl)}
                {InProgressCell(lvl)}
            </ol>
        )
    }

    return (
        <Alert color="failure">
                <span>
                    <span className="font-medium">
                        Alerte !
                    </span>
                    {' '}Le statut de l'évenement est indéterminable.
                </span>
        </Alert>
    )
}

export default EventStatutBaseline;
