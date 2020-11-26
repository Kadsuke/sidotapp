import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeuPrevisionSTBV, GeuPrevisionSTBV } from 'app/shared/model/geu-prevision-stbv.model';
import { GeuPrevisionSTBVService } from './geu-prevision-stbv.service';
import { GeuPrevisionSTBVComponent } from './geu-prevision-stbv.component';
import { GeuPrevisionSTBVDetailComponent } from './geu-prevision-stbv-detail.component';
import { GeuPrevisionSTBVUpdateComponent } from './geu-prevision-stbv-update.component';

@Injectable({ providedIn: 'root' })
export class GeuPrevisionSTBVResolve implements Resolve<IGeuPrevisionSTBV> {
  constructor(private service: GeuPrevisionSTBVService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeuPrevisionSTBV> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geuPrevisionSTBV: HttpResponse<GeuPrevisionSTBV>) => {
          if (geuPrevisionSTBV.body) {
            return of(geuPrevisionSTBV.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeuPrevisionSTBV());
  }
}

export const geuPrevisionSTBVRoute: Routes = [
  {
    path: '',
    component: GeuPrevisionSTBVComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geuPrevisionSTBV.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeuPrevisionSTBVDetailComponent,
    resolve: {
      geuPrevisionSTBV: GeuPrevisionSTBVResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuPrevisionSTBV.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeuPrevisionSTBVUpdateComponent,
    resolve: {
      geuPrevisionSTBV: GeuPrevisionSTBVResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuPrevisionSTBV.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeuPrevisionSTBVUpdateComponent,
    resolve: {
      geuPrevisionSTBV: GeuPrevisionSTBVResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuPrevisionSTBV.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
