import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEtatProjet, EtatProjet } from 'app/shared/model/etat-projet.model';
import { EtatProjetService } from './etat-projet.service';
import { EtatProjetComponent } from './etat-projet.component';
import { EtatProjetDetailComponent } from './etat-projet-detail.component';
import { EtatProjetUpdateComponent } from './etat-projet-update.component';

@Injectable({ providedIn: 'root' })
export class EtatProjetResolve implements Resolve<IEtatProjet> {
  constructor(private service: EtatProjetService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEtatProjet> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((etatProjet: HttpResponse<EtatProjet>) => {
          if (etatProjet.body) {
            return of(etatProjet.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EtatProjet());
  }
}

export const etatProjetRoute: Routes = [
  {
    path: '',
    component: EtatProjetComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.etatProjet.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EtatProjetDetailComponent,
    resolve: {
      etatProjet: EtatProjetResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.etatProjet.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EtatProjetUpdateComponent,
    resolve: {
      etatProjet: EtatProjetResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.etatProjet.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EtatProjetUpdateComponent,
    resolve: {
      etatProjet: EtatProjetResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.etatProjet.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
