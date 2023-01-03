import Layout from "../src/shared/components/layout";
import Head from "next/head";
import useSWR from "swr";
import {fetcher, GET_EVENTS_OF_USER} from "../src/api/user-events.api";
import EventReducedTable from "../src/shared/components/event-reduced-table";
import {signIn, useSession} from "next-auth/react";
import UserAvatar from "../src/shared/components/user-avatar";
import { Button } from "flowbite-react";
import {HiOutlineArrowRight} from "react-icons/all";
import Link from "next/link";

const Events = () => {

    const { data: session, status  } = useSession();

    const { data, error, isLoading } = useSWR(GET_EVENTS_OF_USER, fetcher);

    if (status === "unauthenticated") {
        signIn("keycloak", {callbackUrl: 'http://localhost:3000/events'})
    }

    if (data) {
        return (
            <Layout>
                <Head>
                    <title>Evenements</title>
                    <meta name="description" content="Tarask-event"/>
                    <meta name="viewport" content="width=device-width, initial-scale=1"/>
                    <link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon.png"/>
                    <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png"/>
                    <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png"/>
                    <link rel="manifest" href="/site.webmanifest"/>
                    <script src="/darkmode.js"></script>
                </Head>
                <main className={"max-w-screen-xl px-4 py-3 mx-auto md:px-6"}>
                    <div className={"mt-4 mb-8"}>
                        <span className={"text-xl font-bold dark:text-white"}>Événements organisés</span>
                        <div className={"mt-4"}>
                            <EventReducedTable events={data.eventsOrganized} organizer={true}/>
                        </div>
                        <Link href="/events/create">
                            <div className={"mt-2 ml-6"}>
                                <Button outline={true} gradientDuoTone="pinkToOrange">
                                    <span className="font-bold text-lg" >Créer un événement</span>
                                    <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                                         xmlns="http://www.w3.org/2000/svg">
                                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                              d="M13 5l7 7-7 7M5 5l7 7-7 7"></path>
                                    </svg>
                                </Button>
                            </div>
                        </Link>
                    </div>
                    <div>
                        <span className={"text-xl font-bold dark:text-white"}>Événements en tant qu'invités</span>
                        <div className={"mt-4"}>
                            <EventReducedTable events={data.eventsOrganized}/>
                        </div>
                    </div>
                </main>
            </Layout>
        )
    }
    if (isLoading) {
        return (
            <Layout>
                <Head>
                    <title>Evenements</title>
                    <meta name="description" content="Tarask-event"/>
                    <meta name="viewport" content="width=device-width, initial-scale=1"/>
                    <link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon.png"/>
                    <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png"/>
                    <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png"/>
                    <link rel="manifest" href="/site.webmanifest"/>
                    <script src="/darkmode.js"></script>
                </Head>
                <main className={"max-w-screen-xl px-4 py-3 mx-auto md:px-6"}>
                    <div>
                        <span className={"text-xl font-bold dark:text-white"}>Événements organisés</span>
                        <span className={"text-lg font-bold dark:text-white"}> Chargement . . . </span>
                    </div>
                    <div>
                        <span className={"text-xl font-bold dark:text-white"}>Événements en tant qu'invités</span>
                        <span className={"text-lg font-bold dark:text-white"}> Chargement . . . </span>
                    </div>
                </main>
            </Layout>
        )
    }
}

export default Events;
