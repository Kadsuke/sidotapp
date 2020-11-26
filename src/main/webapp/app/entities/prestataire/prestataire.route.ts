import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPrestataire, Prestataire } from 'app/shared/model/prestataire.model';
import { PrestataireService } from './prestataire.service';
import { PrestataireComponent } from './prestataire.component';
import { PrestataireDetailComponent } from './prestataire-detail.component';
import { PrestataireUpdateComponent } from './prestataire-update.component';

@Injectable({ providedIn: 'root' })
export class PrestataireResolve implements Resolve<IPrestataire> {
  constructor(private service: PrestataireService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPrestataire> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((prestataire: HttpResponse<Prestataire>) => {
          if (prestataire.body) {
            return of(prestataire.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Prestataire());
  }
}

export const prestataireRoute: Routes = [
  {
    path: '',
    component: PrestataireComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.prestataire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PrestataireDetailComponent,
    resolve: {
      prestataire: PrestataireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.prestataire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PrestataireUpdateComponent,
    resolve: {
      prestataire: PrestataireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.prestataire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PrestataireUpdateComponent,
    resolve: {
      prestataire: PrestataireResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.prestataire.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
