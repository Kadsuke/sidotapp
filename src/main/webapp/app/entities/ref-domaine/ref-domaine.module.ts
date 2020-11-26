import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { RefDomaineComponent } from './ref-domaine.component';
import { RefDomaineDetailComponent } from './ref-domaine-detail.component';
import { RefDomaineUpdateComponent } from './ref-domaine-update.component';
import { RefDomaineDeleteDialogComponent } from './ref-domaine-delete-dialog.component';
import { refDomaineRoute } from './ref-domaine.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(refDomaineRoute)],
  declarations: [RefDomaineComponent, RefDomaineDetailComponent, RefDomaineUpdateComponent, RefDomaineDeleteDialogComponent],
  entryComponents: [RefDomaineDeleteDialogComponent],
})
export class SidotRefDomaineModule {}
