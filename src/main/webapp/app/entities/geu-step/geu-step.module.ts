import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeuSTEPComponent } from './geu-step.component';
import { GeuSTEPDetailComponent } from './geu-step-detail.component';
import { GeuSTEPUpdateComponent } from './geu-step-update.component';
import { GeuSTEPDeleteDialogComponent } from './geu-step-delete-dialog.component';
import { geuSTEPRoute } from './geu-step.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geuSTEPRoute)],
  declarations: [GeuSTEPComponent, GeuSTEPDetailComponent, GeuSTEPUpdateComponent, GeuSTEPDeleteDialogComponent],
  entryComponents: [GeuSTEPDeleteDialogComponent],
})
export class SidotGeuSTEPModule {}
