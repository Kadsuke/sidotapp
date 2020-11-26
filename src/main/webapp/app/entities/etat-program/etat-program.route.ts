import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IEtatProgram, EtatProgram } from 'app/shared/model/etat-program.model';
import { EtatProgramService } from './etat-program.service';
import { EtatProgramComponent } from './etat-program.component';
import { EtatProgramDetailComponent } from './etat-program-detail.component';
import { EtatProgramUpdateComponent } from './etat-program-update.component';

@Injectable({ providedIn: 'root' })
export class EtatProgramResolve implements Resolve<IEtatProgram> {
  constructor(private service: EtatProgramService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IEtatProgram> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((etatProgram: HttpResponse<EtatProgram>) => {
          if (etatProgram.body) {
            return of(etatProgram.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new EtatProgram());
  }
}

export const etatProgramRoute: Routes = [
  {
    path: '',
    component: EtatProgramComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.etatProgram.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: EtatProgramDetailComponent,
    resolve: {
      etatProgram: EtatProgramResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.etatProgram.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: EtatProgramUpdateComponent,
    resolve: {
      etatProgram: EtatProgramResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.etatProgram.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: EtatProgramUpdateComponent,
    resolve: {
      etatProgram: EtatProgramResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.etatProgram.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
