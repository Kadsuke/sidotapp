import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IProduitChimique, ProduitChimique } from 'app/shared/model/produit-chimique.model';
import { ProduitChimiqueService } from './produit-chimique.service';
import { ProduitChimiqueComponent } from './produit-chimique.component';
import { ProduitChimiqueDetailComponent } from './produit-chimique-detail.component';
import { ProduitChimiqueUpdateComponent } from './produit-chimique-update.component';

@Injectable({ providedIn: 'root' })
export class ProduitChimiqueResolve implements Resolve<IProduitChimique> {
  constructor(private service: ProduitChimiqueService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IProduitChimique> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((produitChimique: HttpResponse<ProduitChimique>) => {
          if (produitChimique.body) {
            return of(produitChimique.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ProduitChimique());
  }
}

export const produitChimiqueRoute: Routes = [
  {
    path: '',
    component: ProduitChimiqueComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.produitChimique.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ProduitChimiqueDetailComponent,
    resolve: {
      produitChimique: ProduitChimiqueResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.produitChimique.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ProduitChimiqueUpdateComponent,
    resolve: {
      produitChimique: ProduitChimiqueResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.produitChimique.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ProduitChimiqueUpdateComponent,
    resolve: {
      produitChimique: ProduitChimiqueResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.produitChimique.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
