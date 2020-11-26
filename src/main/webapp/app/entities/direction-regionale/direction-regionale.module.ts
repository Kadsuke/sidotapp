import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { DirectionRegionaleComponent } from './direction-regionale.component';
import { DirectionRegionaleDetailComponent } from './direction-regionale-detail.component';
import { DirectionRegionaleUpdateComponent } from './direction-regionale-update.component';
import { DirectionRegionaleDeleteDialogComponent } from './direction-regionale-delete-dialog.component';
import { directionRegionaleRoute } from './direction-regionale.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(directionRegionaleRoute)],
  declarations: [
    DirectionRegionaleComponent,
    DirectionRegionaleDetailComponent,
    DirectionRegionaleUpdateComponent,
    DirectionRegionaleDeleteDialogComponent,
  ],
  entryComponents: [DirectionRegionaleDeleteDialogComponent],
})
export class SidotDirectionRegionaleModule {}
