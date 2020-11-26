import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeuSTBV } from 'app/shared/model/geu-stbv.model';

type EntityResponseType = HttpResponse<IGeuSTBV>;
type EntityArrayResponseType = HttpResponse<IGeuSTBV[]>;

@Injectable({ providedIn: 'root' })
export class GeuSTBVService {
  public resourceUrl = SERVER_API_URL + 'api/geu-stbvs';

  constructor(protected http: HttpClient) {}

  create(geuSTBV: IGeuSTBV): Observable<EntityResponseType> {
    return this.http.post<IGeuSTBV>(this.resourceUrl, geuSTBV, { observe: 'response' });
  }

  update(geuSTBV: IGeuSTBV): Observable<EntityResponseType> {
    return this.http.put<IGeuSTBV>(this.resourceUrl, geuSTBV, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeuSTBV>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeuSTBV[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
