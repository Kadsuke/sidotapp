import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { TypeOuvrageComponent } from './type-ouvrage.component';
import { TypeOuvrageDetailComponent } from './type-ouvrage-detail.component';
import { TypeOuvrageUpdateComponent } from './type-ouvrage-update.component';
import { TypeOuvrageDeleteDialogComponent } from './type-ouvrage-delete-dialog.component';
import { typeOuvrageRoute } from './type-ouvrage.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(typeOuvrageRoute)],
  declarations: [TypeOuvrageComponent, TypeOuvrageDetailComponent, TypeOuvrageUpdateComponent, TypeOuvrageDeleteDialogComponent],
  entryComponents: [TypeOuvrageDeleteDialogComponent],
})
export class SidotTypeOuvrageModule {}
