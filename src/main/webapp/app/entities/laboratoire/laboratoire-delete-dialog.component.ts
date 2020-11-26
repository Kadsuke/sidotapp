import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILaboratoire } from 'app/shared/model/laboratoire.model';
import { LaboratoireService } from './laboratoire.service';

@Component({
  templateUrl: './laboratoire-delete-dialog.component.html',
})
export class LaboratoireDeleteDialogComponent {
  laboratoire?: ILaboratoire;

  constructor(
    protected laboratoireService: LaboratoireService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.laboratoireService.delete(id).subscribe(() => {
      this.eventManager.broadcast('laboratoireListModification');
      this.activeModal.close();
    });
  }
}
