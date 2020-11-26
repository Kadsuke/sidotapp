import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IGeuPrevisionSTEP } from 'app/shared/model/geu-prevision-step.model';

type EntityResponseType = HttpResponse<IGeuPrevisionSTEP>;
type EntityArrayResponseType = HttpResponse<IGeuPrevisionSTEP[]>;

@Injectable({ providedIn: 'root' })
export class GeuPrevisionSTEPService {
  public resourceUrl = SERVER_API_URL + 'api/geu-prevision-steps';

  constructor(protected http: HttpClient) {}

  create(geuPrevisionSTEP: IGeuPrevisionSTEP): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(geuPrevisionSTEP);
    return this.http
      .post<IGeuPrevisionSTEP>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(geuPrevisionSTEP: IGeuPrevisionSTEP): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(geuPrevisionSTEP);
    return this.http
      .put<IGeuPrevisionSTEP>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IGeuPrevisionSTEP>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IGeuPrevisionSTEP[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(geuPrevisionSTEP: IGeuPrevisionSTEP): IGeuPrevisionSTEP {
    const copy: IGeuPrevisionSTEP = Object.assign({}, geuPrevisionSTEP, {
      datePrevStep:
        geuPrevisionSTEP.datePrevStep && geuPrevisionSTEP.datePrevStep.isValid() ? geuPrevisionSTEP.datePrevStep.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.datePrevStep = res.body.datePrevStep ? moment(res.body.datePrevStep) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((geuPrevisionSTEP: IGeuPrevisionSTEP) => {
        geuPrevisionSTEP.datePrevStep = geuPrevisionSTEP.datePrevStep ? moment(geuPrevisionSTEP.datePrevStep) : undefined;
      });
    }
    return res;
  }
}
