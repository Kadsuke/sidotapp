import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMacon } from 'app/shared/model/macon.model';
import { MaconService } from './macon.service';

@Component({
  templateUrl: './macon-delete-dialog.component.html',
})
export class MaconDeleteDialogComponent {
  macon?: IMacon;

  constructor(protected maconService: MaconService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.maconService.delete(id).subscribe(() => {
      this.eventManager.broadcast('maconListModification');
      this.activeModal.close();
    });
  }
}
