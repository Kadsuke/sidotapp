import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITacherons } from 'app/shared/model/tacherons.model';

type EntityResponseType = HttpResponse<ITacherons>;
type EntityArrayResponseType = HttpResponse<ITacherons[]>;

@Injectable({ providedIn: 'root' })
export class TacheronsService {
  public resourceUrl = SERVER_API_URL + 'api/tacherons';

  constructor(protected http: HttpClient) {}

  create(tacherons: ITacherons): Observable<EntityResponseType> {
    return this.http.post<ITacherons>(this.resourceUrl, tacherons, { observe: 'response' });
  }

  update(tacherons: ITacherons): Observable<EntityResponseType> {
    return this.http.put<ITacherons>(this.resourceUrl, tacherons, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITacherons>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITacherons[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
