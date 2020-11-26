import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { EtatProgramComponent } from './etat-program.component';
import { EtatProgramDetailComponent } from './etat-program-detail.component';
import { EtatProgramUpdateComponent } from './etat-program-update.component';
import { EtatProgramDeleteDialogComponent } from './etat-program-delete-dialog.component';
import { etatProgramRoute } from './etat-program.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(etatProgramRoute)],
  declarations: [EtatProgramComponent, EtatProgramDetailComponent, EtatProgramUpdateComponent, EtatProgramDeleteDialogComponent],
  entryComponents: [EtatProgramDeleteDialogComponent],
})
export class SidotEtatProgramModule {}
