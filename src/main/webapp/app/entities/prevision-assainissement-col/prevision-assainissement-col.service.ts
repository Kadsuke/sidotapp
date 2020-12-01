import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPrevisionAssainissementCol } from 'app/shared/model/prevision-assainissement-col.model';

type EntityResponseType = HttpResponse<IPrevisionAssainissementCol>;
type EntityArrayResponseType = HttpResponse<IPrevisionAssainissementCol[]>;

@Injectable({ providedIn: 'root' })
export class PrevisionAssainissementColService {
  public resourceUrl = SERVER_API_URL + 'api/prevision-assainissement-cols';

  constructor(protected http: HttpClient) {}

  create(previsionAssainissementCol: IPrevisionAssainissementCol): Observable<EntityResponseType> {
    return this.http.post<IPrevisionAssainissementCol>(this.resourceUrl, previsionAssainissementCol, { observe: 'response' });
  }

  update(previsionAssainissementCol: IPrevisionAssainissementCol): Observable<EntityResponseType> {
    return this.http.put<IPrevisionAssainissementCol>(this.resourceUrl, previsionAssainissementCol, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPrevisionAssainissementCol>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPrevisionAssainissementCol[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
