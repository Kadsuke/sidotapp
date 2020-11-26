import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { TacheronsComponent } from './tacherons.component';
import { TacheronsDetailComponent } from './tacherons-detail.component';
import { TacheronsUpdateComponent } from './tacherons-update.component';
import { TacheronsDeleteDialogComponent } from './tacherons-delete-dialog.component';
import { tacheronsRoute } from './tacherons.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(tacheronsRoute)],
  declarations: [TacheronsComponent, TacheronsDetailComponent, TacheronsUpdateComponent, TacheronsDeleteDialogComponent],
  entryComponents: [TacheronsDeleteDialogComponent],
})
export class SidotTacheronsModule {}
