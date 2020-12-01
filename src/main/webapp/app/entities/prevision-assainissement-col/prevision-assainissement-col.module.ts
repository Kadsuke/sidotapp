import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { PrevisionAssainissementColComponent } from './prevision-assainissement-col.component';
import { PrevisionAssainissementColDetailComponent } from './prevision-assainissement-col-detail.component';
import { PrevisionAssainissementColUpdateComponent } from './prevision-assainissement-col-update.component';
import { PrevisionAssainissementColDeleteDialogComponent } from './prevision-assainissement-col-delete-dialog.component';
import { previsionAssainissementColRoute } from './prevision-assainissement-col.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(previsionAssainissementColRoute)],
  declarations: [
    PrevisionAssainissementColComponent,
    PrevisionAssainissementColDetailComponent,
    PrevisionAssainissementColUpdateComponent,
    PrevisionAssainissementColDeleteDialogComponent,
  ],
  entryComponents: [PrevisionAssainissementColDeleteDialogComponent],
})
export class SidotPrevisionAssainissementColModule {}
