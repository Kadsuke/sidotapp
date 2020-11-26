import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { ModeEvacuationEauUseeComponent } from './mode-evacuation-eau-usee.component';
import { ModeEvacuationEauUseeDetailComponent } from './mode-evacuation-eau-usee-detail.component';
import { ModeEvacuationEauUseeUpdateComponent } from './mode-evacuation-eau-usee-update.component';
import { ModeEvacuationEauUseeDeleteDialogComponent } from './mode-evacuation-eau-usee-delete-dialog.component';
import { modeEvacuationEauUseeRoute } from './mode-evacuation-eau-usee.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(modeEvacuationEauUseeRoute)],
  declarations: [
    ModeEvacuationEauUseeComponent,
    ModeEvacuationEauUseeDetailComponent,
    ModeEvacuationEauUseeUpdateComponent,
    ModeEvacuationEauUseeDeleteDialogComponent,
  ],
  entryComponents: [ModeEvacuationEauUseeDeleteDialogComponent],
})
export class SidotModeEvacuationEauUseeModule {}
