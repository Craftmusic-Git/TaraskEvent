import Head from 'next/head'
import Image from 'next/image'
import { Inter } from '@next/font/google'
import styles from '../src/styles/Home.module.css'
import Layout from '../src/shared/components/layout'

const inter = Inter({ subsets: ['latin'] })

export default function Home() {
  return (
    <Layout>
      <Head>
        <title>Tarask-event</title>
        <meta name="description" content="Tarask-event" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="apple-touch-icon" sizes="180x180" href="/apple-touch-icon.png"/>
        <link rel="icon" type="image/png" sizes="32x32" href="/favicon-32x32.png"/>
        <link rel="icon" type="image/png" sizes="16x16" href="/favicon-16x16.png"/>
        <link rel="manifest" href="/site.webmanifest"/>
        <script src="/darkmode.js"></script>
      </Head>
      <main>
        <div className={"text-center p-8"}>
          <h1 className="mb-4 text-5xl font-extrabold text-gray-900 dark:text-white md:text-5xl lg:text-6xl">
            <span className="text-transparent bg-clip-text bg-gradient-to-r to-emerald-600 from-sky-400">
              Tarask-Event
            </span></h1>
          <p className="mb-2 text-lg font-normal text-gray-500 lg:text-xl sm:px-16 xl:px-48 dark:text-gray-400">
            Organiser un événement n'a jamais était aussi simple.</p>
          <p className={"text-md mb-6 font-normal text-gray-600 lg:text-lg sm:px-16 xl:px-48 dark:text-gray-500"}>
            Projet d'Application Internet Avancée de 3A à l'ENSISA
          </p>
        </div>
      </main>
    </Layout>
  )
}
