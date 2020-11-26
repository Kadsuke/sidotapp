import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeoParcelle } from 'app/shared/model/geo-parcelle.model';

type EntityResponseType = HttpResponse<IGeoParcelle>;
type EntityArrayResponseType = HttpResponse<IGeoParcelle[]>;

@Injectable({ providedIn: 'root' })
export class GeoParcelleService {
  public resourceUrl = SERVER_API_URL + 'api/geo-parcelles';

  constructor(protected http: HttpClient) {}

  create(geoParcelle: IGeoParcelle): Observable<EntityResponseType> {
    return this.http.post<IGeoParcelle>(this.resourceUrl, geoParcelle, { observe: 'response' });
  }

  update(geoParcelle: IGeoParcelle): Observable<EntityResponseType> {
    return this.http.put<IGeoParcelle>(this.resourceUrl, geoParcelle, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeoParcelle>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeoParcelle[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
