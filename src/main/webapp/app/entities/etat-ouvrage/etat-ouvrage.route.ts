import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEtatOuvrage, EtatOuvrage } from 'app/shared/model/etat-ouvrage.model';
import { EtatOuvrageService } from './etat-ouvrage.service';
import { EtatOuvrageComponent } from './etat-ouvrage.component';
import { EtatOuvrageDetailComponent } from './etat-ouvrage-detail.component';
import { EtatOuvrageUpdateComponent } from './etat-ouvrage-update.component';

@Injectable({ providedIn: 'root' })
export class EtatOuvrageResolve implements Resolve<IEtatOuvrage> {
  constructor(private service: EtatOuvrageService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEtatOuvrage> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((etatOuvrage: HttpResponse<EtatOuvrage>) => {
          if (etatOuvrage.body) {
            return of(etatOuvrage.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EtatOuvrage());
  }
}

export const etatOuvrageRoute: Routes = [
  {
    path: '',
    component: EtatOuvrageComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.etatOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EtatOuvrageDetailComponent,
    resolve: {
      etatOuvrage: EtatOuvrageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.etatOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EtatOuvrageUpdateComponent,
    resolve: {
      etatOuvrage: EtatOuvrageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.etatOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EtatOuvrageUpdateComponent,
    resolve: {
      etatOuvrage: EtatOuvrageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.etatOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
