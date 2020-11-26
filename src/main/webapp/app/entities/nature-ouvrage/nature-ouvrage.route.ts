import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { INatureOuvrage, NatureOuvrage } from 'app/shared/model/nature-ouvrage.model';
import { NatureOuvrageService } from './nature-ouvrage.service';
import { NatureOuvrageComponent } from './nature-ouvrage.component';
import { NatureOuvrageDetailComponent } from './nature-ouvrage-detail.component';
import { NatureOuvrageUpdateComponent } from './nature-ouvrage-update.component';

@Injectable({ providedIn: 'root' })
export class NatureOuvrageResolve implements Resolve<INatureOuvrage> {
  constructor(private service: NatureOuvrageService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<INatureOuvrage> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((natureOuvrage: HttpResponse<NatureOuvrage>) => {
          if (natureOuvrage.body) {
            return of(natureOuvrage.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new NatureOuvrage());
  }
}

export const natureOuvrageRoute: Routes = [
  {
    path: '',
    component: NatureOuvrageComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.natureOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: NatureOuvrageDetailComponent,
    resolve: {
      natureOuvrage: NatureOuvrageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.natureOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: NatureOuvrageUpdateComponent,
    resolve: {
      natureOuvrage: NatureOuvrageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.natureOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: NatureOuvrageUpdateComponent,
    resolve: {
      natureOuvrage: NatureOuvrageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.natureOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
