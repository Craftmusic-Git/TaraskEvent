import {useSession} from "next-auth/react";
import {AbstractRequestDto} from "../src/shared/dtos/abstract/abstract-request.dto";
import {AxiosInstance, AxiosResponse} from "axios";
import {instanceToPlain} from "class-transformer";
import {BACK_END_SERVICE_URL} from "./back-service";
import axios from 'axios'

export abstract class BackSecurizedService {

    private api: AxiosInstance;

    constructor(private token: string) {
        this.api = axios.create({
            baseURL: BACK_END_SERVICE_URL,
            headers: {
                Authorization: `Bearer ${token}`,
            },
        })
    }

    protected doPostSecurizedBackRequest<T>(path: string, request: AbstractRequestDto | null): Promise<AxiosResponse<T>> {
        const final = instanceToPlain(request, { exposeUnsetFields: false });

        return this.api.post(path,final);
    }

    protected doGetSecurizedBackRequest<T>(path: string, request: AbstractRequestDto | null): Promise<AxiosResponse<T>> {
        const final = instanceToPlain(request, { exposeUnsetFields: false});

        return this.api.get(path, final);
    }

    protected  doDeleteSecurizedBackRequest<T>(path: string, request: AbstractRequestDto | null): Promise<AxiosResponse<T>> {
        const final = instanceToPlain(request, { exposeUnsetFields: false});

        return this.api.delete(path, final);
    }

    protected doPatchSecurizedBackRequest<T>(path: string, request: AbstractRequestDto | null) : Promise<AxiosResponse<T>> {
        const final = instanceToPlain(request, { exposeUnsetFields: false});

        return this.api.patch(path, final);
    }
}
