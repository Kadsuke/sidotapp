import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeoTypeCommuneComponent } from './geo-type-commune.component';
import { GeoTypeCommuneDetailComponent } from './geo-type-commune-detail.component';
import { GeoTypeCommuneUpdateComponent } from './geo-type-commune-update.component';
import { GeoTypeCommuneDeleteDialogComponent } from './geo-type-commune-delete-dialog.component';
import { geoTypeCommuneRoute } from './geo-type-commune.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geoTypeCommuneRoute)],
  declarations: [
    GeoTypeCommuneComponent,
    GeoTypeCommuneDetailComponent,
    GeoTypeCommuneUpdateComponent,
    GeoTypeCommuneDeleteDialogComponent,
  ],
  entryComponents: [GeoTypeCommuneDeleteDialogComponent],
})
export class SidotGeoTypeCommuneModule {}
