import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeuSTBV, GeuSTBV } from 'app/shared/model/geu-stbv.model';
import { GeuSTBVService } from './geu-stbv.service';
import { GeuSTBVComponent } from './geu-stbv.component';
import { GeuSTBVDetailComponent } from './geu-stbv-detail.component';
import { GeuSTBVUpdateComponent } from './geu-stbv-update.component';

@Injectable({ providedIn: 'root' })
export class GeuSTBVResolve implements Resolve<IGeuSTBV> {
  constructor(private service: GeuSTBVService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeuSTBV> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geuSTBV: HttpResponse<GeuSTBV>) => {
          if (geuSTBV.body) {
            return of(geuSTBV.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeuSTBV());
  }
}

export const geuSTBVRoute: Routes = [
  {
    path: '',
    component: GeuSTBVComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geuSTBV.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeuSTBVDetailComponent,
    resolve: {
      geuSTBV: GeuSTBVResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuSTBV.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeuSTBVUpdateComponent,
    resolve: {
      geuSTBV: GeuSTBVResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuSTBV.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeuSTBVUpdateComponent,
    resolve: {
      geuSTBV: GeuSTBVResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuSTBV.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
