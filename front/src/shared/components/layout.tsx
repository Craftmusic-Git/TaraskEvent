import Navbar from './navbar'
import Footer from './footer'
import { AppProps } from 'next/app'

const Layout = ({ children } : any) => {
  return (
    <>
      <Navbar />
      <main className={"bg-white h-screen border-gray-200 dark:bg-gray-900"}>
        {children}
      </main>
      <Footer />
    </>
  )
}

export default Layout;
