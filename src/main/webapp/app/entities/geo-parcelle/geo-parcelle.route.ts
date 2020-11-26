import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeoParcelle, GeoParcelle } from 'app/shared/model/geo-parcelle.model';
import { GeoParcelleService } from './geo-parcelle.service';
import { GeoParcelleComponent } from './geo-parcelle.component';
import { GeoParcelleDetailComponent } from './geo-parcelle-detail.component';
import { GeoParcelleUpdateComponent } from './geo-parcelle-update.component';

@Injectable({ providedIn: 'root' })
export class GeoParcelleResolve implements Resolve<IGeoParcelle> {
  constructor(private service: GeoParcelleService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeoParcelle> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geoParcelle: HttpResponse<GeoParcelle>) => {
          if (geoParcelle.body) {
            return of(geoParcelle.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeoParcelle());
  }
}

export const geoParcelleRoute: Routes = [
  {
    path: '',
    component: GeoParcelleComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geoParcelle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeoParcelleDetailComponent,
    resolve: {
      geoParcelle: GeoParcelleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoParcelle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeoParcelleUpdateComponent,
    resolve: {
      geoParcelle: GeoParcelleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoParcelle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeoParcelleUpdateComponent,
    resolve: {
      geoParcelle: GeoParcelleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoParcelle.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
