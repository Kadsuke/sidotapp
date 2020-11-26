import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITypeBeneficiaire } from 'app/shared/model/type-beneficiaire.model';

type EntityResponseType = HttpResponse<ITypeBeneficiaire>;
type EntityArrayResponseType = HttpResponse<ITypeBeneficiaire[]>;

@Injectable({ providedIn: 'root' })
export class TypeBeneficiaireService {
  public resourceUrl = SERVER_API_URL + 'api/type-beneficiaires';

  constructor(protected http: HttpClient) {}

  create(typeBeneficiaire: ITypeBeneficiaire): Observable<EntityResponseType> {
    return this.http.post<ITypeBeneficiaire>(this.resourceUrl, typeBeneficiaire, { observe: 'response' });
  }

  update(typeBeneficiaire: ITypeBeneficiaire): Observable<EntityResponseType> {
    return this.http.put<ITypeBeneficiaire>(this.resourceUrl, typeBeneficiaire, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITypeBeneficiaire>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypeBeneficiaire[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
