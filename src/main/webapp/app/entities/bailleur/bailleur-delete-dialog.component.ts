import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBailleur } from 'app/shared/model/bailleur.model';
import { BailleurService } from './bailleur.service';

@Component({
  templateUrl: './bailleur-delete-dialog.component.html',
})
export class BailleurDeleteDialogComponent {
  bailleur?: IBailleur;

  constructor(protected bailleurService: BailleurService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bailleurService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bailleurListModification');
      this.activeModal.close();
    });
  }
}
