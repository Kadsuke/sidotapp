import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeuPSAComponent } from './geu-psa.component';
import { GeuPSADetailComponent } from './geu-psa-detail.component';
import { GeuPSAUpdateComponent } from './geu-psa-update.component';
import { GeuPSADeleteDialogComponent } from './geu-psa-delete-dialog.component';
import { geuPSARoute } from './geu-psa.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geuPSARoute)],
  declarations: [GeuPSAComponent, GeuPSADetailComponent, GeuPSAUpdateComponent, GeuPSADeleteDialogComponent],
  entryComponents: [GeuPSADeleteDialogComponent],
})
export class SidotGeuPSAModule {}
