import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeoParcelleComponent } from './geo-parcelle.component';
import { GeoParcelleDetailComponent } from './geo-parcelle-detail.component';
import { GeoParcelleUpdateComponent } from './geo-parcelle-update.component';
import { GeoParcelleDeleteDialogComponent } from './geo-parcelle-delete-dialog.component';
import { geoParcelleRoute } from './geo-parcelle.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geoParcelleRoute)],
  declarations: [GeoParcelleComponent, GeoParcelleDetailComponent, GeoParcelleUpdateComponent, GeoParcelleDeleteDialogComponent],
  entryComponents: [GeoParcelleDeleteDialogComponent],
})
export class SidotGeoParcelleModule {}
