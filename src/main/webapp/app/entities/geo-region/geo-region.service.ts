import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeoRegion } from 'app/shared/model/geo-region.model';

type EntityResponseType = HttpResponse<IGeoRegion>;
type EntityArrayResponseType = HttpResponse<IGeoRegion[]>;

@Injectable({ providedIn: 'root' })
export class GeoRegionService {
  public resourceUrl = SERVER_API_URL + 'api/geo-regions';

  constructor(protected http: HttpClient) {}

  create(geoRegion: IGeoRegion): Observable<EntityResponseType> {
    return this.http.post<IGeoRegion>(this.resourceUrl, geoRegion, { observe: 'response' });
  }

  update(geoRegion: IGeoRegion): Observable<EntityResponseType> {
    return this.http.put<IGeoRegion>(this.resourceUrl, geoRegion, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeoRegion>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeoRegion[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
