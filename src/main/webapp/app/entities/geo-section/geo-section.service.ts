import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeoSection } from 'app/shared/model/geo-section.model';

type EntityResponseType = HttpResponse<IGeoSection>;
type EntityArrayResponseType = HttpResponse<IGeoSection[]>;

@Injectable({ providedIn: 'root' })
export class GeoSectionService {
  public resourceUrl = SERVER_API_URL + 'api/geo-sections';

  constructor(protected http: HttpClient) {}

  create(geoSection: IGeoSection): Observable<EntityResponseType> {
    return this.http.post<IGeoSection>(this.resourceUrl, geoSection, { observe: 'response' });
  }

  update(geoSection: IGeoSection): Observable<EntityResponseType> {
    return this.http.put<IGeoSection>(this.resourceUrl, geoSection, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeoSection>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeoSection[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
