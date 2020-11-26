import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { NatureOuvrageComponent } from './nature-ouvrage.component';
import { NatureOuvrageDetailComponent } from './nature-ouvrage-detail.component';
import { NatureOuvrageUpdateComponent } from './nature-ouvrage-update.component';
import { NatureOuvrageDeleteDialogComponent } from './nature-ouvrage-delete-dialog.component';
import { natureOuvrageRoute } from './nature-ouvrage.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(natureOuvrageRoute)],
  declarations: [NatureOuvrageComponent, NatureOuvrageDetailComponent, NatureOuvrageUpdateComponent, NatureOuvrageDeleteDialogComponent],
  entryComponents: [NatureOuvrageDeleteDialogComponent],
})
export class SidotNatureOuvrageModule {}
