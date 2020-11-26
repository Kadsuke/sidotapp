import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefMois } from 'app/shared/model/ref-mois.model';
import { RefMoisService } from './ref-mois.service';

@Component({
  templateUrl: './ref-mois-delete-dialog.component.html',
})
export class RefMoisDeleteDialogComponent {
  refMois?: IRefMois;

  constructor(protected refMoisService: RefMoisService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.refMoisService.delete(id).subscribe(() => {
      this.eventManager.broadcast('refMoisListModification');
      this.activeModal.close();
    });
  }
}
