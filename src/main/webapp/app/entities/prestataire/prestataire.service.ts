import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPrestataire } from 'app/shared/model/prestataire.model';

type EntityResponseType = HttpResponse<IPrestataire>;
type EntityArrayResponseType = HttpResponse<IPrestataire[]>;

@Injectable({ providedIn: 'root' })
export class PrestataireService {
  public resourceUrl = SERVER_API_URL + 'api/prestataires';

  constructor(protected http: HttpClient) {}

  create(prestataire: IPrestataire): Observable<EntityResponseType> {
    return this.http.post<IPrestataire>(this.resourceUrl, prestataire, { observe: 'response' });
  }

  update(prestataire: IPrestataire): Observable<EntityResponseType> {
    return this.http.put<IPrestataire>(this.resourceUrl, prestataire, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPrestataire>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPrestataire[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
