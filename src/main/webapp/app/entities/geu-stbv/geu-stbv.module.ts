import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeuSTBVComponent } from './geu-stbv.component';
import { GeuSTBVDetailComponent } from './geu-stbv-detail.component';
import { GeuSTBVUpdateComponent } from './geu-stbv-update.component';
import { GeuSTBVDeleteDialogComponent } from './geu-stbv-delete-dialog.component';
import { geuSTBVRoute } from './geu-stbv.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geuSTBVRoute)],
  declarations: [GeuSTBVComponent, GeuSTBVDetailComponent, GeuSTBVUpdateComponent, GeuSTBVDeleteDialogComponent],
  entryComponents: [GeuSTBVDeleteDialogComponent],
})
export class SidotGeuSTBVModule {}
