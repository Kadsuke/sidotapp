import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { CaracteristiqueOuvrageComponent } from './caracteristique-ouvrage.component';
import { CaracteristiqueOuvrageDetailComponent } from './caracteristique-ouvrage-detail.component';
import { CaracteristiqueOuvrageUpdateComponent } from './caracteristique-ouvrage-update.component';
import { CaracteristiqueOuvrageDeleteDialogComponent } from './caracteristique-ouvrage-delete-dialog.component';
import { caracteristiqueOuvrageRoute } from './caracteristique-ouvrage.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(caracteristiqueOuvrageRoute)],
  declarations: [
    CaracteristiqueOuvrageComponent,
    CaracteristiqueOuvrageDetailComponent,
    CaracteristiqueOuvrageUpdateComponent,
    CaracteristiqueOuvrageDeleteDialogComponent,
  ],
  entryComponents: [CaracteristiqueOuvrageDeleteDialogComponent],
})
export class SidotCaracteristiqueOuvrageModule {}
