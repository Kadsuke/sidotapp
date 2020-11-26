import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICaracteristiqueOuvrage } from 'app/shared/model/caracteristique-ouvrage.model';

type EntityResponseType = HttpResponse<ICaracteristiqueOuvrage>;
type EntityArrayResponseType = HttpResponse<ICaracteristiqueOuvrage[]>;

@Injectable({ providedIn: 'root' })
export class CaracteristiqueOuvrageService {
  public resourceUrl = SERVER_API_URL + 'api/caracteristique-ouvrages';

  constructor(protected http: HttpClient) {}

  create(caracteristiqueOuvrage: ICaracteristiqueOuvrage): Observable<EntityResponseType> {
    return this.http.post<ICaracteristiqueOuvrage>(this.resourceUrl, caracteristiqueOuvrage, { observe: 'response' });
  }

  update(caracteristiqueOuvrage: ICaracteristiqueOuvrage): Observable<EntityResponseType> {
    return this.http.put<ICaracteristiqueOuvrage>(this.resourceUrl, caracteristiqueOuvrage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICaracteristiqueOuvrage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICaracteristiqueOuvrage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
