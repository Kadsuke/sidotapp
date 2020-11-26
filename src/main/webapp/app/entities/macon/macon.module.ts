import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { MaconComponent } from './macon.component';
import { MaconDetailComponent } from './macon-detail.component';
import { MaconUpdateComponent } from './macon-update.component';
import { MaconDeleteDialogComponent } from './macon-delete-dialog.component';
import { maconRoute } from './macon.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(maconRoute)],
  declarations: [MaconComponent, MaconDetailComponent, MaconUpdateComponent, MaconDeleteDialogComponent],
  entryComponents: [MaconDeleteDialogComponent],
})
export class SidotMaconModule {}
