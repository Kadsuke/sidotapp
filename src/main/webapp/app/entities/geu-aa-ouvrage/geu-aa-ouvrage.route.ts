import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeuAaOuvrage, GeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';
import { GeuAaOuvrageService } from './geu-aa-ouvrage.service';
import { GeuAaOuvrageComponent } from './geu-aa-ouvrage.component';
import { GeuAaOuvrageDetailComponent } from './geu-aa-ouvrage-detail.component';
import { GeuAaOuvrageUpdateComponent } from './geu-aa-ouvrage-update.component';

@Injectable({ providedIn: 'root' })
export class GeuAaOuvrageResolve implements Resolve<IGeuAaOuvrage> {
  constructor(private service: GeuAaOuvrageService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeuAaOuvrage> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geuAaOuvrage: HttpResponse<GeuAaOuvrage>) => {
          if (geuAaOuvrage.body) {
            return of(geuAaOuvrage.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeuAaOuvrage());
  }
}

export const geuAaOuvrageRoute: Routes = [
  {
    path: '',
    component: GeuAaOuvrageComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geuAaOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeuAaOuvrageDetailComponent,
    resolve: {
      geuAaOuvrage: GeuAaOuvrageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuAaOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeuAaOuvrageUpdateComponent,
    resolve: {
      geuAaOuvrage: GeuAaOuvrageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuAaOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeuAaOuvrageUpdateComponent,
    resolve: {
      geuAaOuvrage: GeuAaOuvrageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuAaOuvrage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
