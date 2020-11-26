import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { EtatEquipementComponent } from './etat-equipement.component';
import { EtatEquipementDetailComponent } from './etat-equipement-detail.component';
import { EtatEquipementUpdateComponent } from './etat-equipement-update.component';
import { EtatEquipementDeleteDialogComponent } from './etat-equipement-delete-dialog.component';
import { etatEquipementRoute } from './etat-equipement.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(etatEquipementRoute)],
  declarations: [
    EtatEquipementComponent,
    EtatEquipementDetailComponent,
    EtatEquipementUpdateComponent,
    EtatEquipementDeleteDialogComponent,
  ],
  entryComponents: [EtatEquipementDeleteDialogComponent],
})
export class SidotEtatEquipementModule {}
