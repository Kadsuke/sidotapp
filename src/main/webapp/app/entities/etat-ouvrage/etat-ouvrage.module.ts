import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { EtatOuvrageComponent } from './etat-ouvrage.component';
import { EtatOuvrageDetailComponent } from './etat-ouvrage-detail.component';
import { EtatOuvrageUpdateComponent } from './etat-ouvrage-update.component';
import { EtatOuvrageDeleteDialogComponent } from './etat-ouvrage-delete-dialog.component';
import { etatOuvrageRoute } from './etat-ouvrage.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(etatOuvrageRoute)],
  declarations: [EtatOuvrageComponent, EtatOuvrageDetailComponent, EtatOuvrageUpdateComponent, EtatOuvrageDeleteDialogComponent],
  entryComponents: [EtatOuvrageDeleteDialogComponent],
})
export class SidotEtatOuvrageModule {}
