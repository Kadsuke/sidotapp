import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { RefMoisComponent } from './ref-mois.component';
import { RefMoisDetailComponent } from './ref-mois-detail.component';
import { RefMoisUpdateComponent } from './ref-mois-update.component';
import { RefMoisDeleteDialogComponent } from './ref-mois-delete-dialog.component';
import { refMoisRoute } from './ref-mois.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(refMoisRoute)],
  declarations: [RefMoisComponent, RefMoisDetailComponent, RefMoisUpdateComponent, RefMoisDeleteDialogComponent],
  entryComponents: [RefMoisDeleteDialogComponent],
})
export class SidotRefMoisModule {}
