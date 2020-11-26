import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeoLocalite, GeoLocalite } from 'app/shared/model/geo-localite.model';
import { GeoLocaliteService } from './geo-localite.service';
import { GeoLocaliteComponent } from './geo-localite.component';
import { GeoLocaliteDetailComponent } from './geo-localite-detail.component';
import { GeoLocaliteUpdateComponent } from './geo-localite-update.component';

@Injectable({ providedIn: 'root' })
export class GeoLocaliteResolve implements Resolve<IGeoLocalite> {
  constructor(private service: GeoLocaliteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeoLocalite> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geoLocalite: HttpResponse<GeoLocalite>) => {
          if (geoLocalite.body) {
            return of(geoLocalite.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeoLocalite());
  }
}

export const geoLocaliteRoute: Routes = [
  {
    path: '',
    component: GeoLocaliteComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geoLocalite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeoLocaliteDetailComponent,
    resolve: {
      geoLocalite: GeoLocaliteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoLocalite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeoLocaliteUpdateComponent,
    resolve: {
      geoLocalite: GeoLocaliteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoLocalite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeoLocaliteUpdateComponent,
    resolve: {
      geoLocalite: GeoLocaliteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoLocalite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
