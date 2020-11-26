import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRefPeriodicite, RefPeriodicite } from 'app/shared/model/ref-periodicite.model';
import { RefPeriodiciteService } from './ref-periodicite.service';
import { RefPeriodiciteComponent } from './ref-periodicite.component';
import { RefPeriodiciteDetailComponent } from './ref-periodicite-detail.component';
import { RefPeriodiciteUpdateComponent } from './ref-periodicite-update.component';

@Injectable({ providedIn: 'root' })
export class RefPeriodiciteResolve implements Resolve<IRefPeriodicite> {
  constructor(private service: RefPeriodiciteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRefPeriodicite> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((refPeriodicite: HttpResponse<RefPeriodicite>) => {
          if (refPeriodicite.body) {
            return of(refPeriodicite.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RefPeriodicite());
  }
}

export const refPeriodiciteRoute: Routes = [
  {
    path: '',
    component: RefPeriodiciteComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.refPeriodicite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RefPeriodiciteDetailComponent,
    resolve: {
      refPeriodicite: RefPeriodiciteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.refPeriodicite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RefPeriodiciteUpdateComponent,
    resolve: {
      refPeriodicite: RefPeriodiciteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.refPeriodicite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RefPeriodiciteUpdateComponent,
    resolve: {
      refPeriodicite: RefPeriodiciteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.refPeriodicite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
