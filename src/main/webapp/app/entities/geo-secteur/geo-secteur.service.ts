import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeoSecteur } from 'app/shared/model/geo-secteur.model';

type EntityResponseType = HttpResponse<IGeoSecteur>;
type EntityArrayResponseType = HttpResponse<IGeoSecteur[]>;

@Injectable({ providedIn: 'root' })
export class GeoSecteurService {
  public resourceUrl = SERVER_API_URL + 'api/geo-secteurs';

  constructor(protected http: HttpClient) {}

  create(geoSecteur: IGeoSecteur): Observable<EntityResponseType> {
    return this.http.post<IGeoSecteur>(this.resourceUrl, geoSecteur, { observe: 'response' });
  }

  update(geoSecteur: IGeoSecteur): Observable<EntityResponseType> {
    return this.http.put<IGeoSecteur>(this.resourceUrl, geoSecteur, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IGeoSecteur>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IGeoSecteur[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
