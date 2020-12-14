import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeuRealisationSTBV, GeuRealisationSTBV } from 'app/shared/model/geu-realisation-stbv.model';
import { GeuRealisationSTBVService } from './geu-realisation-stbv.service';
import { GeuRealisationSTBVComponent } from './geu-realisation-stbv.component';
import { GeuRealisationSTBVDetailComponent } from './geu-realisation-stbv-detail.component';
import { GeuRealisationSTBVUpdateComponent } from './geu-realisation-stbv-update.component';

@Injectable({ providedIn: 'root' })
export class GeuRealisationSTBVResolve implements Resolve<IGeuRealisationSTBV> {
  constructor(private service: GeuRealisationSTBVService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeuRealisationSTBV> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geuRealisationSTBV: HttpResponse<GeuRealisationSTBV>) => {
          if (geuRealisationSTBV.body) {
            return of(geuRealisationSTBV.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeuRealisationSTBV());
  }
}

export const geuRealisationSTBVRoute: Routes = [
  {
    path: '',
    component: GeuRealisationSTBVComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geuRealisationSTBV.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeuRealisationSTBVDetailComponent,
    resolve: {
      geuRealisationSTBV: GeuRealisationSTBVResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuRealisationSTBV.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeuRealisationSTBVUpdateComponent,
    resolve: {
      geuRealisationSTBV: GeuRealisationSTBVResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuRealisationSTBV.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeuRealisationSTBVUpdateComponent,
    resolve: {
      geuRealisationSTBV: GeuRealisationSTBVResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuRealisationSTBV.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
