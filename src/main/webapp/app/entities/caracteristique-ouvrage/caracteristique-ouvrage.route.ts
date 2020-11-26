import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICaracteristiqueOuvrage, CaracteristiqueOuvrage } from 'app/shared/model/caracteristique-ouvrage.model';
import { CaracteristiqueOuvrageService } from './caracteristique-ouvrage.service';
import { CaracteristiqueOuvrageComponent } from './caracteristique-ouvrage.component';
import { CaracteristiqueOuvrageDetailComponent } from './caracteristique-ouvrage-detail.component';
import { CaracteristiqueOuvrageUpdateComponent } from './caracteristique-ouvrage-update.component';

@Injectable({ providedIn: 'root' })
export class CaracteristiqueOuvrageResolve implements Resolve<ICaracteristiqueOuvrage> {
  constructor(private service: CaracteristiqueOuvrageService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICaracteristiqueOuvrage> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((caracteristiqueOuvrage: HttpResponse<CaracteristiqueOuvrage>) => {
          if (caracteristiqueOuvrage.body) {
            return of(caracteristiqueOuvrage.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CaracteristiqueOuvrage());
  }
}

export const caracteristiqueOuvrageRoute: Routes = [
  {
    path: '',
    component: CaracteristiqueOuvrageComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.caracteristiqueOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CaracteristiqueOuvrageDetailComponent,
    resolve: {
      caracteristiqueOuvrage: CaracteristiqueOuvrageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.caracteristiqueOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CaracteristiqueOuvrageUpdateComponent,
    resolve: {
      caracteristiqueOuvrage: CaracteristiqueOuvrageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.caracteristiqueOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CaracteristiqueOuvrageUpdateComponent,
    resolve: {
      caracteristiqueOuvrage: CaracteristiqueOuvrageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.caracteristiqueOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
