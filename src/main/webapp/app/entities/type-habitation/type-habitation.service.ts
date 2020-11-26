import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeHabitation } from 'app/shared/model/type-habitation.model';

type EntityResponseType = HttpResponse<ITypeHabitation>;
type EntityArrayResponseType = HttpResponse<ITypeHabitation[]>;

@Injectable({ providedIn: 'root' })
export class TypeHabitationService {
  public resourceUrl = SERVER_API_URL + 'api/type-habitations';

  constructor(protected http: HttpClient) {}

  create(typeHabitation: ITypeHabitation): Observable<EntityResponseType> {
    return this.http.post<ITypeHabitation>(this.resourceUrl, typeHabitation, { observe: 'response' });
  }

  update(typeHabitation: ITypeHabitation): Observable<EntityResponseType> {
    return this.http.put<ITypeHabitation>(this.resourceUrl, typeHabitation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeHabitation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeHabitation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
