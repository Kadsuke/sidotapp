import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeuPrevisionSTEP, GeuPrevisionSTEP } from 'app/shared/model/geu-prevision-step.model';
import { GeuPrevisionSTEPService } from './geu-prevision-step.service';
import { GeuPrevisionSTEPComponent } from './geu-prevision-step.component';
import { GeuPrevisionSTEPDetailComponent } from './geu-prevision-step-detail.component';
import { GeuPrevisionSTEPUpdateComponent } from './geu-prevision-step-update.component';

@Injectable({ providedIn: 'root' })
export class GeuPrevisionSTEPResolve implements Resolve<IGeuPrevisionSTEP> {
  constructor(private service: GeuPrevisionSTEPService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeuPrevisionSTEP> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geuPrevisionSTEP: HttpResponse<GeuPrevisionSTEP>) => {
          if (geuPrevisionSTEP.body) {
            return of(geuPrevisionSTEP.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeuPrevisionSTEP());
  }
}

export const geuPrevisionSTEPRoute: Routes = [
  {
    path: '',
    component: GeuPrevisionSTEPComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geuPrevisionSTEP.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeuPrevisionSTEPDetailComponent,
    resolve: {
      geuPrevisionSTEP: GeuPrevisionSTEPResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuPrevisionSTEP.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeuPrevisionSTEPUpdateComponent,
    resolve: {
      geuPrevisionSTEP: GeuPrevisionSTEPResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuPrevisionSTEP.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeuPrevisionSTEPUpdateComponent,
    resolve: {
      geuPrevisionSTEP: GeuPrevisionSTEPResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuPrevisionSTEP.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
