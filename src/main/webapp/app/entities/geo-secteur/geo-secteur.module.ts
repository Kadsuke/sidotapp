import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeoSecteurComponent } from './geo-secteur.component';
import { GeoSecteurDetailComponent } from './geo-secteur-detail.component';
import { GeoSecteurUpdateComponent } from './geo-secteur-update.component';
import { GeoSecteurDeleteDialogComponent } from './geo-secteur-delete-dialog.component';
import { geoSecteurRoute } from './geo-secteur.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geoSecteurRoute)],
  declarations: [GeoSecteurComponent, GeoSecteurDetailComponent, GeoSecteurUpdateComponent, GeoSecteurDeleteDialogComponent],
  entryComponents: [GeoSecteurDeleteDialogComponent],
})
export class SidotGeoSecteurModule {}
