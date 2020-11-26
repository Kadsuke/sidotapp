import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeoLotComponent } from './geo-lot.component';
import { GeoLotDetailComponent } from './geo-lot-detail.component';
import { GeoLotUpdateComponent } from './geo-lot-update.component';
import { GeoLotDeleteDialogComponent } from './geo-lot-delete-dialog.component';
import { geoLotRoute } from './geo-lot.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geoLotRoute)],
  declarations: [GeoLotComponent, GeoLotDetailComponent, GeoLotUpdateComponent, GeoLotDeleteDialogComponent],
  entryComponents: [GeoLotDeleteDialogComponent],
})
export class SidotGeoLotModule {}
