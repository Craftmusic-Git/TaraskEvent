import Head from 'next/head'
import Link from 'next/link'
import {UserRegistrationDto} from "../src/shared/dtos/user-registration.dto";
import axios from "axios";
import {signIn} from "next-auth/react";
import Footer from '../src/shared/components/footer';

export const REGISTER_NEW_USER = "http://localhost:3000/api/user/register"

const Register = () => {
  const submitRegister = (e : any) => {
    e.preventDefault();
    let user = new UserRegistrationDto();
    user.username = e.target.username.value;
    user.name = e.target.name.value;
    user.lastName = e.target.lastname.value;
    user.password = e.target.password.value;
    user.birthDate = e.target.birthdate.value;

    const res = axios.post(REGISTER_NEW_USER, user);

    signIn("keycloak", {callbackUrl: 'http://localhost:3000/'});
  }

  return (
    <>
      <Head>
        <title>Inscription</title>
        <meta name="description" content="Tarask-event" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="icon" href="/favicon.ico" />
        <script src="/darkmode.js"></script>
        <script src="/validateRegisterForm.js"></script>
      </Head>
      <main className={"bg-white h-screen border-gray-200 dark:bg-gray-900"}>
        <div className={"pt-8"}>
          <div className={"ml-auto mr-auto w-full max-w-sm p-4 bg-white border border-gray-200 rounded-lg shadow-md sm:p-6 md:p-8 dark:bg-gray-800 dark:border-gray-700"}>
            <form className="space-y-6 ml-auto mr-auto" onSubmit={submitRegister} method="post">
              <h5 className="text-xl font-medium text-gray-900 dark:text-white">Inscription à Tarask-Event</h5>
              <div>
                <label htmlFor={"username"} className={"block mb-2 text-sm font-medium text-gray-900 dark:text-white"}>Pseudonyme :</label>
                <input type={"text"} name={"username"} id={"username"} className={"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"} placeholder={"Pseudonyme"} required/>
              </div>
              <div>
                <label htmlFor={"name"} className={"block mb-2 text-sm font-medium text-gray-900 dark:text-white"}>Prénom :</label>
                <input type={"text"} name={"name"} id={"name"} className={"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"} placeholder={"Prénom"} required/>
              </div>
              <div>
                <label htmlFor={"lastname"} className={"block mb-2 text-sm font-medium text-gray-900 dark:text-white"}>Nom :</label>
                <input type={"text"} name={"lastname"} id={"lastname"} className={"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"} placeholder={"Nom"} required/>
              </div>
              <div>
                <label htmlFor={"mail"} className={"block mb-2 text-sm font-medium text-gray-900 dark:text-white"}>Mail :</label>
                <input type={"email"} name={"mail"} id={"mail"} className={"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"} placeholder={"jean.dupont@mail.com"} />
              </div>
              <div>
                <label htmlFor={"birthdate"} className={"block mb-2 text-sm font-medium text-gray-900 dark:text-white"}>Date de naissance :</label>
                <input type={"date"} name={"birthdate"} id={"birthdate"} className={"bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"} />
              </div>
              <div>
                <label htmlFor="password" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">Mot de passe :</label>
                <input type="password" name="password" id="password" placeholder="Mot de passe"
                       className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
                       required minLength={8}/>
                <input type="password" name="repeat-password" id="password-repeat" placeholder="Répéter votre mot de passe"
                       className="mt-4 bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-600 dark:border-gray-500 dark:placeholder-gray-400 dark:text-white"
                       required minLength={8}/>
              </div>
              <button type="submit"
                      className="w-full text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 text-center dark:bg-blue-600 dark:hover:bg-blue-700 dark:focus:ring-blue-800">
                <span>S'inscrire !</span>
              </button>
              <div className="text-sm font-medium text-gray-500 dark:text-gray-300">
                <span>Déja inscrit ?</span>
                <Link href="/authentication" className="text-blue-700 hover:underline dark:text-blue-500"> Se connecter </Link>
              </div>
            </form>
          </div>
        </div>
      </main>
      <Footer />
    </>
  )
}

export default Register;
