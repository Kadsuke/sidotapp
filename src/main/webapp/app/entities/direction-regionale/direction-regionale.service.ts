import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IDirectionRegionale } from 'app/shared/model/direction-regionale.model';

type EntityResponseType = HttpResponse<IDirectionRegionale>;
type EntityArrayResponseType = HttpResponse<IDirectionRegionale[]>;

@Injectable({ providedIn: 'root' })
export class DirectionRegionaleService {
  public resourceUrl = SERVER_API_URL + 'api/direction-regionales';

  constructor(protected http: HttpClient) {}

  create(directionRegionale: IDirectionRegionale): Observable<EntityResponseType> {
    return this.http.post<IDirectionRegionale>(this.resourceUrl, directionRegionale, { observe: 'response' });
  }

  update(directionRegionale: IDirectionRegionale): Observable<EntityResponseType> {
    return this.http.put<IDirectionRegionale>(this.resourceUrl, directionRegionale, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IDirectionRegionale>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDirectionRegionale[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
