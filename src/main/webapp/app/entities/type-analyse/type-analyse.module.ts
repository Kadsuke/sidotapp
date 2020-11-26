import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { TypeAnalyseComponent } from './type-analyse.component';
import { TypeAnalyseDetailComponent } from './type-analyse-detail.component';
import { TypeAnalyseUpdateComponent } from './type-analyse-update.component';
import { TypeAnalyseDeleteDialogComponent } from './type-analyse-delete-dialog.component';
import { typeAnalyseRoute } from './type-analyse.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(typeAnalyseRoute)],
  declarations: [TypeAnalyseComponent, TypeAnalyseDetailComponent, TypeAnalyseUpdateComponent, TypeAnalyseDeleteDialogComponent],
  entryComponents: [TypeAnalyseDeleteDialogComponent],
})
export class SidotTypeAnalyseModule {}
