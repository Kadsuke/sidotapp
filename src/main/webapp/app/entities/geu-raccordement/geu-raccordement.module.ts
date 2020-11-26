import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { GeuRaccordementComponent } from './geu-raccordement.component';
import { GeuRaccordementDetailComponent } from './geu-raccordement-detail.component';
import { GeuRaccordementUpdateComponent } from './geu-raccordement-update.component';
import { GeuRaccordementDeleteDialogComponent } from './geu-raccordement-delete-dialog.component';
import { geuRaccordementRoute } from './geu-raccordement.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(geuRaccordementRoute)],
  declarations: [
    GeuRaccordementComponent,
    GeuRaccordementDetailComponent,
    GeuRaccordementUpdateComponent,
    GeuRaccordementDeleteDialogComponent,
  ],
  entryComponents: [GeuRaccordementDeleteDialogComponent],
})
export class SidotGeuRaccordementModule {}
