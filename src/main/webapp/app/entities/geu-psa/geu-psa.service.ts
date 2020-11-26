import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeuPSA } from 'app/shared/model/geu-psa.model';

type EntityResponseType = HttpResponse<IGeuPSA>;
type EntityArrayResponseType = HttpResponse<IGeuPSA[]>;

@Injectable({ providedIn: 'root' })
export class GeuPSAService {
  public resourceUrl = SERVER_API_URL + 'api/geu-psas';

  constructor(protected http: HttpClient) {}

  create(geuPSA: IGeuPSA): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(geuPSA);
    return this.http
      .post<IGeuPSA>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(geuPSA: IGeuPSA): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(geuPSA);
    return this.http
      .put<IGeuPSA>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGeuPSA>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGeuPSA[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(geuPSA: IGeuPSA): IGeuPSA {
    const copy: IGeuPSA = Object.assign({}, geuPSA, {
      dateElaboration: geuPSA.dateElaboration && geuPSA.dateElaboration.isValid() ? geuPSA.dateElaboration.toJSON() : undefined,
      dateMiseEnOeuvre: geuPSA.dateMiseEnOeuvre && geuPSA.dateMiseEnOeuvre.isValid() ? geuPSA.dateMiseEnOeuvre.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.dateElaboration = res.body.dateElaboration ? moment(res.body.dateElaboration) : undefined;
      res.body.dateMiseEnOeuvre = res.body.dateMiseEnOeuvre ? moment(res.body.dateMiseEnOeuvre) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((geuPSA: IGeuPSA) => {
        geuPSA.dateElaboration = geuPSA.dateElaboration ? moment(geuPSA.dateElaboration) : undefined;
        geuPSA.dateMiseEnOeuvre = geuPSA.dateMiseEnOeuvre ? moment(geuPSA.dateMiseEnOeuvre) : undefined;
      });
    }
    return res;
  }
}
