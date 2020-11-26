import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeuRealisation } from 'app/shared/model/geu-realisation.model';

type EntityResponseType = HttpResponse<IGeuRealisation>;
type EntityArrayResponseType = HttpResponse<IGeuRealisation[]>;

@Injectable({ providedIn: 'root' })
export class GeuRealisationService {
  public resourceUrl = SERVER_API_URL + 'api/geu-realisations';

  constructor(protected http: HttpClient) {}

  create(geuRealisation: IGeuRealisation): Observable<EntityResponseType> {
    return this.http.post<IGeuRealisation>(this.resourceUrl, geuRealisation, { observe: 'response' });
  }

  update(geuRealisation: IGeuRealisation): Observable<EntityResponseType> {
    return this.http.put<IGeuRealisation>(this.resourceUrl, geuRealisation, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeuRealisation>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeuRealisation[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
