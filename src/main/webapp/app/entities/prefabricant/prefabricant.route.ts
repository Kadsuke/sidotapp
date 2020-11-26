import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPrefabricant, Prefabricant } from 'app/shared/model/prefabricant.model';
import { PrefabricantService } from './prefabricant.service';
import { PrefabricantComponent } from './prefabricant.component';
import { PrefabricantDetailComponent } from './prefabricant-detail.component';
import { PrefabricantUpdateComponent } from './prefabricant-update.component';

@Injectable({ providedIn: 'root' })
export class PrefabricantResolve implements Resolve<IPrefabricant> {
  constructor(private service: PrefabricantService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPrefabricant> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((prefabricant: HttpResponse<Prefabricant>) => {
          if (prefabricant.body) {
            return of(prefabricant.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Prefabricant());
  }
}

export const prefabricantRoute: Routes = [
  {
    path: '',
    component: PrefabricantComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.prefabricant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: PrefabricantDetailComponent,
    resolve: {
      prefabricant: PrefabricantResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.prefabricant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: PrefabricantUpdateComponent,
    resolve: {
      prefabricant: PrefabricantResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.prefabricant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: PrefabricantUpdateComponent,
    resolve: {
      prefabricant: PrefabricantResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.prefabricant.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
