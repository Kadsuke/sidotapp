import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEtatEquipement, EtatEquipement } from 'app/shared/model/etat-equipement.model';
import { EtatEquipementService } from './etat-equipement.service';
import { EtatEquipementComponent } from './etat-equipement.component';
import { EtatEquipementDetailComponent } from './etat-equipement-detail.component';
import { EtatEquipementUpdateComponent } from './etat-equipement-update.component';

@Injectable({ providedIn: 'root' })
export class EtatEquipementResolve implements Resolve<IEtatEquipement> {
  constructor(private service: EtatEquipementService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEtatEquipement> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((etatEquipement: HttpResponse<EtatEquipement>) => {
          if (etatEquipement.body) {
            return of(etatEquipement.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EtatEquipement());
  }
}

export const etatEquipementRoute: Routes = [
  {
    path: '',
    component: EtatEquipementComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.etatEquipement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EtatEquipementDetailComponent,
    resolve: {
      etatEquipement: EtatEquipementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.etatEquipement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EtatEquipementUpdateComponent,
    resolve: {
      etatEquipement: EtatEquipementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.etatEquipement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EtatEquipementUpdateComponent,
    resolve: {
      etatEquipement: EtatEquipementResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.etatEquipement.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
