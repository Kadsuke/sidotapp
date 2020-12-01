import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPrevisionAssainissementAu, PrevisionAssainissementAu } from 'app/shared/model/prevision-assainissement-au.model';
import { PrevisionAssainissementAuService } from './prevision-assainissement-au.service';
import { PrevisionAssainissementAuComponent } from './prevision-assainissement-au.component';
import { PrevisionAssainissementAuDetailComponent } from './prevision-assainissement-au-detail.component';
import { PrevisionAssainissementAuUpdateComponent } from './prevision-assainissement-au-update.component';

@Injectable({ providedIn: 'root' })
export class PrevisionAssainissementAuResolve implements Resolve<IPrevisionAssainissementAu> {
  constructor(private service: PrevisionAssainissementAuService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPrevisionAssainissementAu> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((previsionAssainissementAu: HttpResponse<PrevisionAssainissementAu>) => {
          if (previsionAssainissementAu.body) {
            return of(previsionAssainissementAu.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PrevisionAssainissementAu());
  }
}

export const previsionAssainissementAuRoute: Routes = [
  {
    path: '',
    component: PrevisionAssainissementAuComponent,
    data: {
      authorities: [Authority.SEDA],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.previsionAssainissementAu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PrevisionAssainissementAuDetailComponent,
    resolve: {
      previsionAssainissementAu: PrevisionAssainissementAuResolve,
    },
    data: {
      authorities: [Authority.SEDA],
      pageTitle: 'sidotApp.previsionAssainissementAu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PrevisionAssainissementAuUpdateComponent,
    resolve: {
      previsionAssainissementAu: PrevisionAssainissementAuResolve,
    },
    data: {
      authorities: [Authority.SEDA],
      pageTitle: 'sidotApp.previsionAssainissementAu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PrevisionAssainissementAuUpdateComponent,
    resolve: {
      previsionAssainissementAu: PrevisionAssainissementAuResolve,
    },
    data: {
      authorities: [Authority.SEDA],
      pageTitle: 'sidotApp.previsionAssainissementAu.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
