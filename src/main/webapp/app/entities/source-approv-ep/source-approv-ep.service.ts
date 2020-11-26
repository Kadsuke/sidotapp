import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISourceApprovEp } from 'app/shared/model/source-approv-ep.model';

type EntityResponseType = HttpResponse<ISourceApprovEp>;
type EntityArrayResponseType = HttpResponse<ISourceApprovEp[]>;

@Injectable({ providedIn: 'root' })
export class SourceApprovEpService {
  public resourceUrl = SERVER_API_URL + 'api/source-approv-eps';

  constructor(protected http: HttpClient) {}

  create(sourceApprovEp: ISourceApprovEp): Observable<EntityResponseType> {
    return this.http.post<ISourceApprovEp>(this.resourceUrl, sourceApprovEp, { observe: 'response' });
  }

  update(sourceApprovEp: ISourceApprovEp): Observable<EntityResponseType> {
    return this.http.put<ISourceApprovEp>(this.resourceUrl, sourceApprovEp, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISourceApprovEp>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISourceApprovEp[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
