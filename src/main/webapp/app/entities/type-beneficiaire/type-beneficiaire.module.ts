import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { TypeBeneficiaireComponent } from './type-beneficiaire.component';
import { TypeBeneficiaireDetailComponent } from './type-beneficiaire-detail.component';
import { TypeBeneficiaireUpdateComponent } from './type-beneficiaire-update.component';
import { TypeBeneficiaireDeleteDialogComponent } from './type-beneficiaire-delete-dialog.component';
import { typeBeneficiaireRoute } from './type-beneficiaire.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(typeBeneficiaireRoute)],
  declarations: [
    TypeBeneficiaireComponent,
    TypeBeneficiaireDetailComponent,
    TypeBeneficiaireUpdateComponent,
    TypeBeneficiaireDeleteDialogComponent,
  ],
  entryComponents: [TypeBeneficiaireDeleteDialogComponent],
})
export class SidotTypeBeneficiaireModule {}
