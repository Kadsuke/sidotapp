import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';

type EntityResponseType = HttpResponse<IGeuAaOuvrage>;
type EntityArrayResponseType = HttpResponse<IGeuAaOuvrage[]>;

@Injectable({ providedIn: 'root' })
export class GeuAaOuvrageService {
  public resourceUrl = SERVER_API_URL + 'api/geu-aa-ouvrages';

  constructor(protected http: HttpClient) {}

  create(geuAaOuvrage: IGeuAaOuvrage): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(geuAaOuvrage);
    return this.http
      .post<IGeuAaOuvrage>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(geuAaOuvrage: IGeuAaOuvrage): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(geuAaOuvrage);
    return this.http
      .put<IGeuAaOuvrage>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGeuAaOuvrage>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGeuAaOuvrage[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(geuAaOuvrage: IGeuAaOuvrage): IGeuAaOuvrage {
    const copy: IGeuAaOuvrage = Object.assign({}, geuAaOuvrage, {
      dateRemiseDevis:
        geuAaOuvrage.dateRemiseDevis && geuAaOuvrage.dateRemiseDevis.isValid() ? geuAaOuvrage.dateRemiseDevis.toJSON() : undefined,
      dateDebutTravaux:
        geuAaOuvrage.dateDebutTravaux && geuAaOuvrage.dateDebutTravaux.isValid() ? geuAaOuvrage.dateDebutTravaux.toJSON() : undefined,
      dateFinTravaux:
        geuAaOuvrage.dateFinTravaux && geuAaOuvrage.dateFinTravaux.isValid() ? geuAaOuvrage.dateFinTravaux.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateRemiseDevis = res.body.dateRemiseDevis ? moment(res.body.dateRemiseDevis) : undefined;
      res.body.dateDebutTravaux = res.body.dateDebutTravaux ? moment(res.body.dateDebutTravaux) : undefined;
      res.body.dateFinTravaux = res.body.dateFinTravaux ? moment(res.body.dateFinTravaux) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((geuAaOuvrage: IGeuAaOuvrage) => {
        geuAaOuvrage.dateRemiseDevis = geuAaOuvrage.dateRemiseDevis ? moment(geuAaOuvrage.dateRemiseDevis) : undefined;
        geuAaOuvrage.dateDebutTravaux = geuAaOuvrage.dateDebutTravaux ? moment(geuAaOuvrage.dateDebutTravaux) : undefined;
        geuAaOuvrage.dateFinTravaux = geuAaOuvrage.dateFinTravaux ? moment(geuAaOuvrage.dateFinTravaux) : undefined;
      });
    }
    return res;
  }
}
