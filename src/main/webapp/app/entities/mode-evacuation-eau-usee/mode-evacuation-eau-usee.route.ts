import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IModeEvacuationEauUsee, ModeEvacuationEauUsee } from 'app/shared/model/mode-evacuation-eau-usee.model';
import { ModeEvacuationEauUseeService } from './mode-evacuation-eau-usee.service';
import { ModeEvacuationEauUseeComponent } from './mode-evacuation-eau-usee.component';
import { ModeEvacuationEauUseeDetailComponent } from './mode-evacuation-eau-usee-detail.component';
import { ModeEvacuationEauUseeUpdateComponent } from './mode-evacuation-eau-usee-update.component';

@Injectable({ providedIn: 'root' })
export class ModeEvacuationEauUseeResolve implements Resolve<IModeEvacuationEauUsee> {
  constructor(private service: ModeEvacuationEauUseeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IModeEvacuationEauUsee> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((modeEvacuationEauUsee: HttpResponse<ModeEvacuationEauUsee>) => {
          if (modeEvacuationEauUsee.body) {
            return of(modeEvacuationEauUsee.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ModeEvacuationEauUsee());
  }
}

export const modeEvacuationEauUseeRoute: Routes = [
  {
    path: '',
    component: ModeEvacuationEauUseeComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.modeEvacuationEauUsee.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ModeEvacuationEauUseeDetailComponent,
    resolve: {
      modeEvacuationEauUsee: ModeEvacuationEauUseeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.modeEvacuationEauUsee.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ModeEvacuationEauUseeUpdateComponent,
    resolve: {
      modeEvacuationEauUsee: ModeEvacuationEauUseeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.modeEvacuationEauUsee.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ModeEvacuationEauUseeUpdateComponent,
    resolve: {
      modeEvacuationEauUsee: ModeEvacuationEauUseeResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.modeEvacuationEauUsee.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
