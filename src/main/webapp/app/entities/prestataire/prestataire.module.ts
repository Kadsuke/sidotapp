import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { PrestataireComponent } from './prestataire.component';
import { PrestataireDetailComponent } from './prestataire-detail.component';
import { PrestataireUpdateComponent } from './prestataire-update.component';
import { PrestataireDeleteDialogComponent } from './prestataire-delete-dialog.component';
import { prestataireRoute } from './prestataire.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(prestataireRoute)],
  declarations: [PrestataireComponent, PrestataireDetailComponent, PrestataireUpdateComponent, PrestataireDeleteDialogComponent],
  entryComponents: [PrestataireDeleteDialogComponent],
})
export class SidotPrestataireModule {}
