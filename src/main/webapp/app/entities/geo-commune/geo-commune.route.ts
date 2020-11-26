import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeoCommune, GeoCommune } from 'app/shared/model/geo-commune.model';
import { GeoCommuneService } from './geo-commune.service';
import { GeoCommuneComponent } from './geo-commune.component';
import { GeoCommuneDetailComponent } from './geo-commune-detail.component';
import { GeoCommuneUpdateComponent } from './geo-commune-update.component';

@Injectable({ providedIn: 'root' })
export class GeoCommuneResolve implements Resolve<IGeoCommune> {
  constructor(private service: GeoCommuneService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeoCommune> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geoCommune: HttpResponse<GeoCommune>) => {
          if (geoCommune.body) {
            return of(geoCommune.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeoCommune());
  }
}

export const geoCommuneRoute: Routes = [
  {
    path: '',
    component: GeoCommuneComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geoCommune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeoCommuneDetailComponent,
    resolve: {
      geoCommune: GeoCommuneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoCommune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeoCommuneUpdateComponent,
    resolve: {
      geoCommune: GeoCommuneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoCommune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeoCommuneUpdateComponent,
    resolve: {
      geoCommune: GeoCommuneResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoCommune.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
