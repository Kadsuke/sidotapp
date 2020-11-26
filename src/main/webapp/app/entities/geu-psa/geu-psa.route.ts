import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeuPSA, GeuPSA } from 'app/shared/model/geu-psa.model';
import { GeuPSAService } from './geu-psa.service';
import { GeuPSAComponent } from './geu-psa.component';
import { GeuPSADetailComponent } from './geu-psa-detail.component';
import { GeuPSAUpdateComponent } from './geu-psa-update.component';

@Injectable({ providedIn: 'root' })
export class GeuPSAResolve implements Resolve<IGeuPSA> {
  constructor(private service: GeuPSAService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeuPSA> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geuPSA: HttpResponse<GeuPSA>) => {
          if (geuPSA.body) {
            return of(geuPSA.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeuPSA());
  }
}

export const geuPSARoute: Routes = [
  {
    path: '',
    component: GeuPSAComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geuPSA.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeuPSADetailComponent,
    resolve: {
      geuPSA: GeuPSAResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuPSA.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeuPSAUpdateComponent,
    resolve: {
      geuPSA: GeuPSAResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuPSA.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeuPSAUpdateComponent,
    resolve: {
      geuPSA: GeuPSAResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuPSA.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
