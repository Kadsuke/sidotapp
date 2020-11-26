import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeuSTBV } from 'app/shared/model/geu-stbv.model';
import { GeuSTBVService } from './geu-stbv.service';

@Component({
  templateUrl: './geu-stbv-delete-dialog.component.html',
})
export class GeuSTBVDeleteDialogComponent {
  geuSTBV?: IGeuSTBV;

  constructor(protected geuSTBVService: GeuSTBVService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geuSTBVService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geuSTBVListModification');
      this.activeModal.close();
    });
  }
}
