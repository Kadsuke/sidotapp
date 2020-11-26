import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { SourceApprovEpComponent } from './source-approv-ep.component';
import { SourceApprovEpDetailComponent } from './source-approv-ep-detail.component';
import { SourceApprovEpUpdateComponent } from './source-approv-ep-update.component';
import { SourceApprovEpDeleteDialogComponent } from './source-approv-ep-delete-dialog.component';
import { sourceApprovEpRoute } from './source-approv-ep.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(sourceApprovEpRoute)],
  declarations: [
    SourceApprovEpComponent,
    SourceApprovEpDetailComponent,
    SourceApprovEpUpdateComponent,
    SourceApprovEpDeleteDialogComponent,
  ],
  entryComponents: [SourceApprovEpDeleteDialogComponent],
})
export class SidotSourceApprovEpModule {}
