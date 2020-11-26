import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeuRealisationComponent } from './geu-realisation.component';
import { GeuRealisationDetailComponent } from './geu-realisation-detail.component';
import { GeuRealisationUpdateComponent } from './geu-realisation-update.component';
import { GeuRealisationDeleteDialogComponent } from './geu-realisation-delete-dialog.component';
import { geuRealisationRoute } from './geu-realisation.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geuRealisationRoute)],
  declarations: [
    GeuRealisationComponent,
    GeuRealisationDetailComponent,
    GeuRealisationUpdateComponent,
    GeuRealisationDeleteDialogComponent,
  ],
  entryComponents: [GeuRealisationDeleteDialogComponent],
})
export class SidotGeuRealisationModule {}
