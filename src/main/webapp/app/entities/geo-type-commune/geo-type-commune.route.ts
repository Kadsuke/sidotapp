import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeoTypeCommune, GeoTypeCommune } from 'app/shared/model/geo-type-commune.model';
import { GeoTypeCommuneService } from './geo-type-commune.service';
import { GeoTypeCommuneComponent } from './geo-type-commune.component';
import { GeoTypeCommuneDetailComponent } from './geo-type-commune-detail.component';
import { GeoTypeCommuneUpdateComponent } from './geo-type-commune-update.component';

@Injectable({ providedIn: 'root' })
export class GeoTypeCommuneResolve implements Resolve<IGeoTypeCommune> {
  constructor(private service: GeoTypeCommuneService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeoTypeCommune> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geoTypeCommune: HttpResponse<GeoTypeCommune>) => {
          if (geoTypeCommune.body) {
            return of(geoTypeCommune.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeoTypeCommune());
  }
}

export const geoTypeCommuneRoute: Routes = [
  {
    path: '',
    component: GeoTypeCommuneComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geoTypeCommune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeoTypeCommuneDetailComponent,
    resolve: {
      geoTypeCommune: GeoTypeCommuneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoTypeCommune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeoTypeCommuneUpdateComponent,
    resolve: {
      geoTypeCommune: GeoTypeCommuneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoTypeCommune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeoTypeCommuneUpdateComponent,
    resolve: {
      geoTypeCommune: GeoTypeCommuneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoTypeCommune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
