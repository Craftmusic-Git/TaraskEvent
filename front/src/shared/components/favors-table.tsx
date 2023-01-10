import { Button, Modal, Table } from 'flowbite-react'
import React, { useState } from 'react'
import { FavorDto } from '../dtos/favor.dto'
import axios from 'axios'
import { REGISTER_NEW_USER } from '../../../pages/register'
import { useSWRConfig } from 'swr'
import { GET_EVENT } from '../../api/get-event.api'
import { Router, useRouter } from 'next/router'

export type favorsTableProps = {
  favors?: FavorDto[];
  withProgress?: boolean;
  mutate: () => void;
}

const FavorsTable = ({favors, withProgress = false, mutate} : favorsTableProps) => {

  const [ editionModal, setEditionModal ] = useState<boolean>();
  const [ deleteModal, setDeleteModal ] = useState<boolean>();
  const [ editedFavor, setEditedFavor ] = useState<FavorDto>();

  const router = useRouter()

  const DELETE_API_PATH = "http://localhost:3000/api/favors/delete"

  const deleteFavor = async () => {
    axios.get(DELETE_API_PATH + '?id=' + editedFavor?.id).then(mutate);
  }

  const progressHeader = (progress : boolean) => {
    if (progress) {
      return (
        <Table.HeadCell>
          Progression
        </Table.HeadCell>
      )
    } else {
      return null;
    }
  }

  const progressCell = (progress : boolean, favor: FavorDto) => {
    if (progress) {
      const progressStyle = 'witdh: '+favor.progress+'%';

      return (
        <div className="w-full bg-gray-200 rounded-full dark:bg-gray-700">
          <div className="bg-blue-600 text-xs font-medium text-blue-100 text-center p-0.5 leading-none rounded-full" style={progressStyle}> {favor.progress}%
          </div>
        </div>
      )
    } else {
      return null;
    }
  }

  const editionCell = (favor: FavorDto) => {

    return (
      <div className="flex flex-wrap gap-2">
        <Button.Group>
          <Button onClick={() => {setEditedFavor(favor); setEditionModal(true)}} color={'info'}>
            <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                 xmlns="http://www.w3.org/2000/svg">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                    d="M15.232 5.232l3.536 3.536m-2.036-5.036a2.5 2.5 0 113.536 3.536L6.5 21.036H3v-3.572L16.732 3.732z">
              </path>
            </svg>
            Éditer
          </Button>
          <Button onClick={() => {setEditedFavor(favor); setDeleteModal(true)}} color={'failure'}>
            <svg className="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                 xmlns="http://www.w3.org/2000/svg">
              <path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2"
                    d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16">
              </path>
            </svg>
            Supprimer
          </Button>
        </Button.Group>
      </div>
    )
  }

  return (
    <>
      <Table>
        <Table.Head>
          <Table.HeadCell>
            Nom
          </Table.HeadCell>
          <Table.HeadCell>
            Description
          </Table.HeadCell>
          { progressHeader(withProgress) }
          <Table.HeadCell>
            Édition
          </Table.HeadCell>
        </Table.Head>
        <Table.Body className={"divide-y"}>
          {
            favors?.map(value => (
              <Table.Row key={value.id} className="bg-white dark:border-gray-700 dark:bg-gray-800" >
                <Table.Cell className="whitespace-nowrap font-medium text-gray-900 dark:text-white">
                  {value.title}
                </Table.Cell>
                <Table.Cell>
                  {value.description}
                </Table.Cell>
                <Table.Cell className={"w-72"}>
                  {editionCell(value)}
                </Table.Cell>
              </Table.Row>
            ))
          }
        </Table.Body>
      </Table>

      <Modal show={editionModal} onClose={() => setEditionModal(false)}>
        <Modal.Header>
          Service : {editedFavor?.title}
        </Modal.Header>
      </Modal>

      <Modal show={deleteModal} onClose={() => {setDeleteModal(false)}} size="md" popup={true}>
        <Modal.Header>
          Suppresion : {editedFavor?.title}
        </Modal.Header>
        <Modal.Body>
          <div className="text-center">
            <h3 className="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400">
              Voulez-vous sûr de vouloir supprimer ce service ?
            </h3>
            <div className="flex justify-center gap-4">
              <Button
                color="failure"
                onClick={() => {
                  deleteFavor();
                  setDeleteModal(false);
                }} >
                Oui, je suis sûr(e)s
              </Button>

              <Button
                color="gray"
                onClick={() => setDeleteModal(false)} >
                Non, annuler
              </Button>
            </div>
          </div>
        </Modal.Body>
      </Modal>
    </>
  )
}

export default FavorsTable;
