import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeuPSA } from 'app/shared/model/geu-psa.model';
import { GeuPSAService } from './geu-psa.service';

@Component({
  templateUrl: './geu-psa-delete-dialog.component.html',
})
export class GeuPSADeleteDialogComponent {
  geuPSA?: IGeuPSA;

  constructor(protected geuPSAService: GeuPSAService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geuPSAService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geuPSAListModification');
      this.activeModal.close();
    });
  }
}
