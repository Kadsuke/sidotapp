import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeuRealisationSTBV } from 'app/shared/model/geu-realisation-stbv.model';

type EntityResponseType = HttpResponse<IGeuRealisationSTBV>;
type EntityArrayResponseType = HttpResponse<IGeuRealisationSTBV[]>;

@Injectable({ providedIn: 'root' })
export class GeuRealisationSTBVService {
  public resourceUrl = SERVER_API_URL + 'api/geu-realisation-stbvs';

  constructor(protected http: HttpClient) {}

  create(geuRealisationSTBV: IGeuRealisationSTBV): Observable<EntityResponseType> {
    return this.http.post<IGeuRealisationSTBV>(this.resourceUrl, geuRealisationSTBV, { observe: 'response' });
  }

  update(geuRealisationSTBV: IGeuRealisationSTBV): Observable<EntityResponseType> {
    return this.http.put<IGeuRealisationSTBV>(this.resourceUrl, geuRealisationSTBV, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeuRealisationSTBV>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeuRealisationSTBV[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
