import {Alert, Card} from "flowbite-react";
import {HiInformationCircle} from "react-icons/all";

type EventBaselineProp = {

}

const EventStatutBaseline = ({} : EventBaselineProp) => {

    if (statut == "ORGANISATION" || statut == "WAITING" || statut == "READY" || statut == "IN_PROGRESS") {
        return (
            <>
            </>
        )
    }

    return (
        <Alert color="failure">
                <span>
                    <span className="font-medium">
                        Alerte !
                    </span>
                    {' '}Le statut de l'évenement est indéterminable.
                </span>
        </Alert>
    )
}

export default EventStatutBaseline;
