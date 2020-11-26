import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeoCommune } from 'app/shared/model/geo-commune.model';

type EntityResponseType = HttpResponse<IGeoCommune>;
type EntityArrayResponseType = HttpResponse<IGeoCommune[]>;

@Injectable({ providedIn: 'root' })
export class GeoCommuneService {
  public resourceUrl = SERVER_API_URL + 'api/geo-communes';

  constructor(protected http: HttpClient) {}

  create(geoCommune: IGeoCommune): Observable<EntityResponseType> {
    return this.http.post<IGeoCommune>(this.resourceUrl, geoCommune, { observe: 'response' });
  }

  update(geoCommune: IGeoCommune): Observable<EntityResponseType> {
    return this.http.put<IGeoCommune>(this.resourceUrl, geoCommune, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeoCommune>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeoCommune[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
