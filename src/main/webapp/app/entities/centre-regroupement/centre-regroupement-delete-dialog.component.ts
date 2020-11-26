import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICentreRegroupement } from 'app/shared/model/centre-regroupement.model';
import { CentreRegroupementService } from './centre-regroupement.service';

@Component({
  templateUrl: './centre-regroupement-delete-dialog.component.html',
})
export class CentreRegroupementDeleteDialogComponent {
  centreRegroupement?: ICentreRegroupement;

  constructor(
    protected centreRegroupementService: CentreRegroupementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.centreRegroupementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('centreRegroupementListModification');
      this.activeModal.close();
    });
  }
}
