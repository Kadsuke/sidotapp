import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAnalyseParametre } from 'app/shared/model/analyse-parametre.model';

type EntityResponseType = HttpResponse<IAnalyseParametre>;
type EntityArrayResponseType = HttpResponse<IAnalyseParametre[]>;

@Injectable({ providedIn: 'root' })
export class AnalyseParametreService {
  public resourceUrl = SERVER_API_URL + 'api/analyse-parametres';

  constructor(protected http: HttpClient) {}

  create(analyseParametre: IAnalyseParametre): Observable<EntityResponseType> {
    return this.http.post<IAnalyseParametre>(this.resourceUrl, analyseParametre, { observe: 'response' });
  }

  update(analyseParametre: IAnalyseParametre): Observable<EntityResponseType> {
    return this.http.put<IAnalyseParametre>(this.resourceUrl, analyseParametre, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAnalyseParametre>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAnalyseParametre[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
