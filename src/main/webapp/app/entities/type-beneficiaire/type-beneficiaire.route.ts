import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeBeneficiaire, TypeBeneficiaire } from 'app/shared/model/type-beneficiaire.model';
import { TypeBeneficiaireService } from './type-beneficiaire.service';
import { TypeBeneficiaireComponent } from './type-beneficiaire.component';
import { TypeBeneficiaireDetailComponent } from './type-beneficiaire-detail.component';
import { TypeBeneficiaireUpdateComponent } from './type-beneficiaire-update.component';

@Injectable({ providedIn: 'root' })
export class TypeBeneficiaireResolve implements Resolve<ITypeBeneficiaire> {
  constructor(private service: TypeBeneficiaireService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeBeneficiaire> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeBeneficiaire: HttpResponse<TypeBeneficiaire>) => {
          if (typeBeneficiaire.body) {
            return of(typeBeneficiaire.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TypeBeneficiaire());
  }
}

export const typeBeneficiaireRoute: Routes = [
  {
    path: '',
    component: TypeBeneficiaireComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.typeBeneficiaire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeBeneficiaireDetailComponent,
    resolve: {
      typeBeneficiaire: TypeBeneficiaireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeBeneficiaire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeBeneficiaireUpdateComponent,
    resolve: {
      typeBeneficiaire: TypeBeneficiaireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeBeneficiaire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeBeneficiaireUpdateComponent,
    resolve: {
      typeBeneficiaire: TypeBeneficiaireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeBeneficiaire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
