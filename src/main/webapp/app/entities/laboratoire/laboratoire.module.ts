import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { LaboratoireComponent } from './laboratoire.component';
import { LaboratoireDetailComponent } from './laboratoire-detail.component';
import { LaboratoireUpdateComponent } from './laboratoire-update.component';
import { LaboratoireDeleteDialogComponent } from './laboratoire-delete-dialog.component';
import { laboratoireRoute } from './laboratoire.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(laboratoireRoute)],
  declarations: [LaboratoireComponent, LaboratoireDetailComponent, LaboratoireUpdateComponent, LaboratoireDeleteDialogComponent],
  entryComponents: [LaboratoireDeleteDialogComponent],
})
export class SidotLaboratoireModule {}
