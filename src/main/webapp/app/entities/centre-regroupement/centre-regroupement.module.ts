import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { CentreRegroupementComponent } from './centre-regroupement.component';
import { CentreRegroupementDetailComponent } from './centre-regroupement-detail.component';
import { CentreRegroupementUpdateComponent } from './centre-regroupement-update.component';
import { CentreRegroupementDeleteDialogComponent } from './centre-regroupement-delete-dialog.component';
import { centreRegroupementRoute } from './centre-regroupement.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(centreRegroupementRoute)],
  declarations: [
    CentreRegroupementComponent,
    CentreRegroupementDetailComponent,
    CentreRegroupementUpdateComponent,
    CentreRegroupementDeleteDialogComponent,
  ],
  entryComponents: [CentreRegroupementDeleteDialogComponent],
})
export class SidotCentreRegroupementModule {}
