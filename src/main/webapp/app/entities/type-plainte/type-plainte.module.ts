import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { TypePlainteComponent } from './type-plainte.component';
import { TypePlainteDetailComponent } from './type-plainte-detail.component';
import { TypePlainteUpdateComponent } from './type-plainte-update.component';
import { TypePlainteDeleteDialogComponent } from './type-plainte-delete-dialog.component';
import { typePlainteRoute } from './type-plainte.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(typePlainteRoute)],
  declarations: [TypePlainteComponent, TypePlainteDetailComponent, TypePlainteUpdateComponent, TypePlainteDeleteDialogComponent],
  entryComponents: [TypePlainteDeleteDialogComponent],
})
export class SidotTypePlainteModule {}
