import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IProduitChimique } from 'app/shared/model/produit-chimique.model';

type EntityResponseType = HttpResponse<IProduitChimique>;
type EntityArrayResponseType = HttpResponse<IProduitChimique[]>;

@Injectable({ providedIn: 'root' })
export class ProduitChimiqueService {
  public resourceUrl = SERVER_API_URL + 'api/produit-chimiques';

  constructor(protected http: HttpClient) {}

  create(produitChimique: IProduitChimique): Observable<EntityResponseType> {
    return this.http.post<IProduitChimique>(this.resourceUrl, produitChimique, { observe: 'response' });
  }

  update(produitChimique: IProduitChimique): Observable<EntityResponseType> {
    return this.http.put<IProduitChimique>(this.resourceUrl, produitChimique, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IProduitChimique>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IProduitChimique[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
