import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { BailleurComponent } from './bailleur.component';
import { BailleurDetailComponent } from './bailleur-detail.component';
import { BailleurUpdateComponent } from './bailleur-update.component';
import { BailleurDeleteDialogComponent } from './bailleur-delete-dialog.component';
import { bailleurRoute } from './bailleur.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(bailleurRoute)],
  declarations: [BailleurComponent, BailleurDetailComponent, BailleurUpdateComponent, BailleurDeleteDialogComponent],
  entryComponents: [BailleurDeleteDialogComponent],
})
export class SidotBailleurModule {}
