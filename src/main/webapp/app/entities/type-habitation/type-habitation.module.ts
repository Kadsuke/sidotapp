import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { TypeHabitationComponent } from './type-habitation.component';
import { TypeHabitationDetailComponent } from './type-habitation-detail.component';
import { TypeHabitationUpdateComponent } from './type-habitation-update.component';
import { TypeHabitationDeleteDialogComponent } from './type-habitation-delete-dialog.component';
import { typeHabitationRoute } from './type-habitation.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(typeHabitationRoute)],
  declarations: [
    TypeHabitationComponent,
    TypeHabitationDetailComponent,
    TypeHabitationUpdateComponent,
    TypeHabitationDeleteDialogComponent,
  ],
  entryComponents: [TypeHabitationDeleteDialogComponent],
})
export class SidotTypeHabitationModule {}
