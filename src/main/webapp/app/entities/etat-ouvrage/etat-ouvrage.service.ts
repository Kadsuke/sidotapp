import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEtatOuvrage } from 'app/shared/model/etat-ouvrage.model';

type EntityResponseType = HttpResponse<IEtatOuvrage>;
type EntityArrayResponseType = HttpResponse<IEtatOuvrage[]>;

@Injectable({ providedIn: 'root' })
export class EtatOuvrageService {
  public resourceUrl = SERVER_API_URL + 'api/etat-ouvrages';

  constructor(protected http: HttpClient) {}

  create(etatOuvrage: IEtatOuvrage): Observable<EntityResponseType> {
    return this.http.post<IEtatOuvrage>(this.resourceUrl, etatOuvrage, { observe: 'response' });
  }

  update(etatOuvrage: IEtatOuvrage): Observable<EntityResponseType> {
    return this.http.put<IEtatOuvrage>(this.resourceUrl, etatOuvrage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEtatOuvrage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEtatOuvrage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
