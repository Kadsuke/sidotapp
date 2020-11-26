import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { AnalyseSpecialiteComponent } from './analyse-specialite.component';
import { AnalyseSpecialiteDetailComponent } from './analyse-specialite-detail.component';
import { AnalyseSpecialiteUpdateComponent } from './analyse-specialite-update.component';
import { AnalyseSpecialiteDeleteDialogComponent } from './analyse-specialite-delete-dialog.component';
import { analyseSpecialiteRoute } from './analyse-specialite.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(analyseSpecialiteRoute)],
  declarations: [
    AnalyseSpecialiteComponent,
    AnalyseSpecialiteDetailComponent,
    AnalyseSpecialiteUpdateComponent,
    AnalyseSpecialiteDeleteDialogComponent,
  ],
  entryComponents: [AnalyseSpecialiteDeleteDialogComponent],
})
export class SidotAnalyseSpecialiteModule {}
