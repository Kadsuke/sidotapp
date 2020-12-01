import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPrevisionAssainissementCol, PrevisionAssainissementCol } from 'app/shared/model/prevision-assainissement-col.model';
import { PrevisionAssainissementColService } from './prevision-assainissement-col.service';
import { PrevisionAssainissementColComponent } from './prevision-assainissement-col.component';
import { PrevisionAssainissementColDetailComponent } from './prevision-assainissement-col-detail.component';
import { PrevisionAssainissementColUpdateComponent } from './prevision-assainissement-col-update.component';

@Injectable({ providedIn: 'root' })
export class PrevisionAssainissementColResolve implements Resolve<IPrevisionAssainissementCol> {
  constructor(private service: PrevisionAssainissementColService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPrevisionAssainissementCol> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((previsionAssainissementCol: HttpResponse<PrevisionAssainissementCol>) => {
          if (previsionAssainissementCol.body) {
            return of(previsionAssainissementCol.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PrevisionAssainissementCol());
  }
}

export const previsionAssainissementColRoute: Routes = [
  {
    path: '',
    component: PrevisionAssainissementColComponent,
    data: {
      authorities: [Authority.SEAC],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.previsionAssainissementCol.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PrevisionAssainissementColDetailComponent,
    resolve: {
      previsionAssainissementCol: PrevisionAssainissementColResolve,
    },
    data: {
      authorities: [Authority.SEAC],
      pageTitle: 'sidotApp.previsionAssainissementCol.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PrevisionAssainissementColUpdateComponent,
    resolve: {
      previsionAssainissementCol: PrevisionAssainissementColResolve,
    },
    data: {
      authorities: [Authority.SEAC],
      pageTitle: 'sidotApp.previsionAssainissementCol.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PrevisionAssainissementColUpdateComponent,
    resolve: {
      previsionAssainissementCol: PrevisionAssainissementColResolve,
    },
    data: {
      authorities: [Authority.SEAC],
      pageTitle: 'sidotApp.previsionAssainissementCol.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
