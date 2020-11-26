import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IRefDomaine, RefDomaine } from 'app/shared/model/ref-domaine.model';
import { RefDomaineService } from './ref-domaine.service';
import { RefDomaineComponent } from './ref-domaine.component';
import { RefDomaineDetailComponent } from './ref-domaine-detail.component';
import { RefDomaineUpdateComponent } from './ref-domaine-update.component';

@Injectable({ providedIn: 'root' })
export class RefDomaineResolve implements Resolve<IRefDomaine> {
  constructor(private service: RefDomaineService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRefDomaine> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((refDomaine: HttpResponse<RefDomaine>) => {
          if (refDomaine.body) {
            return of(refDomaine.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RefDomaine());
  }
}

export const refDomaineRoute: Routes = [
  {
    path: '',
    component: RefDomaineComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.refDomaine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RefDomaineDetailComponent,
    resolve: {
      refDomaine: RefDomaineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.refDomaine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RefDomaineUpdateComponent,
    resolve: {
      refDomaine: RefDomaineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.refDomaine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RefDomaineUpdateComponent,
    resolve: {
      refDomaine: RefDomaineResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.refDomaine.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
