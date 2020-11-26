import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeoLot, GeoLot } from 'app/shared/model/geo-lot.model';
import { GeoLotService } from './geo-lot.service';
import { GeoLotComponent } from './geo-lot.component';
import { GeoLotDetailComponent } from './geo-lot-detail.component';
import { GeoLotUpdateComponent } from './geo-lot-update.component';

@Injectable({ providedIn: 'root' })
export class GeoLotResolve implements Resolve<IGeoLot> {
  constructor(private service: GeoLotService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeoLot> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geoLot: HttpResponse<GeoLot>) => {
          if (geoLot.body) {
            return of(geoLot.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeoLot());
  }
}

export const geoLotRoute: Routes = [
  {
    path: '',
    component: GeoLotComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geoLot.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeoLotDetailComponent,
    resolve: {
      geoLot: GeoLotResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoLot.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeoLotUpdateComponent,
    resolve: {
      geoLot: GeoLotResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoLot.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeoLotUpdateComponent,
    resolve: {
      geoLot: GeoLotResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoLot.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
