import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBailleur, Bailleur } from 'app/shared/model/bailleur.model';
import { BailleurService } from './bailleur.service';
import { BailleurComponent } from './bailleur.component';
import { BailleurDetailComponent } from './bailleur-detail.component';
import { BailleurUpdateComponent } from './bailleur-update.component';

@Injectable({ providedIn: 'root' })
export class BailleurResolve implements Resolve<IBailleur> {
  constructor(private service: BailleurService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBailleur> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bailleur: HttpResponse<Bailleur>) => {
          if (bailleur.body) {
            return of(bailleur.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Bailleur());
  }
}

export const bailleurRoute: Routes = [
  {
    path: '',
    component: BailleurComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.bailleur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BailleurDetailComponent,
    resolve: {
      bailleur: BailleurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.bailleur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BailleurUpdateComponent,
    resolve: {
      bailleur: BailleurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.bailleur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BailleurUpdateComponent,
    resolve: {
      bailleur: BailleurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.bailleur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
