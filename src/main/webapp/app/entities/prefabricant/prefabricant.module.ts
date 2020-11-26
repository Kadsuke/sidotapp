import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SidotSharedModule } from 'app/shared/shared.module';
import { PrefabricantComponent } from './prefabricant.component';
import { PrefabricantDetailComponent } from './prefabricant-detail.component';
import { PrefabricantUpdateComponent } from './prefabricant-update.component';
import { PrefabricantDeleteDialogComponent } from './prefabricant-delete-dialog.component';
import { prefabricantRoute } from './prefabricant.route';

@NgModule({
  imports: [SidotSharedModule, RouterModule.forChild(prefabricantRoute)],
  declarations: [PrefabricantComponent, PrefabricantDetailComponent, PrefabricantUpdateComponent, PrefabricantDeleteDialogComponent],
  entryComponents: [PrefabricantDeleteDialogComponent],
})
export class SidotPrefabricantModule {}
