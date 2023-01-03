import Head from 'next/head'
import Link from 'next/link'

const Authentication = () => {
  return (
    <>
      <Head>
        <title>Connection</title>
        <meta name="description" content="Tarask-event" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href="/favicon.ico" />
        <script src="/darkmode.js"></script>
      </Head>
      <main className={"bg-white h-screen border-gray-200 dark:bg-gray-900"}>
        <div className={"pt-8"}>
          <div className="ml-auto mr-auto w-full max-w-sm p-4 bg-white border border-gray-200 rounded-lg shadow-md sm:p-6 md:p-8 dark:bg-gray-800 dark:border-gray-700">
            <form className="space-y-6" action="/login" method="post">
              <h5 className="text-xl font-medium text-gray-900 dark:text-white">Connection à Tarask-Event</h5>
              <div>
                <label htmlFor={"username"} className={"block mb-2 text-sm font-medium text-gray-900 dark:text-white"}>Pseudonyme :</label>
                <input type={"text"} name={"username"} id={"username"} className={"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"} placeholder={"username"} required/>
              </div>
              <div>
                <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Mot de passe :</label>
                <input type="password" name="password" id="password" placeholder="••••••••"
                       className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
                       required/>
              </div>
              <div className={"flex items-start"}>
                <div className="flex items-start">
                  <div className="flex items-center h-5">
                    <input id="remember" type="checkbox" value=""
                           className="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-blue-600 dark:ring-offset-gray-800"
                           required/>
                  </div>
                  <label htmlFor="remember" className="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">
                    Enregistrer
                  </label>
                </div>
              </div>
              <button type="submit"
                      className="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                Se connecter
              </button>
              <div className="text-sm font-medium text-gray-500 dark:text-gray-300">
                Pas inscrit ?
                <Link href="/register" className="text-blue-700 hover:underline dark:text-blue-500"> Créer un compte</Link>
              </div>
            </form>
          </div>
        </div>
      </main>
    </>
  )
}

export default Authentication;
