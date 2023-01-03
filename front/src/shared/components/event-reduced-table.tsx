import {Badge, Button, Table} from "flowbite-react";
import {EventReducedDto} from "../dtos/event-reduced.dto";
import Link from "next/link";

type ReducedEventTableProps = {
    events?: EventReducedDto[];

    organizer?: boolean;
}

type CellOptionProps = {
    event?: EventReducedDto;
    organizer?: boolean;
}

const cellTableForStatus = (statut : string | undefined) => {
    if (statut == "CONFIGURATION") {
        return (
            <Table.Cell className="whitespace-nowrap font-medium text-yellow-300">
                <div className="flex flex-wrap">
                    <Badge color="warning">
                        <svg className="w-6 h-6 inline" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                             xmlns="http://www.w3.org/2000/svg">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                  d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z"></path>
                        </svg>
                        Configuration
                    </Badge>
                </div>
            </Table.Cell>
        )
    }
    if (statut == "WAITING") {
        return (
            <Table.Cell className="whitespace-nowrap font-medium text-blue-700">
                <div className="flex flex-wrap">
                    <Badge color="info">
                        <svg className="w-6 h-6 inline" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                             xmlns="http://www.w3.org/2000/svg">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                                  d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-3 7h3m-3 4h3m-6-4h.01M9 16h.01"></path>
                        </svg>
                        En attente
                    </Badge>
                </div>
            </Table.Cell>
        )
    }
    if (statut == "READY") {
        return (
            <Table.Cell className="whitespace-nowrap font-medium text-green-500">

                <svg className="w-6 h-6 inline" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                     xmlns="http://www.w3.org/2000/svg">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7"></path>
                </svg>
                Prêt
            </Table.Cell>
        )
    }
    if (statut == "IN_PROGRESS") {
        return (
            <Table.Cell className="whitespace-nowrap font-medium text-purple-600">
                <svg className="w-6 h-6 inline" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                     xmlns="http://www.w3.org/2000/svg">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                          d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15"></path>
                </svg>
                En cours
            </Table.Cell>
        )
    }
    if (statut == "ERROR" || statut == undefined) {
        return (
            <Table.Cell className="whitespace-nowrap font-bold text-red-700">
                <svg className="w-6 h-6 inline" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                     xmlns="http://www.w3.org/2000/svg">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
                          d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                </svg>
                Erreur
            </Table.Cell>
        )
    }
}

const cellEventOptions = (event:EventReducedDto , organizer:boolean|undefined) => {
    if (organizer == true) {
        return (
            <Table.Cell className={"w-56"}>
                <div className="flex flex-wrap gap-2">
                    <Button.Group>
                        <Button href={"/events/event?id="+event.id} gradientDuoTone="purpleToBlue">
                            Rejoindre
                        </Button>
                        <Button  href={"/events/option?id="+event.id} gradientDuoTone="redToYellow">
                            Options
                        </Button>
                    </Button.Group>
                </div>
            </Table.Cell>
        )
    } else {
        return (
            <Table.Cell className={"w-56"}>
                <Link href={"/events/event?id="+event.id}>
                    <Button gradientDuoTone="purpleToBlue">
                        Rejoindre
                    </Button>
                </Link>
            </Table.Cell>
        )
    }
}

const EventReducedTable = ({ events, organizer } : ReducedEventTableProps) => {
    return (
        <Table>
            <Table.Head>
                <Table.HeadCell>
                    Nom
                </Table.HeadCell>
                <Table.HeadCell>
                    Participants
                </Table.HeadCell>
                <Table.HeadCell>
                    État
                </Table.HeadCell>
                <Table.HeadCell>
                    Édition
                </Table.HeadCell>
            </Table.Head>
            <Table.Body className={"divide-y"}>
                {
                    events?.map(value => (
                        <Table.Row key={value.id} className="bg-white dark:border-gray-700 dark:bg-gray-800" >
                            <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white">
                                {value.name}
                            </Table.Cell>
                            <Table.Cell>
                                {value.nbGuest} / {value.maxGuest}
                            </Table.Cell>
                            {cellTableForStatus(value.statut)}
                            {cellEventOptions(value, organizer)}
                        </Table.Row>
                    ))
                }
            </Table.Body>
        </Table>
    )
}

export default EventReducedTable;
