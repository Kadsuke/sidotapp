import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeuRealisationSTBVComponent } from './geu-realisation-stbv.component';
import { GeuRealisationSTBVDetailComponent } from './geu-realisation-stbv-detail.component';
import { GeuRealisationSTBVUpdateComponent } from './geu-realisation-stbv-update.component';
import { GeuRealisationSTBVDeleteDialogComponent } from './geu-realisation-stbv-delete-dialog.component';
import { geuRealisationSTBVRoute } from './geu-realisation-stbv.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geuRealisationSTBVRoute)],
  declarations: [
    GeuRealisationSTBVComponent,
    GeuRealisationSTBVDetailComponent,
    GeuRealisationSTBVUpdateComponent,
    GeuRealisationSTBVDeleteDialogComponent,
  ],
  entryComponents: [GeuRealisationSTBVDeleteDialogComponent],
})
export class SidotGeuRealisationSTBVModule {}
