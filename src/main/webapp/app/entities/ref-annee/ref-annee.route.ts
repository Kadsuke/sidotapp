import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRefAnnee, RefAnnee } from 'app/shared/model/ref-annee.model';
import { RefAnneeService } from './ref-annee.service';
import { RefAnneeComponent } from './ref-annee.component';
import { RefAnneeDetailComponent } from './ref-annee-detail.component';
import { RefAnneeUpdateComponent } from './ref-annee-update.component';

@Injectable({ providedIn: 'root' })
export class RefAnneeResolve implements Resolve<IRefAnnee> {
  constructor(private service: RefAnneeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRefAnnee> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((refAnnee: HttpResponse<RefAnnee>) => {
          if (refAnnee.body) {
            return of(refAnnee.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RefAnnee());
  }
}

export const refAnneeRoute: Routes = [
  {
    path: '',
    component: RefAnneeComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.refAnnee.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RefAnneeDetailComponent,
    resolve: {
      refAnnee: RefAnneeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.refAnnee.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RefAnneeUpdateComponent,
    resolve: {
      refAnnee: RefAnneeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.refAnnee.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RefAnneeUpdateComponent,
    resolve: {
      refAnnee: RefAnneeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.refAnnee.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
