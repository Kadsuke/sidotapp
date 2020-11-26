import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRefPeriodicite } from 'app/shared/model/ref-periodicite.model';

type EntityResponseType = HttpResponse<IRefPeriodicite>;
type EntityArrayResponseType = HttpResponse<IRefPeriodicite[]>;

@Injectable({ providedIn: 'root' })
export class RefPeriodiciteService {
  public resourceUrl = SERVER_API_URL + 'api/ref-periodicites';

  constructor(protected http: HttpClient) {}

  create(refPeriodicite: IRefPeriodicite): Observable<EntityResponseType> {
    return this.http.post<IRefPeriodicite>(this.resourceUrl, refPeriodicite, { observe: 'response' });
  }

  update(refPeriodicite: IRefPeriodicite): Observable<EntityResponseType> {
    return this.http.put<IRefPeriodicite>(this.resourceUrl, refPeriodicite, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRefPeriodicite>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRefPeriodicite[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
