import axios, {AxiosInstance, AxiosResponse} from "axios";
import {AbstractRequestDto} from "../src/shared/dtos/abstract/abstract-request.dto";
import {instanceToPlain} from "class-transformer";

export const BACK_END_SERVICE_URL : string = 'http://localhost:8080/'

export abstract class BackService {

    private api : AxiosInstance;

    constructor() {
        this.api = axios.create({
            baseURL: BACK_END_SERVICE_URL,
        })
    }

    protected async doPostBackRequest<T>(path: string, request: AbstractRequestDto | null): Promise<AxiosResponse<T>> {
        const final = instanceToPlain(request, { exposeUnsetFields: false });

        return this.api.post(path, final);
    }

    protected async doGetBackRequest<T>(path: string, request: AbstractRequestDto | null): Promise<AxiosResponse<T>> {
        const final = instanceToPlain(request, { exposeUnsetFields: false});

        return this.api.get(path, final);
    }
}
