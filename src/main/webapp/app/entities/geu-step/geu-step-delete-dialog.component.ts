import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeuSTEP } from 'app/shared/model/geu-step.model';
import { GeuSTEPService } from './geu-step.service';

@Component({
  templateUrl: './geu-step-delete-dialog.component.html',
})
export class GeuSTEPDeleteDialogComponent {
  geuSTEP?: IGeuSTEP;

  constructor(protected geuSTEPService: GeuSTEPService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geuSTEPService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geuSTEPListModification');
      this.activeModal.close();
    });
  }
}
