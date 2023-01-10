import Navbar from './navbar'
import Footer from './footer'

const Layout = ({ children } : any) => {
  return (
    <>
      <Navbar />
      <main className={"bg-white min-h-screen max-h-full border-gray-200 dark:bg-gray-900"}>
        {children}
      </main>
      <Footer />
    </>
  )
}

export default Layout;
