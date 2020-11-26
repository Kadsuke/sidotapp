import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypePlainte } from 'app/shared/model/type-plainte.model';

type EntityResponseType = HttpResponse<ITypePlainte>;
type EntityArrayResponseType = HttpResponse<ITypePlainte[]>;

@Injectable({ providedIn: 'root' })
export class TypePlainteService {
  public resourceUrl = SERVER_API_URL + 'api/type-plaintes';

  constructor(protected http: HttpClient) {}

  create(typePlainte: ITypePlainte): Observable<EntityResponseType> {
    return this.http.post<ITypePlainte>(this.resourceUrl, typePlainte, { observe: 'response' });
  }

  update(typePlainte: ITypePlainte): Observable<EntityResponseType> {
    return this.http.put<ITypePlainte>(this.resourceUrl, typePlainte, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypePlainte>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypePlainte[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
