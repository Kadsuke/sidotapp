import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { AnalyseParametreComponent } from './analyse-parametre.component';
import { AnalyseParametreDetailComponent } from './analyse-parametre-detail.component';
import { AnalyseParametreUpdateComponent } from './analyse-parametre-update.component';
import { AnalyseParametreDeleteDialogComponent } from './analyse-parametre-delete-dialog.component';
import { analyseParametreRoute } from './analyse-parametre.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(analyseParametreRoute)],
  declarations: [
    AnalyseParametreComponent,
    AnalyseParametreDetailComponent,
    AnalyseParametreUpdateComponent,
    AnalyseParametreDeleteDialogComponent,
  ],
  entryComponents: [AnalyseParametreDeleteDialogComponent],
})
export class SidotAnalyseParametreModule {}
