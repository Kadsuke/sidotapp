import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { INatureOuvrage } from 'app/shared/model/nature-ouvrage.model';

type EntityResponseType = HttpResponse<INatureOuvrage>;
type EntityArrayResponseType = HttpResponse<INatureOuvrage[]>;

@Injectable({ providedIn: 'root' })
export class NatureOuvrageService {
  public resourceUrl = SERVER_API_URL + 'api/nature-ouvrages';

  constructor(protected http: HttpClient) {}

  create(natureOuvrage: INatureOuvrage): Observable<EntityResponseType> {
    return this.http.post<INatureOuvrage>(this.resourceUrl, natureOuvrage, { observe: 'response' });
  }

  update(natureOuvrage: INatureOuvrage): Observable<EntityResponseType> {
    return this.http.put<INatureOuvrage>(this.resourceUrl, natureOuvrage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<INatureOuvrage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<INatureOuvrage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
