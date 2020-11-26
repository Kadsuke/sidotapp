import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeoSectionComponent } from './geo-section.component';
import { GeoSectionDetailComponent } from './geo-section-detail.component';
import { GeoSectionUpdateComponent } from './geo-section-update.component';
import { GeoSectionDeleteDialogComponent } from './geo-section-delete-dialog.component';
import { geoSectionRoute } from './geo-section.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geoSectionRoute)],
  declarations: [GeoSectionComponent, GeoSectionDetailComponent, GeoSectionUpdateComponent, GeoSectionDeleteDialogComponent],
  entryComponents: [GeoSectionDeleteDialogComponent],
})
export class SidotGeoSectionModule {}
