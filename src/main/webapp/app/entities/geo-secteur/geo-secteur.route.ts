import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IGeoSecteur, GeoSecteur } from 'app/shared/model/geo-secteur.model';
import { GeoSecteurService } from './geo-secteur.service';
import { GeoSecteurComponent } from './geo-secteur.component';
import { GeoSecteurDetailComponent } from './geo-secteur-detail.component';
import { GeoSecteurUpdateComponent } from './geo-secteur-update.component';

@Injectable({ providedIn: 'root' })
export class GeoSecteurResolve implements Resolve<IGeoSecteur> {
  constructor(private service: GeoSecteurService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGeoSecteur> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((geoSecteur: HttpResponse<GeoSecteur>) => {
          if (geoSecteur.body) {
            return of(geoSecteur.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new GeoSecteur());
  }
}

export const geoSecteurRoute: Routes = [
  {
    path: '',
    component: GeoSecteurComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'sidotApp.geoSecteur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: GeoSecteurDetailComponent,
    resolve: {
      geoSecteur: GeoSecteurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoSecteur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: GeoSecteurUpdateComponent,
    resolve: {
      geoSecteur: GeoSecteurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoSecteur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: GeoSecteurUpdateComponent,
    resolve: {
      geoSecteur: GeoSecteurResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'sidotApp.geoSecteur.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
