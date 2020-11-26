import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeuAaOuvrageComponent } from './geu-aa-ouvrage.component';
import { GeuAaOuvrageDetailComponent } from './geu-aa-ouvrage-detail.component';
import { GeuAaOuvrageUpdateComponent } from './geu-aa-ouvrage-update.component';
import { GeuAaOuvrageDeleteDialogComponent } from './geu-aa-ouvrage-delete-dialog.component';
import { geuAaOuvrageRoute } from './geu-aa-ouvrage.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geuAaOuvrageRoute)],
  declarations: [GeuAaOuvrageComponent, GeuAaOuvrageDetailComponent, GeuAaOuvrageUpdateComponent, GeuAaOuvrageDeleteDialogComponent],
  entryComponents: [GeuAaOuvrageDeleteDialogComponent],
})
export class SidotGeuAaOuvrageModule {}
