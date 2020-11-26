import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefAnnee } from 'app/shared/model/ref-annee.model';
import { RefAnneeService } from './ref-annee.service';

@Component({
  templateUrl: './ref-annee-delete-dialog.component.html',
})
export class RefAnneeDeleteDialogComponent {
  refAnnee?: IRefAnnee;

  constructor(protected refAnneeService: RefAnneeService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.refAnneeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('refAnneeListModification');
      this.activeModal.close();
    });
  }
}
