import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefPeriodicite } from 'app/shared/model/ref-periodicite.model';
import { RefPeriodiciteService } from './ref-periodicite.service';

@Component({
  templateUrl: './ref-periodicite-delete-dialog.component.html',
})
export class RefPeriodiciteDeleteDialogComponent {
  refPeriodicite?: IRefPeriodicite;

  constructor(
    protected refPeriodiciteService: RefPeriodiciteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.refPeriodiciteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('refPeriodiciteListModification');
      this.activeModal.close();
    });
  }
}
