import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IAnalyseParametre, AnalyseParametre } from 'app/shared/model/analyse-parametre.model';
import { AnalyseParametreService } from './analyse-parametre.service';
import { AnalyseParametreComponent } from './analyse-parametre.component';
import { AnalyseParametreDetailComponent } from './analyse-parametre-detail.component';
import { AnalyseParametreUpdateComponent } from './analyse-parametre-update.component';

@Injectable({ providedIn: 'root' })
export class AnalyseParametreResolve implements Resolve<IAnalyseParametre> {
  constructor(private service: AnalyseParametreService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAnalyseParametre> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((analyseParametre: HttpResponse<AnalyseParametre>) => {
          if (analyseParametre.body) {
            return of(analyseParametre.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new AnalyseParametre());
  }
}

export const analyseParametreRoute: Routes = [
  {
    path: '',
    component: AnalyseParametreComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.analyseParametre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: AnalyseParametreDetailComponent,
    resolve: {
      analyseParametre: AnalyseParametreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.analyseParametre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: AnalyseParametreUpdateComponent,
    resolve: {
      analyseParametre: AnalyseParametreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.analyseParametre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: AnalyseParametreUpdateComponent,
    resolve: {
      analyseParametre: AnalyseParametreResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.analyseParametre.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
