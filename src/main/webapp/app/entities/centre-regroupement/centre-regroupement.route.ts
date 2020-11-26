import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICentreRegroupement, CentreRegroupement } from 'app/shared/model/centre-regroupement.model';
import { CentreRegroupementService } from './centre-regroupement.service';
import { CentreRegroupementComponent } from './centre-regroupement.component';
import { CentreRegroupementDetailComponent } from './centre-regroupement-detail.component';
import { CentreRegroupementUpdateComponent } from './centre-regroupement-update.component';

@Injectable({ providedIn: 'root' })
export class CentreRegroupementResolve implements Resolve<ICentreRegroupement> {
  constructor(private service: CentreRegroupementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICentreRegroupement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((centreRegroupement: HttpResponse<CentreRegroupement>) => {
          if (centreRegroupement.body) {
            return of(centreRegroupement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CentreRegroupement());
  }
}

export const centreRegroupementRoute: Routes = [
  {
    path: '',
    component: CentreRegroupementComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.centreRegroupement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CentreRegroupementDetailComponent,
    resolve: {
      centreRegroupement: CentreRegroupementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.centreRegroupement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CentreRegroupementUpdateComponent,
    resolve: {
      centreRegroupement: CentreRegroupementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.centreRegroupement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CentreRegroupementUpdateComponent,
    resolve: {
      centreRegroupement: CentreRegroupementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.centreRegroupement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
