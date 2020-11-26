import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeoRegion, GeoRegion } from 'app/shared/model/geo-region.model';
import { GeoRegionService } from './geo-region.service';
import { GeoRegionComponent } from './geo-region.component';
import { GeoRegionDetailComponent } from './geo-region-detail.component';
import { GeoRegionUpdateComponent } from './geo-region-update.component';

@Injectable({ providedIn: 'root' })
export class GeoRegionResolve implements Resolve<IGeoRegion> {
  constructor(private service: GeoRegionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeoRegion> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geoRegion: HttpResponse<GeoRegion>) => {
          if (geoRegion.body) {
            return of(geoRegion.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeoRegion());
  }
}

export const geoRegionRoute: Routes = [
  {
    path: '',
    component: GeoRegionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geoRegion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeoRegionDetailComponent,
    resolve: {
      geoRegion: GeoRegionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoRegion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeoRegionUpdateComponent,
    resolve: {
      geoRegion: GeoRegionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoRegion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeoRegionUpdateComponent,
    resolve: {
      geoRegion: GeoRegionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoRegion.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
