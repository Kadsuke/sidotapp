import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPrefabricant } from 'app/shared/model/prefabricant.model';
import { PrefabricantService } from './prefabricant.service';

@Component({
  templateUrl: './prefabricant-delete-dialog.component.html',
})
export class PrefabricantDeleteDialogComponent {
  prefabricant?: IPrefabricant;

  constructor(
    protected prefabricantService: PrefabricantService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.prefabricantService.delete(id).subscribe(() => {
      this.eventManager.broadcast('prefabricantListModification');
      this.activeModal.close();
    });
  }
}
