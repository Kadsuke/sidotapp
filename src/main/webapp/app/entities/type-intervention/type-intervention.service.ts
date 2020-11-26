import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeIntervention } from 'app/shared/model/type-intervention.model';

type EntityResponseType = HttpResponse<ITypeIntervention>;
type EntityArrayResponseType = HttpResponse<ITypeIntervention[]>;

@Injectable({ providedIn: 'root' })
export class TypeInterventionService {
  public resourceUrl = SERVER_API_URL + 'api/type-interventions';

  constructor(protected http: HttpClient) {}

  create(typeIntervention: ITypeIntervention): Observable<EntityResponseType> {
    return this.http.post<ITypeIntervention>(this.resourceUrl, typeIntervention, { observe: 'response' });
  }

  update(typeIntervention: ITypeIntervention): Observable<EntityResponseType> {
    return this.http.put<ITypeIntervention>(this.resourceUrl, typeIntervention, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeIntervention>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeIntervention[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
