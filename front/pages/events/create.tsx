import Layout from "../../src/shared/components/layout";
import Head from "next/head";
import {Button, Card} from "flowbite-react";
import {router} from "next/client";
import {EventReducedDto} from "../../src/shared/dtos/event-reduced.dto";
import axios from "axios";

export const CREATE_EVENT = "http://localhost:3000/api/events/create"

const Create =  () => {

    const redirectSubmit = async (e: {
        target: any;
        preventDefault: () => void; }) => {
        e.preventDefault();

        let event = new EventReducedDto();
        event.name = e.target.name.value;

        const rep : EventReducedDto = await axios.post(CREATE_EVENT, event);

        router.push("/events");
    }

    return (
        <Layout>
            <Head>
                <title>Création</title>
                <meta name="description" content="Tarask-event"/>
                <meta name="viewport" content="width=device-width, initial-scale=1"/>
                <link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon.png"/>
                <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png"/>
                <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png"/>
                <link rel="manifest" href="/site.webmanifest"/>
                <script src="/darkmode.js"></script>
            </Head>
            <main className={"max-w-screen-xl px-4 py-3 mx-auto md:px-6"}>
                <div className="w-full mt-8">
                    <Card>
                        <h2 className="mb-4 text-3xl font-extrabold tracking-tight leading-none text-gray-900 md:text-4xl dark:text-white">Créer un événement</h2>
                        <form className="flex flex-col gap-4" onSubmit={redirectSubmit}>
                            <div className="mb-6">
                                <label htmlFor="name"
                                       className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Nom de l'événement</label>
                                <input type="text" id="name"
                                       className="block w-full p-4 text-gray-900 border border-gray-300 rounded-lg bg-gray-50 sm:text-md focus:ring-blue-500 focus:border-blue-500 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"/>
                            </div>
                            <Button type="submit" className="w-64 justify-center" gradientDuoTone="pinkToOrange">
                                <span className="font-bold text-lg" >Créer un événement</span>
                                <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                                     xmlns="http://www.w3.org/2000/svg">
                                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                          d="M13 5l7 7-7 7M5 5l7 7-7 7"></path>
                                </svg>
                            </Button>
                        </form>
                    </Card>
                </div>
            </main>
        </Layout>
    )
}

export default Create;
