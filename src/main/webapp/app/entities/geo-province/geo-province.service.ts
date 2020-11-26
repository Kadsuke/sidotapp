import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeoProvince } from 'app/shared/model/geo-province.model';

type EntityResponseType = HttpResponse<IGeoProvince>;
type EntityArrayResponseType = HttpResponse<IGeoProvince[]>;

@Injectable({ providedIn: 'root' })
export class GeoProvinceService {
  public resourceUrl = SERVER_API_URL + 'api/geo-provinces';

  constructor(protected http: HttpClient) {}

  create(geoProvince: IGeoProvince): Observable<EntityResponseType> {
    return this.http.post<IGeoProvince>(this.resourceUrl, geoProvince, { observe: 'response' });
  }

  update(geoProvince: IGeoProvince): Observable<EntityResponseType> {
    return this.http.put<IGeoProvince>(this.resourceUrl, geoProvince, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeoProvince>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeoProvince[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
