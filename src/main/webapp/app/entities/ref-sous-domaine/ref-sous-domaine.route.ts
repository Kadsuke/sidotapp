import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRefSousDomaine, RefSousDomaine } from 'app/shared/model/ref-sous-domaine.model';
import { RefSousDomaineService } from './ref-sous-domaine.service';
import { RefSousDomaineComponent } from './ref-sous-domaine.component';
import { RefSousDomaineDetailComponent } from './ref-sous-domaine-detail.component';
import { RefSousDomaineUpdateComponent } from './ref-sous-domaine-update.component';

@Injectable({ providedIn: 'root' })
export class RefSousDomaineResolve implements Resolve<IRefSousDomaine> {
  constructor(private service: RefSousDomaineService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRefSousDomaine> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((refSousDomaine: HttpResponse<RefSousDomaine>) => {
          if (refSousDomaine.body) {
            return of(refSousDomaine.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RefSousDomaine());
  }
}

export const refSousDomaineRoute: Routes = [
  {
    path: '',
    component: RefSousDomaineComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.refSousDomaine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RefSousDomaineDetailComponent,
    resolve: {
      refSousDomaine: RefSousDomaineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.refSousDomaine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RefSousDomaineUpdateComponent,
    resolve: {
      refSousDomaine: RefSousDomaineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.refSousDomaine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RefSousDomaineUpdateComponent,
    resolve: {
      refSousDomaine: RefSousDomaineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.refSousDomaine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
