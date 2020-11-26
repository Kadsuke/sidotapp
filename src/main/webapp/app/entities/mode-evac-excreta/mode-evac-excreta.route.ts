import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IModeEvacExcreta, ModeEvacExcreta } from 'app/shared/model/mode-evac-excreta.model';
import { ModeEvacExcretaService } from './mode-evac-excreta.service';
import { ModeEvacExcretaComponent } from './mode-evac-excreta.component';
import { ModeEvacExcretaDetailComponent } from './mode-evac-excreta-detail.component';
import { ModeEvacExcretaUpdateComponent } from './mode-evac-excreta-update.component';

@Injectable({ providedIn: 'root' })
export class ModeEvacExcretaResolve implements Resolve<IModeEvacExcreta> {
  constructor(private service: ModeEvacExcretaService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IModeEvacExcreta> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((modeEvacExcreta: HttpResponse<ModeEvacExcreta>) => {
          if (modeEvacExcreta.body) {
            return of(modeEvacExcreta.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ModeEvacExcreta());
  }
}

export const modeEvacExcretaRoute: Routes = [
  {
    path: '',
    component: ModeEvacExcretaComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.modeEvacExcreta.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ModeEvacExcretaDetailComponent,
    resolve: {
      modeEvacExcreta: ModeEvacExcretaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.modeEvacExcreta.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ModeEvacExcretaUpdateComponent,
    resolve: {
      modeEvacExcreta: ModeEvacExcretaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.modeEvacExcreta.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ModeEvacExcretaUpdateComponent,
    resolve: {
      modeEvacExcreta: ModeEvacExcretaResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.modeEvacExcreta.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
