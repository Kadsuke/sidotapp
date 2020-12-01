import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeuRaccordement, GeuRaccordement } from 'app/shared/model/geu-raccordement.model';
import { GeuRaccordementService } from './geu-raccordement.service';
import { GeuRaccordementComponent } from './geu-raccordement.component';
import { GeuRaccordementDetailComponent } from './geu-raccordement-detail.component';
import { GeuRaccordementUpdateComponent } from './geu-raccordement-update.component';

@Injectable({ providedIn: 'root' })
export class GeuRaccordementResolve implements Resolve<IGeuRaccordement> {
  constructor(private service: GeuRaccordementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeuRaccordement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geuRaccordement: HttpResponse<GeuRaccordement>) => {
          if (geuRaccordement.body) {
            return of(geuRaccordement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeuRaccordement());
  }
}

export const geuRaccordementRoute: Routes = [
  {
    path: '',
    component: GeuRaccordementComponent,
    data: {
      authorities: [Authority.SEAC],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geuRaccordement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeuRaccordementDetailComponent,
    resolve: {
      geuRaccordement: GeuRaccordementResolve,
    },
    data: {
      authorities: [Authority.SEAC],
      pageTitle: 'sidotApp.geuRaccordement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeuRaccordementUpdateComponent,
    resolve: {
      geuRaccordement: GeuRaccordementResolve,
    },
    data: {
      authorities: [Authority.SEAC],
      pageTitle: 'sidotApp.geuRaccordement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeuRaccordementUpdateComponent,
    resolve: {
      geuRaccordement: GeuRaccordementResolve,
    },
    data: {
      authorities: [Authority.SEAC],
      pageTitle: 'sidotApp.geuRaccordement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
