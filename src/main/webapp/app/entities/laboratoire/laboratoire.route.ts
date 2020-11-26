import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILaboratoire, Laboratoire } from 'app/shared/model/laboratoire.model';
import { LaboratoireService } from './laboratoire.service';
import { LaboratoireComponent } from './laboratoire.component';
import { LaboratoireDetailComponent } from './laboratoire-detail.component';
import { LaboratoireUpdateComponent } from './laboratoire-update.component';

@Injectable({ providedIn: 'root' })
export class LaboratoireResolve implements Resolve<ILaboratoire> {
  constructor(private service: LaboratoireService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILaboratoire> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((laboratoire: HttpResponse<Laboratoire>) => {
          if (laboratoire.body) {
            return of(laboratoire.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Laboratoire());
  }
}

export const laboratoireRoute: Routes = [
  {
    path: '',
    component: LaboratoireComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.laboratoire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LaboratoireDetailComponent,
    resolve: {
      laboratoire: LaboratoireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.laboratoire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LaboratoireUpdateComponent,
    resolve: {
      laboratoire: LaboratoireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.laboratoire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LaboratoireUpdateComponent,
    resolve: {
      laboratoire: LaboratoireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.laboratoire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
