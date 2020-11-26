import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeEquipement } from 'app/shared/model/type-equipement.model';

type EntityResponseType = HttpResponse<ITypeEquipement>;
type EntityArrayResponseType = HttpResponse<ITypeEquipement[]>;

@Injectable({ providedIn: 'root' })
export class TypeEquipementService {
  public resourceUrl = SERVER_API_URL + 'api/type-equipements';

  constructor(protected http: HttpClient) {}

  create(typeEquipement: ITypeEquipement): Observable<EntityResponseType> {
    return this.http.post<ITypeEquipement>(this.resourceUrl, typeEquipement, { observe: 'response' });
  }

  update(typeEquipement: ITypeEquipement): Observable<EntityResponseType> {
    return this.http.put<ITypeEquipement>(this.resourceUrl, typeEquipement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeEquipement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeEquipement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
