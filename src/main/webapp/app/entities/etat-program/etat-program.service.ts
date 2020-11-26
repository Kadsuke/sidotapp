import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEtatProgram } from 'app/shared/model/etat-program.model';

type EntityResponseType = HttpResponse<IEtatProgram>;
type EntityArrayResponseType = HttpResponse<IEtatProgram[]>;

@Injectable({ providedIn: 'root' })
export class EtatProgramService {
  public resourceUrl = SERVER_API_URL + 'api/etat-programs';

  constructor(protected http: HttpClient) {}

  create(etatProgram: IEtatProgram): Observable<EntityResponseType> {
    return this.http.post<IEtatProgram>(this.resourceUrl, etatProgram, { observe: 'response' });
  }

  update(etatProgram: IEtatProgram): Observable<EntityResponseType> {
    return this.http.put<IEtatProgram>(this.resourceUrl, etatProgram, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEtatProgram>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEtatProgram[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
