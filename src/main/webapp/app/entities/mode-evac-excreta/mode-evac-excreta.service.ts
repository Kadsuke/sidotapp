import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IModeEvacExcreta } from 'app/shared/model/mode-evac-excreta.model';

type EntityResponseType = HttpResponse<IModeEvacExcreta>;
type EntityArrayResponseType = HttpResponse<IModeEvacExcreta[]>;

@Injectable({ providedIn: 'root' })
export class ModeEvacExcretaService {
  public resourceUrl = SERVER_API_URL + 'api/mode-evac-excretas';

  constructor(protected http: HttpClient) {}

  create(modeEvacExcreta: IModeEvacExcreta): Observable<EntityResponseType> {
    return this.http.post<IModeEvacExcreta>(this.resourceUrl, modeEvacExcreta, { observe: 'response' });
  }

  update(modeEvacExcreta: IModeEvacExcreta): Observable<EntityResponseType> {
    return this.http.put<IModeEvacExcreta>(this.resourceUrl, modeEvacExcreta, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IModeEvacExcreta>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IModeEvacExcreta[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
