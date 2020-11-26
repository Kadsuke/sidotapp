import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IMacon, Macon } from 'app/shared/model/macon.model';
import { MaconService } from './macon.service';
import { MaconComponent } from './macon.component';
import { MaconDetailComponent } from './macon-detail.component';
import { MaconUpdateComponent } from './macon-update.component';

@Injectable({ providedIn: 'root' })
export class MaconResolve implements Resolve<IMacon> {
  constructor(private service: MaconService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMacon> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((macon: HttpResponse<Macon>) => {
          if (macon.body) {
            return of(macon.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Macon());
  }
}

export const maconRoute: Routes = [
  {
    path: '',
    component: MaconComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.macon.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: MaconDetailComponent,
    resolve: {
      macon: MaconResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.macon.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: MaconUpdateComponent,
    resolve: {
      macon: MaconResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.macon.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: MaconUpdateComponent,
    resolve: {
      macon: MaconResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.macon.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
