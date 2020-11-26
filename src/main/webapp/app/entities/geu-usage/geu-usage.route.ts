import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeuUsage, GeuUsage } from 'app/shared/model/geu-usage.model';
import { GeuUsageService } from './geu-usage.service';
import { GeuUsageComponent } from './geu-usage.component';
import { GeuUsageDetailComponent } from './geu-usage-detail.component';
import { GeuUsageUpdateComponent } from './geu-usage-update.component';

@Injectable({ providedIn: 'root' })
export class GeuUsageResolve implements Resolve<IGeuUsage> {
  constructor(private service: GeuUsageService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeuUsage> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geuUsage: HttpResponse<GeuUsage>) => {
          if (geuUsage.body) {
            return of(geuUsage.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeuUsage());
  }
}

export const geuUsageRoute: Routes = [
  {
    path: '',
    component: GeuUsageComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geuUsage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeuUsageDetailComponent,
    resolve: {
      geuUsage: GeuUsageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuUsage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeuUsageUpdateComponent,
    resolve: {
      geuUsage: GeuUsageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuUsage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeuUsageUpdateComponent,
    resolve: {
      geuUsage: GeuUsageResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geuUsage.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
