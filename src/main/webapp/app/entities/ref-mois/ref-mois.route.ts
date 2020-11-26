import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRefMois, RefMois } from 'app/shared/model/ref-mois.model';
import { RefMoisService } from './ref-mois.service';
import { RefMoisComponent } from './ref-mois.component';
import { RefMoisDetailComponent } from './ref-mois-detail.component';
import { RefMoisUpdateComponent } from './ref-mois-update.component';

@Injectable({ providedIn: 'root' })
export class RefMoisResolve implements Resolve<IRefMois> {
  constructor(private service: RefMoisService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRefMois> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((refMois: HttpResponse<RefMois>) => {
          if (refMois.body) {
            return of(refMois.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RefMois());
  }
}

export const refMoisRoute: Routes = [
  {
    path: '',
    component: RefMoisComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.refMois.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RefMoisDetailComponent,
    resolve: {
      refMois: RefMoisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.refMois.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RefMoisUpdateComponent,
    resolve: {
      refMois: RefMoisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.refMois.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RefMoisUpdateComponent,
    resolve: {
      refMois: RefMoisResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.refMois.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
