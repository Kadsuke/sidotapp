import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeuRaccordement } from 'app/shared/model/geu-raccordement.model';

type EntityResponseType = HttpResponse<IGeuRaccordement>;
type EntityArrayResponseType = HttpResponse<IGeuRaccordement[]>;

@Injectable({ providedIn: 'root' })
export class GeuRaccordementService {
  public resourceUrl = SERVER_API_URL + 'api/geu-raccordements';

  constructor(protected http: HttpClient) {}

  create(geuRaccordement: IGeuRaccordement): Observable<EntityResponseType> {
    return this.http.post<IGeuRaccordement>(this.resourceUrl, geuRaccordement, { observe: 'response' });
  }

  update(geuRaccordement: IGeuRaccordement): Observable<EntityResponseType> {
    return this.http.put<IGeuRaccordement>(this.resourceUrl, geuRaccordement, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeuRaccordement>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeuRaccordement[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
