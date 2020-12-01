import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeuSTEP, GeuSTEP } from 'app/shared/model/geu-step.model';
import { GeuSTEPService } from './geu-step.service';
import { GeuSTEPComponent } from './geu-step.component';
import { GeuSTEPDetailComponent } from './geu-step-detail.component';
import { GeuSTEPUpdateComponent } from './geu-step-update.component';

@Injectable({ providedIn: 'root' })
export class GeuSTEPResolve implements Resolve<IGeuSTEP> {
  constructor(private service: GeuSTEPService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeuSTEP> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geuSTEP: HttpResponse<GeuSTEP>) => {
          if (geuSTEP.body) {
            return of(geuSTEP.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeuSTEP());
  }
}

export const geuSTEPRoute: Routes = [
  {
    path: '',
    component: GeuSTEPComponent,
    data: {
      authorities: [Authority.SEAC],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geuSTEP.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeuSTEPDetailComponent,
    resolve: {
      geuSTEP: GeuSTEPResolve,
    },
    data: {
      authorities: [Authority.SEAC],
      pageTitle: 'sidotApp.geuSTEP.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeuSTEPUpdateComponent,
    resolve: {
      geuSTEP: GeuSTEPResolve,
    },
    data: {
      authorities: [Authority.SEAC],
      pageTitle: 'sidotApp.geuSTEP.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeuSTEPUpdateComponent,
    resolve: {
      geuSTEP: GeuSTEPResolve,
    },
    data: {
      authorities: [Authority.SEAC],
      pageTitle: 'sidotApp.geuSTEP.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
