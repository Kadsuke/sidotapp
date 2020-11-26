import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRefMois } from 'app/shared/model/ref-mois.model';

type EntityResponseType = HttpResponse<IRefMois>;
type EntityArrayResponseType = HttpResponse<IRefMois[]>;

@Injectable({ providedIn: 'root' })
export class RefMoisService {
  public resourceUrl = SERVER_API_URL + 'api/ref-mois';

  constructor(protected http: HttpClient) {}

  create(refMois: IRefMois): Observable<EntityResponseType> {
    return this.http.post<IRefMois>(this.resourceUrl, refMois, { observe: 'response' });
  }

  update(refMois: IRefMois): Observable<EntityResponseType> {
    return this.http.put<IRefMois>(this.resourceUrl, refMois, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRefMois>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRefMois[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
