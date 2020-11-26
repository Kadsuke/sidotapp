import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypePlainte, TypePlainte } from 'app/shared/model/type-plainte.model';
import { TypePlainteService } from './type-plainte.service';
import { TypePlainteComponent } from './type-plainte.component';
import { TypePlainteDetailComponent } from './type-plainte-detail.component';
import { TypePlainteUpdateComponent } from './type-plainte-update.component';

@Injectable({ providedIn: 'root' })
export class TypePlainteResolve implements Resolve<ITypePlainte> {
  constructor(private service: TypePlainteService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypePlainte> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typePlainte: HttpResponse<TypePlainte>) => {
          if (typePlainte.body) {
            return of(typePlainte.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TypePlainte());
  }
}

export const typePlainteRoute: Routes = [
  {
    path: '',
    component: TypePlainteComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.typePlainte.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypePlainteDetailComponent,
    resolve: {
      typePlainte: TypePlainteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typePlainte.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypePlainteUpdateComponent,
    resolve: {
      typePlainte: TypePlainteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typePlainte.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypePlainteUpdateComponent,
    resolve: {
      typePlainte: TypePlainteResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typePlainte.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
