import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeoLocalite } from 'app/shared/model/geo-localite.model';

type EntityResponseType = HttpResponse<IGeoLocalite>;
type EntityArrayResponseType = HttpResponse<IGeoLocalite[]>;

@Injectable({ providedIn: 'root' })
export class GeoLocaliteService {
  public resourceUrl = SERVER_API_URL + 'api/geo-localites';

  constructor(protected http: HttpClient) {}

  create(geoLocalite: IGeoLocalite): Observable<EntityResponseType> {
    return this.http.post<IGeoLocalite>(this.resourceUrl, geoLocalite, { observe: 'response' });
  }

  update(geoLocalite: IGeoLocalite): Observable<EntityResponseType> {
    return this.http.put<IGeoLocalite>(this.resourceUrl, geoLocalite, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeoLocalite>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeoLocalite[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
