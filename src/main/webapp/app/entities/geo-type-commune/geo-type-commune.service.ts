import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeoTypeCommune } from 'app/shared/model/geo-type-commune.model';

type EntityResponseType = HttpResponse<IGeoTypeCommune>;
type EntityArrayResponseType = HttpResponse<IGeoTypeCommune[]>;

@Injectable({ providedIn: 'root' })
export class GeoTypeCommuneService {
  public resourceUrl = SERVER_API_URL + 'api/geo-type-communes';

  constructor(protected http: HttpClient) {}

  create(geoTypeCommune: IGeoTypeCommune): Observable<EntityResponseType> {
    return this.http.post<IGeoTypeCommune>(this.resourceUrl, geoTypeCommune, { observe: 'response' });
  }

  update(geoTypeCommune: IGeoTypeCommune): Observable<EntityResponseType> {
    return this.http.put<IGeoTypeCommune>(this.resourceUrl, geoTypeCommune, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeoTypeCommune>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeoTypeCommune[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
