import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPrevisionPsa } from 'app/shared/model/prevision-psa.model';

type EntityResponseType = HttpResponse<IPrevisionPsa>;
type EntityArrayResponseType = HttpResponse<IPrevisionPsa[]>;

@Injectable({ providedIn: 'root' })
export class PrevisionPsaService {
  public resourceUrl = SERVER_API_URL + 'api/prevision-psas';

  constructor(protected http: HttpClient) {}

  create(previsionPsa: IPrevisionPsa): Observable<EntityResponseType> {
    return this.http.post<IPrevisionPsa>(this.resourceUrl, previsionPsa, { observe: 'response' });
  }

  update(previsionPsa: IPrevisionPsa): Observable<EntityResponseType> {
    return this.http.put<IPrevisionPsa>(this.resourceUrl, previsionPsa, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPrevisionPsa>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPrevisionPsa[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
