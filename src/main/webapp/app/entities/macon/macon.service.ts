import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMacon } from 'app/shared/model/macon.model';

type EntityResponseType = HttpResponse<IMacon>;
type EntityArrayResponseType = HttpResponse<IMacon[]>;

@Injectable({ providedIn: 'root' })
export class MaconService {
  public resourceUrl = SERVER_API_URL + 'api/macons';

  constructor(protected http: HttpClient) {}

  create(macon: IMacon): Observable<EntityResponseType> {
    return this.http.post<IMacon>(this.resourceUrl, macon, { observe: 'response' });
  }

  update(macon: IMacon): Observable<EntityResponseType> {
    return this.http.put<IMacon>(this.resourceUrl, macon, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMacon>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMacon[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
