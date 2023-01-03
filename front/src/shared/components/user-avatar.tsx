import useSWR from "swr";
import {GET_ACTUAL_USER, fetcher} from "../../api/auth.api";
import {signOut} from "next-auth/react";
import {Dropdown} from "flowbite-react";
import Link from "next/link";

const UserAvatar = () => {

    const { data, error, isLoading } = useSWR(GET_ACTUAL_USER, fetcher);

    if (error) signOut();
    if (isLoading) return <div>loading...</div>

    let firstLetters : string = "XX";

    if (data) {
        if (data.lastname != undefined && data.name != undefined) {
            firstLetters = data.name.charAt(0).concat(data.lastname.charAt(0)).toUpperCase();
        }
    }

    // render data
    return (
    <>
        <Dropdown
            arrowIcon={false}
            inline={true}
            label={
            <div className="inline-flex mb-2 overflow-hidden relative justify-center items-center w-10 h-10 bg-gray-100 rounded-full dark:bg-gray-600">
                <span className="font-medium text-gray-600 dark:text-gray-300">{firstLetters}</span>
            </div>
        }>
            <Dropdown.Header>
                <span className="block text-sm font-semibold">
                    {data?.username}
                </span>
                <span className="block truncate text-sm font-medium">
                    {data?.name + ' ' + data?.lastname}
                </span>
            </Dropdown.Header>
            <Link href={"/properties"}>
                <Dropdown.Item>Configurations</Dropdown.Item>
            </Link>
            <Dropdown.Divider />
            <Dropdown.Item>
                <span onClick={() => signOut()}>Déconnexion</span>
            </Dropdown.Item>
        </Dropdown>
    </>
    )
}

export default UserAvatar;
