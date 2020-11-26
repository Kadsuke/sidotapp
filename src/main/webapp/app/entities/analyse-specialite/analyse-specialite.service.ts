import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IAnalyseSpecialite } from 'app/shared/model/analyse-specialite.model';

type EntityResponseType = HttpResponse<IAnalyseSpecialite>;
type EntityArrayResponseType = HttpResponse<IAnalyseSpecialite[]>;

@Injectable({ providedIn: 'root' })
export class AnalyseSpecialiteService {
  public resourceUrl = SERVER_API_URL + 'api/analyse-specialites';

  constructor(protected http: HttpClient) {}

  create(analyseSpecialite: IAnalyseSpecialite): Observable<EntityResponseType> {
    return this.http.post<IAnalyseSpecialite>(this.resourceUrl, analyseSpecialite, { observe: 'response' });
  }

  update(analyseSpecialite: IAnalyseSpecialite): Observable<EntityResponseType> {
    return this.http.put<IAnalyseSpecialite>(this.resourceUrl, analyseSpecialite, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IAnalyseSpecialite>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAnalyseSpecialite[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
