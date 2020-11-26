import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IEtatProjet } from 'app/shared/model/etat-projet.model';

type EntityResponseType = HttpResponse<IEtatProjet>;
type EntityArrayResponseType = HttpResponse<IEtatProjet[]>;

@Injectable({ providedIn: 'root' })
export class EtatProjetService {
  public resourceUrl = SERVER_API_URL + 'api/etat-projets';

  constructor(protected http: HttpClient) {}

  create(etatProjet: IEtatProjet): Observable<EntityResponseType> {
    return this.http.post<IEtatProjet>(this.resourceUrl, etatProjet, { observe: 'response' });
  }

  update(etatProjet: IEtatProjet): Observable<EntityResponseType> {
    return this.http.put<IEtatProjet>(this.resourceUrl, etatProjet, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IEtatProjet>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IEtatProjet[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
