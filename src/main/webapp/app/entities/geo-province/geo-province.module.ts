import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeoProvinceComponent } from './geo-province.component';
import { GeoProvinceDetailComponent } from './geo-province-detail.component';
import { GeoProvinceUpdateComponent } from './geo-province-update.component';
import { GeoProvinceDeleteDialogComponent } from './geo-province-delete-dialog.component';
import { geoProvinceRoute } from './geo-province.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geoProvinceRoute)],
  declarations: [GeoProvinceComponent, GeoProvinceDetailComponent, GeoProvinceUpdateComponent, GeoProvinceDeleteDialogComponent],
  entryComponents: [GeoProvinceDeleteDialogComponent],
})
export class SidotGeoProvinceModule {}
