import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAnalyseSpecialite, AnalyseSpecialite } from 'app/shared/model/analyse-specialite.model';
import { AnalyseSpecialiteService } from './analyse-specialite.service';
import { AnalyseSpecialiteComponent } from './analyse-specialite.component';
import { AnalyseSpecialiteDetailComponent } from './analyse-specialite-detail.component';
import { AnalyseSpecialiteUpdateComponent } from './analyse-specialite-update.component';

@Injectable({ providedIn: 'root' })
export class AnalyseSpecialiteResolve implements Resolve<IAnalyseSpecialite> {
  constructor(private service: AnalyseSpecialiteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAnalyseSpecialite> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((analyseSpecialite: HttpResponse<AnalyseSpecialite>) => {
          if (analyseSpecialite.body) {
            return of(analyseSpecialite.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AnalyseSpecialite());
  }
}

export const analyseSpecialiteRoute: Routes = [
  {
    path: '',
    component: AnalyseSpecialiteComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.analyseSpecialite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AnalyseSpecialiteDetailComponent,
    resolve: {
      analyseSpecialite: AnalyseSpecialiteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.analyseSpecialite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AnalyseSpecialiteUpdateComponent,
    resolve: {
      analyseSpecialite: AnalyseSpecialiteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.analyseSpecialite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AnalyseSpecialiteUpdateComponent,
    resolve: {
      analyseSpecialite: AnalyseSpecialiteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.analyseSpecialite.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
