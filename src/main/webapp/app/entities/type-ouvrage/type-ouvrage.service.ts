import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeOuvrage } from 'app/shared/model/type-ouvrage.model';

type EntityResponseType = HttpResponse<ITypeOuvrage>;
type EntityArrayResponseType = HttpResponse<ITypeOuvrage[]>;

@Injectable({ providedIn: 'root' })
export class TypeOuvrageService {
  public resourceUrl = SERVER_API_URL + 'api/type-ouvrages';

  constructor(protected http: HttpClient) {}

  create(typeOuvrage: ITypeOuvrage): Observable<EntityResponseType> {
    return this.http.post<ITypeOuvrage>(this.resourceUrl, typeOuvrage, { observe: 'response' });
  }

  update(typeOuvrage: ITypeOuvrage): Observable<EntityResponseType> {
    return this.http.put<ITypeOuvrage>(this.resourceUrl, typeOuvrage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeOuvrage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeOuvrage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
