import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICategorieRessources, CategorieRessources } from 'app/shared/model/categorie-ressources.model';
import { CategorieRessourcesService } from './categorie-ressources.service';
import { CategorieRessourcesComponent } from './categorie-ressources.component';
import { CategorieRessourcesDetailComponent } from './categorie-ressources-detail.component';
import { CategorieRessourcesUpdateComponent } from './categorie-ressources-update.component';

@Injectable({ providedIn: 'root' })
export class CategorieRessourcesResolve implements Resolve<ICategorieRessources> {
  constructor(private service: CategorieRessourcesService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICategorieRessources> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((categorieRessources: HttpResponse<CategorieRessources>) => {
          if (categorieRessources.body) {
            return of(categorieRessources.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CategorieRessources());
  }
}

export const categorieRessourcesRoute: Routes = [
  {
    path: '',
    component: CategorieRessourcesComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.categorieRessources.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CategorieRessourcesDetailComponent,
    resolve: {
      categorieRessources: CategorieRessourcesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.categorieRessources.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CategorieRessourcesUpdateComponent,
    resolve: {
      categorieRessources: CategorieRessourcesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.categorieRessources.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CategorieRessourcesUpdateComponent,
    resolve: {
      categorieRessources: CategorieRessourcesResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.categorieRessources.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
