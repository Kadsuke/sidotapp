import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPrefabricant } from 'app/shared/model/prefabricant.model';

type EntityResponseType = HttpResponse<IPrefabricant>;
type EntityArrayResponseType = HttpResponse<IPrefabricant[]>;

@Injectable({ providedIn: 'root' })
export class PrefabricantService {
  public resourceUrl = SERVER_API_URL + 'api/prefabricants';

  constructor(protected http: HttpClient) {}

  create(prefabricant: IPrefabricant): Observable<EntityResponseType> {
    return this.http.post<IPrefabricant>(this.resourceUrl, prefabricant, { observe: 'response' });
  }

  update(prefabricant: IPrefabricant): Observable<EntityResponseType> {
    return this.http.put<IPrefabricant>(this.resourceUrl, prefabricant, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPrefabricant>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPrefabricant[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
