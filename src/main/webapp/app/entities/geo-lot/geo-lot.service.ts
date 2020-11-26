import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeoLot } from 'app/shared/model/geo-lot.model';

type EntityResponseType = HttpResponse<IGeoLot>;
type EntityArrayResponseType = HttpResponse<IGeoLot[]>;

@Injectable({ providedIn: 'root' })
export class GeoLotService {
  public resourceUrl = SERVER_API_URL + 'api/geo-lots';

  constructor(protected http: HttpClient) {}

  create(geoLot: IGeoLot): Observable<EntityResponseType> {
    return this.http.post<IGeoLot>(this.resourceUrl, geoLot, { observe: 'response' });
  }

  update(geoLot: IGeoLot): Observable<EntityResponseType> {
    return this.http.put<IGeoLot>(this.resourceUrl, geoLot, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeoLot>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeoLot[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
