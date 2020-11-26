import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeuSTEP } from 'app/shared/model/geu-step.model';

type EntityResponseType = HttpResponse<IGeuSTEP>;
type EntityArrayResponseType = HttpResponse<IGeuSTEP[]>;

@Injectable({ providedIn: 'root' })
export class GeuSTEPService {
  public resourceUrl = SERVER_API_URL + 'api/geu-steps';

  constructor(protected http: HttpClient) {}

  create(geuSTEP: IGeuSTEP): Observable<EntityResponseType> {
    return this.http.post<IGeuSTEP>(this.resourceUrl, geuSTEP, { observe: 'response' });
  }

  update(geuSTEP: IGeuSTEP): Observable<EntityResponseType> {
    return this.http.put<IGeuSTEP>(this.resourceUrl, geuSTEP, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeuSTEP>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeuSTEP[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
