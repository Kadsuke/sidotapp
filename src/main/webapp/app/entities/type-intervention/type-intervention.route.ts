import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ITypeIntervention, TypeIntervention } from 'app/shared/model/type-intervention.model';
import { TypeInterventionService } from './type-intervention.service';
import { TypeInterventionComponent } from './type-intervention.component';
import { TypeInterventionDetailComponent } from './type-intervention-detail.component';
import { TypeInterventionUpdateComponent } from './type-intervention-update.component';

@Injectable({ providedIn: 'root' })
export class TypeInterventionResolve implements Resolve<ITypeIntervention> {
  constructor(private service: TypeInterventionService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypeIntervention> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((typeIntervention: HttpResponse<TypeIntervention>) => {
          if (typeIntervention.body) {
            return of(typeIntervention.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new TypeIntervention());
  }
}

export const typeInterventionRoute: Routes = [
  {
    path: '',
    component: TypeInterventionComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.typeIntervention.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: TypeInterventionDetailComponent,
    resolve: {
      typeIntervention: TypeInterventionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeIntervention.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: TypeInterventionUpdateComponent,
    resolve: {
      typeIntervention: TypeInterventionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeIntervention.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: TypeInterventionUpdateComponent,
    resolve: {
      typeIntervention: TypeInterventionResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.typeIntervention.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
