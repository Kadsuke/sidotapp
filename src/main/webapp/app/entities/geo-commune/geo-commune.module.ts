import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeoCommuneComponent } from './geo-commune.component';
import { GeoCommuneDetailComponent } from './geo-commune-detail.component';
import { GeoCommuneUpdateComponent } from './geo-commune-update.component';
import { GeoCommuneDeleteDialogComponent } from './geo-commune-delete-dialog.component';
import { geoCommuneRoute } from './geo-commune.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geoCommuneRoute)],
  declarations: [GeoCommuneComponent, GeoCommuneDetailComponent, GeoCommuneUpdateComponent, GeoCommuneDeleteDialogComponent],
  entryComponents: [GeoCommuneDeleteDialogComponent],
})
export class SidotGeoCommuneModule {}
