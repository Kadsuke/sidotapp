import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICentreRegroupement } from 'app/shared/model/centre-regroupement.model';

type EntityResponseType = HttpResponse<ICentreRegroupement>;
type EntityArrayResponseType = HttpResponse<ICentreRegroupement[]>;

@Injectable({ providedIn: 'root' })
export class CentreRegroupementService {
  public resourceUrl = SERVER_API_URL + 'api/centre-regroupements';

  constructor(protected http: HttpClient) {}

  create(centreRegroupement: ICentreRegroupement): Observable<EntityResponseType> {
    return this.http.post<ICentreRegroupement>(this.resourceUrl, centreRegroupement, { observe: 'response' });
  }

  update(centreRegroupement: ICentreRegroupement): Observable<EntityResponseType> {
    return this.http.put<ICentreRegroupement>(this.resourceUrl, centreRegroupement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICentreRegroupement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICentreRegroupement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
