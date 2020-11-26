import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { ModeEvacExcretaComponent } from './mode-evac-excreta.component';
import { ModeEvacExcretaDetailComponent } from './mode-evac-excreta-detail.component';
import { ModeEvacExcretaUpdateComponent } from './mode-evac-excreta-update.component';
import { ModeEvacExcretaDeleteDialogComponent } from './mode-evac-excreta-delete-dialog.component';
import { modeEvacExcretaRoute } from './mode-evac-excreta.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(modeEvacExcretaRoute)],
  declarations: [
    ModeEvacExcretaComponent,
    ModeEvacExcretaDetailComponent,
    ModeEvacExcretaUpdateComponent,
    ModeEvacExcretaDeleteDialogComponent,
  ],
  entryComponents: [ModeEvacExcretaDeleteDialogComponent],
})
export class SidotModeEvacExcretaModule {}
