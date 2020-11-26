import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { TypeInterventionComponent } from './type-intervention.component';
import { TypeInterventionDetailComponent } from './type-intervention-detail.component';
import { TypeInterventionUpdateComponent } from './type-intervention-update.component';
import { TypeInterventionDeleteDialogComponent } from './type-intervention-delete-dialog.component';
import { typeInterventionRoute } from './type-intervention.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(typeInterventionRoute)],
  declarations: [
    TypeInterventionComponent,
    TypeInterventionDetailComponent,
    TypeInterventionUpdateComponent,
    TypeInterventionDeleteDialogComponent,
  ],
  entryComponents: [TypeInterventionDeleteDialogComponent],
})
export class SidotTypeInterventionModule {}
