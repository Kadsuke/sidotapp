import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeuPrevisionSTBV } from 'app/shared/model/geu-prevision-stbv.model';

type EntityResponseType = HttpResponse<IGeuPrevisionSTBV>;
type EntityArrayResponseType = HttpResponse<IGeuPrevisionSTBV[]>;

@Injectable({ providedIn: 'root' })
export class GeuPrevisionSTBVService {
  public resourceUrl = SERVER_API_URL + 'api/geu-prevision-stbvs';

  constructor(protected http: HttpClient) {}

  create(geuPrevisionSTBV: IGeuPrevisionSTBV): Observable<EntityResponseType> {
    return this.http.post<IGeuPrevisionSTBV>(this.resourceUrl, geuPrevisionSTBV, { observe: 'response' });
  }

  update(geuPrevisionSTBV: IGeuPrevisionSTBV): Observable<EntityResponseType> {
    return this.http.put<IGeuPrevisionSTBV>(this.resourceUrl, geuPrevisionSTBV, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeuPrevisionSTBV>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeuPrevisionSTBV[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
