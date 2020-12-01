import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { PrevisionPsaComponent } from './prevision-psa.component';
import { PrevisionPsaDetailComponent } from './prevision-psa-detail.component';
import { PrevisionPsaUpdateComponent } from './prevision-psa-update.component';
import { PrevisionPsaDeleteDialogComponent } from './prevision-psa-delete-dialog.component';
import { previsionPsaRoute } from './prevision-psa.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(previsionPsaRoute)],
  declarations: [PrevisionPsaComponent, PrevisionPsaDetailComponent, PrevisionPsaUpdateComponent, PrevisionPsaDeleteDialogComponent],
  entryComponents: [PrevisionPsaDeleteDialogComponent],
})
export class SidotPrevisionPsaModule {}
