import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeoLocaliteComponent } from './geo-localite.component';
import { GeoLocaliteDetailComponent } from './geo-localite-detail.component';
import { GeoLocaliteUpdateComponent } from './geo-localite-update.component';
import { GeoLocaliteDeleteDialogComponent } from './geo-localite-delete-dialog.component';
import { geoLocaliteRoute } from './geo-localite.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geoLocaliteRoute)],
  declarations: [GeoLocaliteComponent, GeoLocaliteDetailComponent, GeoLocaliteUpdateComponent, GeoLocaliteDeleteDialogComponent],
  entryComponents: [GeoLocaliteDeleteDialogComponent],
})
export class SidotGeoLocaliteModule {}
