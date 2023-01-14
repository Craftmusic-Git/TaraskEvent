import { GetEventResponseDto } from '../dtos/response/get-event-response.dto'
import { Button, TextInput } from 'flowbite-react'
import { useEffect, useState } from 'react'
import Datepicker from 'react-tailwindcss-datepicker'
import { UpdateDto } from '../dtos/update.dto'
import axios from 'axios'

const UPDATE_EVENT = 'http://localhost:3000/api/events/update'

type OrganisationOptionProp = {

  data?: GetEventResponseDto;
  mutate: () => void;
}

const OrganisationOption = ({data, mutate}: OrganisationOptionProp) => {

  const [capacity, setCapacity] = useState<number>();
  const [ageLimit, setAgeLimit] = useState<number>();
  const [date, setDate] = useState<any>();
  const [isOutside, setIsOutside] = useState<boolean>();

  const handleValueChange = (newValue: any) => {
    setDate(newValue);
  }

  useEffect(() => {
    if (data?.organisation) {
      setCapacity(data.organisation.capacity);
      setAgeLimit(data.organisation.ageLimit)
      setIsOutside(data.organisation.isOutside)
      let entry = {
        "startDate": data.organisation.date,
        "endDate": data.organisation.date,
      }
      handleValueChange(entry)
    }
  }, [])


  const reformatDate = (date: string) : string => {
    if (date.length < 10) {
      if (date.at(6) == '-') {
        date = date.slice(0, 5) + '0' + date.slice(5, 9);
      }
      if (date.length < 10) {
        date = date.slice(0, 8) + '0' + date.slice(8);
      }
    }
    return date;
  }

  const sendUpdates = async (e: { preventDefault: () => void; target: any;}) => {
    e.preventDefault();

    if (capacity != data?.organisation?.capacity) {
      let request = new UpdateDto();
      request.property = "CAPACITY";
      request.information = capacity;
      await sendRequest(request);
    }

    if (ageLimit != data?.organisation?.ageLimit) {
      let request = new UpdateDto();
      request.property = "LIMIT_AGE";
      request.information = ageLimit;
      await sendRequest(request);
    }

    if (reformatDate(date.startDate) != data?.organisation?.date) {
      let request = new UpdateDto();
      request.property = "DATE";
      request.information = reformatDate(date.startDate.replaceAll(',','-'));
      await sendRequest(request);
    }

    if (isOutside != data?.organisation?.isOutside) {
      let request = new UpdateDto();
      request = {
        "property": "EXTERN",
        "information": isOutside ? false : true,
      }
      console.log(request.information)
      await sendRequest(request);
    }

    mutate()
  }

  const sendRequest = async (req: UpdateDto) => {
    await axios.post(UPDATE_EVENT + '?id=' + data?.id, req);
  }

  return (
    <>
      <h3 className="text-3xl mt-3 font-bold dark:text-white">Paramètres d'organisation globaux : </h3>
      <form onSubmit={sendUpdates}>
        <div className="grid gap-6 mb-6 md:grid-cols-4">
          <div>
            <label htmlFor="capacity" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
              Capacité</label>
            <TextInput type="number" id="capactity"
                       className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                       value={capacity} onChange={(e) => setCapacity(parseInt(e.target.value))}/>
          </div>
          <div>
            <label htmlFor="ageLimit" className="block mb-2 text-sm font-medium text-gray-900 dark:text-white">
              Limite d'age</label>
            <TextInput type="number" id="ageLimit"
                       className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
                       value={ageLimit} onChange={(e) => setAgeLimit(parseInt(e.target.value))} />
          </div>
          <div>
            <label htmlFor={"date"} className={"block mb-2 text-sm font-medium text-gray-900 dark:text-white"}>Date de l'événement :</label>
            <Datepicker value={date} onChange={handleValueChange} asSingle={true} useRange={false}  />
          </div>
          <div>
            <span className="ml-3 text-sm font-medium text-gray-900 dark:text-gray-300">En extérieur : </span>
            <div className="mt-4 ml-6">
              <label className="relative inline-flex items-center cursor-pointer">
                <input type="checkbox" checked={isOutside} onChange={(e) => setIsOutside(e.target.checked)} className="sr-only peer"/>
                <div className="w-11 h-6 bg-gray-200 rounded-full peer peer-focus:ring-4 peer-focus:ring-blue-300 dark:peer-focus:ring-blue-800 dark:bg-gray-700 peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-0.5 after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-blue-600"></div>
              </label>
            </div>
          </div>
        </div>
        <Button gradientDuoTone="cyanToBlue" type="submit">
          Enregistrer les modifications
        </Button>
      </form>
    </>
  )
}

export default OrganisationOption;
