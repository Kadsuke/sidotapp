import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITacherons, Tacherons } from 'app/shared/model/tacherons.model';
import { TacheronsService } from './tacherons.service';
import { TacheronsComponent } from './tacherons.component';
import { TacheronsDetailComponent } from './tacherons-detail.component';
import { TacheronsUpdateComponent } from './tacherons-update.component';

@Injectable({ providedIn: 'root' })
export class TacheronsResolve implements Resolve<ITacherons> {
  constructor(private service: TacheronsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITacherons> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((tacherons: HttpResponse<Tacherons>) => {
          if (tacherons.body) {
            return of(tacherons.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Tacherons());
  }
}

export const tacheronsRoute: Routes = [
  {
    path: '',
    component: TacheronsComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.tacherons.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TacheronsDetailComponent,
    resolve: {
      tacherons: TacheronsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.tacherons.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TacheronsUpdateComponent,
    resolve: {
      tacherons: TacheronsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.tacherons.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TacheronsUpdateComponent,
    resolve: {
      tacherons: TacheronsResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.tacherons.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
