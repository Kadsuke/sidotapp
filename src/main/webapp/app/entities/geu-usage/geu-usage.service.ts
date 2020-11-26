import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeuUsage } from 'app/shared/model/geu-usage.model';

type EntityResponseType = HttpResponse<IGeuUsage>;
type EntityArrayResponseType = HttpResponse<IGeuUsage[]>;

@Injectable({ providedIn: 'root' })
export class GeuUsageService {
  public resourceUrl = SERVER_API_URL + 'api/geu-usages';

  constructor(protected http: HttpClient) {}

  create(geuUsage: IGeuUsage): Observable<EntityResponseType> {
    return this.http.post<IGeuUsage>(this.resourceUrl, geuUsage, { observe: 'response' });
  }

  update(geuUsage: IGeuUsage): Observable<EntityResponseType> {
    return this.http.put<IGeuUsage>(this.resourceUrl, geuUsage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeuUsage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeuUsage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
