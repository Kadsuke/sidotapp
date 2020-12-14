import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPrevisionPsa, PrevisionPsa } from 'app/shared/model/prevision-psa.model';
import { PrevisionPsaService } from './prevision-psa.service';
import { PrevisionPsaComponent } from './prevision-psa.component';
import { PrevisionPsaDetailComponent } from './prevision-psa-detail.component';
import { PrevisionPsaUpdateComponent } from './prevision-psa-update.component';

@Injectable({ providedIn: 'root' })
export class PrevisionPsaResolve implements Resolve<IPrevisionPsa> {
  constructor(private service: PrevisionPsaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPrevisionPsa> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((previsionPsa: HttpResponse<PrevisionPsa>) => {
          if (previsionPsa.body) {
            return of(previsionPsa.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new PrevisionPsa());
  }
}

export const previsionPsaRoute: Routes = [
  {
    path: '',
    component: PrevisionPsaComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.previsionPsa.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PrevisionPsaDetailComponent,
    resolve: {
      previsionPsa: PrevisionPsaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.previsionPsa.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PrevisionPsaUpdateComponent,
    resolve: {
      previsionPsa: PrevisionPsaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.previsionPsa.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PrevisionPsaUpdateComponent,
    resolve: {
      previsionPsa: PrevisionPsaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.previsionPsa.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
