import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeAnalyse } from 'app/shared/model/type-analyse.model';

type EntityResponseType = HttpResponse<ITypeAnalyse>;
type EntityArrayResponseType = HttpResponse<ITypeAnalyse[]>;

@Injectable({ providedIn: 'root' })
export class TypeAnalyseService {
  public resourceUrl = SERVER_API_URL + 'api/type-analyses';

  constructor(protected http: HttpClient) {}

  create(typeAnalyse: ITypeAnalyse): Observable<EntityResponseType> {
    return this.http.post<ITypeAnalyse>(this.resourceUrl, typeAnalyse, { observe: 'response' });
  }

  update(typeAnalyse: ITypeAnalyse): Observable<EntityResponseType> {
    return this.http.put<ITypeAnalyse>(this.resourceUrl, typeAnalyse, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeAnalyse>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeAnalyse[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
