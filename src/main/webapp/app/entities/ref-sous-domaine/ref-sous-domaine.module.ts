import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { RefSousDomaineComponent } from './ref-sous-domaine.component';
import { RefSousDomaineDetailComponent } from './ref-sous-domaine-detail.component';
import { RefSousDomaineUpdateComponent } from './ref-sous-domaine-update.component';
import { RefSousDomaineDeleteDialogComponent } from './ref-sous-domaine-delete-dialog.component';
import { refSousDomaineRoute } from './ref-sous-domaine.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(refSousDomaineRoute)],
  declarations: [
    RefSousDomaineComponent,
    RefSousDomaineDetailComponent,
    RefSousDomaineUpdateComponent,
    RefSousDomaineDeleteDialogComponent,
  ],
  entryComponents: [RefSousDomaineDeleteDialogComponent],
})
export class SidotRefSousDomaineModule {}
