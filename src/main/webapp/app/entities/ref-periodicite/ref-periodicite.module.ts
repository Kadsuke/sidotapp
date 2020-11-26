import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { RefPeriodiciteComponent } from './ref-periodicite.component';
import { RefPeriodiciteDetailComponent } from './ref-periodicite-detail.component';
import { RefPeriodiciteUpdateComponent } from './ref-periodicite-update.component';
import { RefPeriodiciteDeleteDialogComponent } from './ref-periodicite-delete-dialog.component';
import { refPeriodiciteRoute } from './ref-periodicite.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(refPeriodiciteRoute)],
  declarations: [
    RefPeriodiciteComponent,
    RefPeriodiciteDetailComponent,
    RefPeriodiciteUpdateComponent,
    RefPeriodiciteDeleteDialogComponent,
  ],
  entryComponents: [RefPeriodiciteDeleteDialogComponent],
})
export class SidotRefPeriodiciteModule {}
