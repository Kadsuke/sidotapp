import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IDirectionRegionale, DirectionRegionale } from 'app/shared/model/direction-regionale.model';
import { DirectionRegionaleService } from './direction-regionale.service';
import { DirectionRegionaleComponent } from './direction-regionale.component';
import { DirectionRegionaleDetailComponent } from './direction-regionale-detail.component';
import { DirectionRegionaleUpdateComponent } from './direction-regionale-update.component';

@Injectable({ providedIn: 'root' })
export class DirectionRegionaleResolve implements Resolve<IDirectionRegionale> {
  constructor(private service: DirectionRegionaleService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDirectionRegionale> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((directionRegionale: HttpResponse<DirectionRegionale>) => {
          if (directionRegionale.body) {
            return of(directionRegionale.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new DirectionRegionale());
  }
}

export const directionRegionaleRoute: Routes = [
  {
    path: '',
    component: DirectionRegionaleComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.directionRegionale.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: DirectionRegionaleDetailComponent,
    resolve: {
      directionRegionale: DirectionRegionaleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.directionRegionale.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: DirectionRegionaleUpdateComponent,
    resolve: {
      directionRegionale: DirectionRegionaleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.directionRegionale.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: DirectionRegionaleUpdateComponent,
    resolve: {
      directionRegionale: DirectionRegionaleResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.directionRegionale.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
