import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { CategorieRessourcesComponent } from './categorie-ressources.component';
import { CategorieRessourcesDetailComponent } from './categorie-ressources-detail.component';
import { CategorieRessourcesUpdateComponent } from './categorie-ressources-update.component';
import { CategorieRessourcesDeleteDialogComponent } from './categorie-ressources-delete-dialog.component';
import { categorieRessourcesRoute } from './categorie-ressources.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(categorieRessourcesRoute)],
  declarations: [
    CategorieRessourcesComponent,
    CategorieRessourcesDetailComponent,
    CategorieRessourcesUpdateComponent,
    CategorieRessourcesDeleteDialogComponent,
  ],
  entryComponents: [CategorieRessourcesDeleteDialogComponent],
})
export class SidotCategorieRessourcesModule {}
