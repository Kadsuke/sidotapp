import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ICategorieRessources } from 'app/shared/model/categorie-ressources.model';

type EntityResponseType = HttpResponse<ICategorieRessources>;
type EntityArrayResponseType = HttpResponse<ICategorieRessources[]>;

@Injectable({ providedIn: 'root' })
export class CategorieRessourcesService {
  public resourceUrl = SERVER_API_URL + 'api/categorie-ressources';

  constructor(protected http: HttpClient) {}

  create(categorieRessources: ICategorieRessources): Observable<EntityResponseType> {
    return this.http.post<ICategorieRessources>(this.resourceUrl, categorieRessources, { observe: 'response' });
  }

  update(categorieRessources: ICategorieRessources): Observable<EntityResponseType> {
    return this.http.put<ICategorieRessources>(this.resourceUrl, categorieRessources, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ICategorieRessources>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ICategorieRessources[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
