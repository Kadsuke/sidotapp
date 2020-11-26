import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeHabitation, TypeHabitation } from 'app/shared/model/type-habitation.model';
import { TypeHabitationService } from './type-habitation.service';
import { TypeHabitationComponent } from './type-habitation.component';
import { TypeHabitationDetailComponent } from './type-habitation-detail.component';
import { TypeHabitationUpdateComponent } from './type-habitation-update.component';

@Injectable({ providedIn: 'root' })
export class TypeHabitationResolve implements Resolve<ITypeHabitation> {
  constructor(private service: TypeHabitationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeHabitation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeHabitation: HttpResponse<TypeHabitation>) => {
          if (typeHabitation.body) {
            return of(typeHabitation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TypeHabitation());
  }
}

export const typeHabitationRoute: Routes = [
  {
    path: '',
    component: TypeHabitationComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.typeHabitation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeHabitationDetailComponent,
    resolve: {
      typeHabitation: TypeHabitationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeHabitation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeHabitationUpdateComponent,
    resolve: {
      typeHabitation: TypeHabitationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeHabitation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeHabitationUpdateComponent,
    resolve: {
      typeHabitation: TypeHabitationResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeHabitation.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
