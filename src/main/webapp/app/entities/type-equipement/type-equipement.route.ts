import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeEquipement, TypeEquipement } from 'app/shared/model/type-equipement.model';
import { TypeEquipementService } from './type-equipement.service';
import { TypeEquipementComponent } from './type-equipement.component';
import { TypeEquipementDetailComponent } from './type-equipement-detail.component';
import { TypeEquipementUpdateComponent } from './type-equipement-update.component';

@Injectable({ providedIn: 'root' })
export class TypeEquipementResolve implements Resolve<ITypeEquipement> {
  constructor(private service: TypeEquipementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeEquipement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeEquipement: HttpResponse<TypeEquipement>) => {
          if (typeEquipement.body) {
            return of(typeEquipement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TypeEquipement());
  }
}

export const typeEquipementRoute: Routes = [
  {
    path: '',
    component: TypeEquipementComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.typeEquipement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeEquipementDetailComponent,
    resolve: {
      typeEquipement: TypeEquipementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeEquipement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeEquipementUpdateComponent,
    resolve: {
      typeEquipement: TypeEquipementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeEquipement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeEquipementUpdateComponent,
    resolve: {
      typeEquipement: TypeEquipementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeEquipement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
