import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeoSection, GeoSection } from 'app/shared/model/geo-section.model';
import { GeoSectionService } from './geo-section.service';
import { GeoSectionComponent } from './geo-section.component';
import { GeoSectionDetailComponent } from './geo-section-detail.component';
import { GeoSectionUpdateComponent } from './geo-section-update.component';

@Injectable({ providedIn: 'root' })
export class GeoSectionResolve implements Resolve<IGeoSection> {
  constructor(private service: GeoSectionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeoSection> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geoSection: HttpResponse<GeoSection>) => {
          if (geoSection.body) {
            return of(geoSection.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeoSection());
  }
}

export const geoSectionRoute: Routes = [
  {
    path: '',
    component: GeoSectionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geoSection.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeoSectionDetailComponent,
    resolve: {
      geoSection: GeoSectionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoSection.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeoSectionUpdateComponent,
    resolve: {
      geoSection: GeoSectionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoSection.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeoSectionUpdateComponent,
    resolve: {
      geoSection: GeoSectionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoSection.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
