import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeuUsageComponent } from './geu-usage.component';
import { GeuUsageDetailComponent } from './geu-usage-detail.component';
import { GeuUsageUpdateComponent } from './geu-usage-update.component';
import { GeuUsageDeleteDialogComponent } from './geu-usage-delete-dialog.component';
import { geuUsageRoute } from './geu-usage.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geuUsageRoute)],
  declarations: [GeuUsageComponent, GeuUsageDetailComponent, GeuUsageUpdateComponent, GeuUsageDeleteDialogComponent],
  entryComponents: [GeuUsageDeleteDialogComponent],
})
export class SidotGeuUsageModule {}
