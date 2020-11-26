import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeoProvince, GeoProvince } from 'app/shared/model/geo-province.model';
import { GeoProvinceService } from './geo-province.service';
import { GeoProvinceComponent } from './geo-province.component';
import { GeoProvinceDetailComponent } from './geo-province-detail.component';
import { GeoProvinceUpdateComponent } from './geo-province-update.component';

@Injectable({ providedIn: 'root' })
export class GeoProvinceResolve implements Resolve<IGeoProvince> {
  constructor(private service: GeoProvinceService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeoProvince> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geoProvince: HttpResponse<GeoProvince>) => {
          if (geoProvince.body) {
            return of(geoProvince.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeoProvince());
  }
}

export const geoProvinceRoute: Routes = [
  {
    path: '',
    component: GeoProvinceComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geoProvince.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeoProvinceDetailComponent,
    resolve: {
      geoProvince: GeoProvinceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoProvince.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeoProvinceUpdateComponent,
    resolve: {
      geoProvince: GeoProvinceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoProvince.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeoProvinceUpdateComponent,
    resolve: {
      geoProvince: GeoProvinceResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoProvince.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
