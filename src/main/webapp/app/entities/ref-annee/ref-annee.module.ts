import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { RefAnneeComponent } from './ref-annee.component';
import { RefAnneeDetailComponent } from './ref-annee-detail.component';
import { RefAnneeUpdateComponent } from './ref-annee-update.component';
import { RefAnneeDeleteDialogComponent } from './ref-annee-delete-dialog.component';
import { refAnneeRoute } from './ref-annee.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(refAnneeRoute)],
  declarations: [RefAnneeComponent, RefAnneeDetailComponent, RefAnneeUpdateComponent, RefAnneeDeleteDialogComponent],
  entryComponents: [RefAnneeDeleteDialogComponent],
})
export class SidotRefAnneeModule {}
