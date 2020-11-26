import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { TypeEquipementComponent } from './type-equipement.component';
import { TypeEquipementDetailComponent } from './type-equipement-detail.component';
import { TypeEquipementUpdateComponent } from './type-equipement-update.component';
import { TypeEquipementDeleteDialogComponent } from './type-equipement-delete-dialog.component';
import { typeEquipementRoute } from './type-equipement.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(typeEquipementRoute)],
  declarations: [
    TypeEquipementComponent,
    TypeEquipementDetailComponent,
    TypeEquipementUpdateComponent,
    TypeEquipementDeleteDialogComponent,
  ],
  entryComponents: [TypeEquipementDeleteDialogComponent],
})
export class SidotTypeEquipementModule {}
