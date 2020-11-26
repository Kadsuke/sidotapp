import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { EtatProjetComponent } from './etat-projet.component';
import { EtatProjetDetailComponent } from './etat-projet-detail.component';
import { EtatProjetUpdateComponent } from './etat-projet-update.component';
import { EtatProjetDeleteDialogComponent } from './etat-projet-delete-dialog.component';
import { etatProjetRoute } from './etat-projet.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(etatProjetRoute)],
  declarations: [EtatProjetComponent, EtatProjetDetailComponent, EtatProjetUpdateComponent, EtatProjetDeleteDialogComponent],
  entryComponents: [EtatProjetDeleteDialogComponent],
})
export class SidotEtatProjetModule {}
