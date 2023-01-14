import { useRouter } from 'next/router'

const InviteLink = () => {
  const router = useRouter()

  return (
    <>
      <h1>
        La page est {router.basePath}
      </h1>
    </>
  )
}

export default InviteLink;
