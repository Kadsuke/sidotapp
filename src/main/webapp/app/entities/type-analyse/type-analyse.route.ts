import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeAnalyse, TypeAnalyse } from 'app/shared/model/type-analyse.model';
import { TypeAnalyseService } from './type-analyse.service';
import { TypeAnalyseComponent } from './type-analyse.component';
import { TypeAnalyseDetailComponent } from './type-analyse-detail.component';
import { TypeAnalyseUpdateComponent } from './type-analyse-update.component';

@Injectable({ providedIn: 'root' })
export class TypeAnalyseResolve implements Resolve<ITypeAnalyse> {
  constructor(private service: TypeAnalyseService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeAnalyse> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeAnalyse: HttpResponse<TypeAnalyse>) => {
          if (typeAnalyse.body) {
            return of(typeAnalyse.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TypeAnalyse());
  }
}

export const typeAnalyseRoute: Routes = [
  {
    path: '',
    component: TypeAnalyseComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.typeAnalyse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeAnalyseDetailComponent,
    resolve: {
      typeAnalyse: TypeAnalyseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeAnalyse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeAnalyseUpdateComponent,
    resolve: {
      typeAnalyse: TypeAnalyseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeAnalyse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeAnalyseUpdateComponent,
    resolve: {
      typeAnalyse: TypeAnalyseResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeAnalyse.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
