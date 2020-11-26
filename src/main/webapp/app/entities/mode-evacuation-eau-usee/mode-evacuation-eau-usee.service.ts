import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IModeEvacuationEauUsee } from 'app/shared/model/mode-evacuation-eau-usee.model';

type EntityResponseType = HttpResponse<IModeEvacuationEauUsee>;
type EntityArrayResponseType = HttpResponse<IModeEvacuationEauUsee[]>;

@Injectable({ providedIn: 'root' })
export class ModeEvacuationEauUseeService {
  public resourceUrl = SERVER_API_URL + 'api/mode-evacuation-eau-usees';

  constructor(protected http: HttpClient) {}

  create(modeEvacuationEauUsee: IModeEvacuationEauUsee): Observable<EntityResponseType> {
    return this.http.post<IModeEvacuationEauUsee>(this.resourceUrl, modeEvacuationEauUsee, { observe: 'response' });
  }

  update(modeEvacuationEauUsee: IModeEvacuationEauUsee): Observable<EntityResponseType> {
    return this.http.put<IModeEvacuationEauUsee>(this.resourceUrl, modeEvacuationEauUsee, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IModeEvacuationEauUsee>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IModeEvacuationEauUsee[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
