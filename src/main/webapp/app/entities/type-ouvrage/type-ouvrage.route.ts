import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeOuvrage, TypeOuvrage } from 'app/shared/model/type-ouvrage.model';
import { TypeOuvrageService } from './type-ouvrage.service';
import { TypeOuvrageComponent } from './type-ouvrage.component';
import { TypeOuvrageDetailComponent } from './type-ouvrage-detail.component';
import { TypeOuvrageUpdateComponent } from './type-ouvrage-update.component';

@Injectable({ providedIn: 'root' })
export class TypeOuvrageResolve implements Resolve<ITypeOuvrage> {
  constructor(private service: TypeOuvrageService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeOuvrage> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeOuvrage: HttpResponse<TypeOuvrage>) => {
          if (typeOuvrage.body) {
            return of(typeOuvrage.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TypeOuvrage());
  }
}

export const typeOuvrageRoute: Routes = [
  {
    path: '',
    component: TypeOuvrageComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.typeOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeOuvrageDetailComponent,
    resolve: {
      typeOuvrage: TypeOuvrageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeOuvrageUpdateComponent,
    resolve: {
      typeOuvrage: TypeOuvrageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeOuvrageUpdateComponent,
    resolve: {
      typeOuvrage: TypeOuvrageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
