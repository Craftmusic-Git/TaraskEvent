import Head from "next/head";
import Layout from "../../src/shared/components/layout";
import { Button, Card, Label, Modal, Textarea, TextInput } from 'flowbite-react'
import useSWR from 'swr'
import { fetcher, GET_EVENT } from '../../src/api/get-event.api'
import { useRouter } from 'next/router'
import EventStatutBaseline from '../../src/shared/components/event-statut-baseline'
import FavorsTable from "../../src/shared/components/favors-table";
import { useState } from 'react'
import AffectedFavorTable from '../../src/shared/components/affected-favor-table'
import { FavorDto } from '../../src/shared/dtos/favor.dto'
import axios from 'axios'
import { FavorCreateRequestDto } from '../../src/shared/dtos/request/favor-create-request.dto'
import OrganisationOption from '../../src/shared/components/organisation-option'


export const CREATE_FAVOR_PATH = "http://localhost:3000/api/favors/create"

const Option = () => {

  const router = useRouter();
  const { id } = router.query;

  const { data, error, isLoading, mutate } = useSWR(GET_EVENT + '?id=' + id, fetcher);
  const [ createModalService, setCreateModalService ] = useState<boolean>(false);

  const createFavor = async (e : {
    target: any
    preventDefault (): void
  }) => {
    e.preventDefault();

    let favor = new FavorDto();

    favor.title = e.target.title.value;
    favor.description = e.target.description.value;

    let req = new FavorCreateRequestDto();
    if (typeof id === 'string') {
      req.eventId = parseInt(id)
    }
    req.favor = favor;

    const rep = await axios.post(CREATE_FAVOR_PATH,req);
    setCreateModalService(false);
    await mutate();
  }

  if (!data) {
    return (
      <>
        <Layout>
          <Head>
            <title>Option</title>
            <meta name="description" content="Tarask-event"/>
            <meta name="viewport" content="width=device-width, initial-scale=1"/>
            <link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon.png"/>
            <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png"/>
            <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png"/>
            <link rel="manifest" href="/site.webmanifest"/>
            {/* eslint-disable-next-line @next/next/no-sync-scripts */}
            <script src="/darkmode.js"></script>
            {/* eslint-disable-next-line @next/next/no-sync-scripts */}
            <script src="flowbite/dist/datepicker.js"></script>
          </Head>
          <main className={"max-w-screen-xl px-4 py-3 mx-auto md:px-6"}>
            <Card className="mt-8 mb-8">
              <div className="block mr-auto ml-auto m-64" role="status">
                <svg aria-hidden="true"
                     className="w-24 h-24 text-gray-200 animate-spin dark:text-gray-600 fill-blue-600"
                     viewBox="0 0 100 101" fill="none" xmlns="http://www.w3.org/2000/svg">
                  <path d="M100 50.5908C100 78.2051 77.6142 100.591 50 100.591C22.3858 100.591 0 78.2051 0 50.5908C0 22.9766 22.3858 0.59082 50 0.59082C77.6142 0.59082 100 22.9766 100 50.5908ZM9.08144 50.5908C9.08144 73.1895 27.4013 91.5094 50 91.5094C72.5987 91.5094 90.9186 73.1895 90.9186 50.5908C90.9186 27.9921 72.5987 9.67226 50 9.67226C27.4013 9.67226 9.08144 27.9921 9.08144 50.5908Z"
                    fill="currentColor"/>
                  <path d="M93.9676 39.0409C96.393 38.4038 97.8624 35.9116 97.0079 33.5539C95.2932 28.8227 92.871 24.3692 89.8167 20.348C85.8452 15.1192 80.8826 10.7238 75.2124 7.41289C69.5422 4.10194 63.2754 1.94025 56.7698 1.05124C51.7666 0.367541 46.6976 0.446843 41.7345 1.27873C39.2613 1.69328 37.813 4.19778 38.4501 6.62326C39.0873 9.04874 41.5694 10.4717 44.0505 10.1071C47.8511 9.54855 51.7191 9.52689 55.5402 10.0491C60.8642 10.7766 65.9928 12.5457 70.6331 15.2552C75.2735 17.9648 79.3347 21.5619 82.5849 25.841C84.9175 28.9121 86.7997 32.2913 88.1811 35.8758C89.083 38.2158 91.5421 39.6781 93.9676 39.0409Z"
                    fill="currentFill"/>
                </svg>
                <span className="sr-only">Loading...</span>
              </div>
            </Card>
          </main>
        </Layout>
      </>
    )
  } else {



    // @ts-ignore
    return (
      <Layout>
        <Head>
          <title>Option</title>
          <meta name="description" content="Tarask-event"/>
          <meta name="viewport" content="width=device-width, initial-scale=1"/>
          <link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon.png"/>
          <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png"/>
          <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png"/>
          <link rel="manifest" href="/site.webmanifest"/>
          {/* eslint-disable-next-line @next/next/no-sync-scripts */}
          <script src="/darkmode.js"></script>
          {/* eslint-disable-next-line @next/next/no-sync-scripts */}
          <script src="/flowbite/dist/datepicker.js"></script>
        </Head>
        <main className={"max-w-screen-xl px-4 py-3 mx-auto md:px-6"}>
          <Card className="mt-8">
            <h2 className="mb-4 text-3xl font-extrabold leading-none tracking-tight text-gray-900 md:text-4xl dark:text-white">
              <small className="ml-2 font-semibold text-gray-500 dark:text-gray-400">Événement : </small> {data?.name}
            </h2>
            <h3 className="text-3xl mt-2 mb-4 font-bold dark:text-white">Avancement :</h3>
            <EventStatutBaseline statut={data?.statut}/>
            <OrganisationOption data={data} mutate={mutate} />
            <h3 className="text-3xl mt-3 font-bold dark:text-white">Services sans affectation :</h3>
            <FavorsTable favors={data?.emptyFavors} mutate={mutate}/>
            <div>
              <Button onClick={() => {
                setCreateModalService(true)
              }} className="w-64 justify-center" gradientDuoTone="pinkToOrange"
              >
                <span className="font-bold text-lg">Créer un service</span>
                <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                     xmlns="http://www.w3.org/2000/svg"
                >
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                        d="M13 5l7 7-7 7M5 5l7 7-7 7"
                  ></path>
                </svg>
              </Button>
              <div className="mb-4"/>
              <h3 className="text-3xl mt-3 font-bold dark:text-white">Services avec manager :</h3>
              <AffectedFavorTable users={data?.guests}/>
              <Button onClick={() => mutate()}>
                Recharger
              </Button>
              <Modal show={createModalService}
                     size="md"
                     popup={true}
                     onClose={() => setCreateModalService(false)}
              >
                <Modal.Header/>
                <Modal.Body>
                  <div className="space-y-6 px-6 pb-4 sm:pb-6 lg:px-8 xl:pb-8">
                    <form onSubmit={createFavor}>
                      <h3 className="text-xl font-medium text-gray-900 dark:text-white">
                        Créer un service
                      </h3>
                      <div>
                        <div className="mb-2 block">
                          <Label
                            htmlFor="title"
                            value="Nom du service :"
                          />
                        </div>
                        <TextInput
                          id="title"
                          placeholder="titre"
                          required={true}
                        />
                      </div>
                      <div>
                        <div className="mb-2 block">
                          <Label
                            htmlFor="description"
                            value="Description :"
                          />
                        </div>
                        <Textarea id="description">
                        </Textarea>
                      </div>
                      <Button className="mt-4" type="submit">
                        Créer
                      </Button>
                    </form>
                  </div>
                </Modal.Body>
              </Modal>
            </div>
          </Card>

        </main>
      </Layout>
    )
  }
}

export default Option;
