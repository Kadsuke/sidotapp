import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBailleur } from 'app/shared/model/bailleur.model';

type EntityResponseType = HttpResponse<IBailleur>;
type EntityArrayResponseType = HttpResponse<IBailleur[]>;

@Injectable({ providedIn: 'root' })
export class BailleurService {
  public resourceUrl = SERVER_API_URL + 'api/bailleurs';

  constructor(protected http: HttpClient) {}

  create(bailleur: IBailleur): Observable<EntityResponseType> {
    return this.http.post<IBailleur>(this.resourceUrl, bailleur, { observe: 'response' });
  }

  update(bailleur: IBailleur): Observable<EntityResponseType> {
    return this.http.put<IBailleur>(this.resourceUrl, bailleur, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBailleur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBailleur[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
