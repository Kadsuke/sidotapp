import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IRefSousDomaine } from 'app/shared/model/ref-sous-domaine.model';

type EntityResponseType = HttpResponse<IRefSousDomaine>;
type EntityArrayResponseType = HttpResponse<IRefSousDomaine[]>;

@Injectable({ providedIn: 'root' })
export class RefSousDomaineService {
  public resourceUrl = SERVER_API_URL + 'api/ref-sous-domaines';

  constructor(protected http: HttpClient) {}

  create(refSousDomaine: IRefSousDomaine): Observable<EntityResponseType> {
    return this.http.post<IRefSousDomaine>(this.resourceUrl, refSousDomaine, { observe: 'response' });
  }

  update(refSousDomaine: IRefSousDomaine): Observable<EntityResponseType> {
    return this.http.put<IRefSousDomaine>(this.resourceUrl, refSousDomaine, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IRefSousDomaine>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IRefSousDomaine[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
