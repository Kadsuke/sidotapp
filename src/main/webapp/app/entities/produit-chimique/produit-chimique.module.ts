import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { ProduitChimiqueComponent } from './produit-chimique.component';
import { ProduitChimiqueDetailComponent } from './produit-chimique-detail.component';
import { ProduitChimiqueUpdateComponent } from './produit-chimique-update.component';
import { ProduitChimiqueDeleteDialogComponent } from './produit-chimique-delete-dialog.component';
import { produitChimiqueRoute } from './produit-chimique.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(produitChimiqueRoute)],
  declarations: [
    ProduitChimiqueComponent,
    ProduitChimiqueDetailComponent,
    ProduitChimiqueUpdateComponent,
    ProduitChimiqueDeleteDialogComponent,
  ],
  entryComponents: [ProduitChimiqueDeleteDialogComponent],
})
export class SidotProduitChimiqueModule {}
