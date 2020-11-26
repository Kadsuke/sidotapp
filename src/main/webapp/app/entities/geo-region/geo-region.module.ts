import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeoRegionComponent } from './geo-region.component';
import { GeoRegionDetailComponent } from './geo-region-detail.component';
import { GeoRegionUpdateComponent } from './geo-region-update.component';
import { GeoRegionDeleteDialogComponent } from './geo-region-delete-dialog.component';
import { geoRegionRoute } from './geo-region.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geoRegionRoute)],
  declarations: [GeoRegionComponent, GeoRegionDetailComponent, GeoRegionUpdateComponent, GeoRegionDeleteDialogComponent],
  entryComponents: [GeoRegionDeleteDialogComponent],
})
export class SidotGeoRegionModule {}
