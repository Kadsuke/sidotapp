import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeuPrevisionSTEPComponent } from './geu-prevision-step.component';
import { GeuPrevisionSTEPDetailComponent } from './geu-prevision-step-detail.component';
import { GeuPrevisionSTEPUpdateComponent } from './geu-prevision-step-update.component';
import { GeuPrevisionSTEPDeleteDialogComponent } from './geu-prevision-step-delete-dialog.component';
import { geuPrevisionSTEPRoute } from './geu-prevision-step.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geuPrevisionSTEPRoute)],
  declarations: [
    GeuPrevisionSTEPComponent,
    GeuPrevisionSTEPDetailComponent,
    GeuPrevisionSTEPUpdateComponent,
    GeuPrevisionSTEPDeleteDialogComponent,
  ],
  entryComponents: [GeuPrevisionSTEPDeleteDialogComponent],
})
export class SidotGeuPrevisionSTEPModule {}
