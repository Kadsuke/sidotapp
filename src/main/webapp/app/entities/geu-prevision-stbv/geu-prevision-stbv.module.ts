import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeuPrevisionSTBVComponent } from './geu-prevision-stbv.component';
import { GeuPrevisionSTBVDetailComponent } from './geu-prevision-stbv-detail.component';
import { GeuPrevisionSTBVUpdateComponent } from './geu-prevision-stbv-update.component';
import { GeuPrevisionSTBVDeleteDialogComponent } from './geu-prevision-stbv-delete-dialog.component';
import { geuPrevisionSTBVRoute } from './geu-prevision-stbv.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geuPrevisionSTBVRoute)],
  declarations: [
    GeuPrevisionSTBVComponent,
    GeuPrevisionSTBVDetailComponent,
    GeuPrevisionSTBVUpdateComponent,
    GeuPrevisionSTBVDeleteDialogComponent,
  ],
  entryComponents: [GeuPrevisionSTBVDeleteDialogComponent],
})
export class SidotGeuPrevisionSTBVModule {}
