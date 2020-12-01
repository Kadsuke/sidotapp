import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { PrevisionAssainissementAuComponent } from './prevision-assainissement-au.component';
import { PrevisionAssainissementAuDetailComponent } from './prevision-assainissement-au-detail.component';
import { PrevisionAssainissementAuUpdateComponent } from './prevision-assainissement-au-update.component';
import { PrevisionAssainissementAuDeleteDialogComponent } from './prevision-assainissement-au-delete-dialog.component';
import { previsionAssainissementAuRoute } from './prevision-assainissement-au.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(previsionAssainissementAuRoute)],
  declarations: [
    PrevisionAssainissementAuComponent,
    PrevisionAssainissementAuDetailComponent,
    PrevisionAssainissementAuUpdateComponent,
    PrevisionAssainissementAuDeleteDialogComponent,
  ],
  entryComponents: [PrevisionAssainissementAuDeleteDialogComponent],
})
export class SidotPrevisionAssainissementAuModule {}
