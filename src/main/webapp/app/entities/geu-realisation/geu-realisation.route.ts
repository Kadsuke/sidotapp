import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeuRealisation, GeuRealisation } from 'app/shared/model/geu-realisation.model';
import { GeuRealisationService } from './geu-realisation.service';
import { GeuRealisationComponent } from './geu-realisation.component';
import { GeuRealisationDetailComponent } from './geu-realisation-detail.component';
import { GeuRealisationUpdateComponent } from './geu-realisation-update.component';

@Injectable({ providedIn: 'root' })
export class GeuRealisationResolve implements Resolve<IGeuRealisation> {
  constructor(private service: GeuRealisationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeuRealisation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geuRealisation: HttpResponse<GeuRealisation>) => {
          if (geuRealisation.body) {
            return of(geuRealisation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeuRealisation());
  }
}

export const geuRealisationRoute: Routes = [
  {
    path: '',
    component: GeuRealisationComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geuRealisation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeuRealisationDetailComponent,
    resolve: {
      geuRealisation: GeuRealisationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuRealisation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeuRealisationUpdateComponent,
    resolve: {
      geuRealisation: GeuRealisationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuRealisation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeuRealisationUpdateComponent,
    resolve: {
      geuRealisation: GeuRealisationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuRealisation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
