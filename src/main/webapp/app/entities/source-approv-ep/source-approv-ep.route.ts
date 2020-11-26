import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISourceApprovEp, SourceApprovEp } from 'app/shared/model/source-approv-ep.model';
import { SourceApprovEpService } from './source-approv-ep.service';
import { SourceApprovEpComponent } from './source-approv-ep.component';
import { SourceApprovEpDetailComponent } from './source-approv-ep-detail.component';
import { SourceApprovEpUpdateComponent } from './source-approv-ep-update.component';

@Injectable({ providedIn: 'root' })
export class SourceApprovEpResolve implements Resolve<ISourceApprovEp> {
  constructor(private service: SourceApprovEpService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISourceApprovEp> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((sourceApprovEp: HttpResponse<SourceApprovEp>) => {
          if (sourceApprovEp.body) {
            return of(sourceApprovEp.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SourceApprovEp());
  }
}

export const sourceApprovEpRoute: Routes = [
  {
    path: '',
    component: SourceApprovEpComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.sourceApprovEp.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SourceApprovEpDetailComponent,
    resolve: {
      sourceApprovEp: SourceApprovEpResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.sourceApprovEp.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SourceApprovEpUpdateComponent,
    resolve: {
      sourceApprovEp: SourceApprovEpResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.sourceApprovEp.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SourceApprovEpUpdateComponent,
    resolve: {
      sourceApprovEp: SourceApprovEpResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.sourceApprovEp.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
