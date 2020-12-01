import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPrevisionPsa } from 'app/shared/model/prevision-psa.model';
import { PrevisionPsaService } from './prevision-psa.service';

@Component({
  templateUrl: './prevision-psa-delete-dialog.component.html',
})
export class PrevisionPsaDeleteDialogComponent {
  previsionPsa?: IPrevisionPsa;

  constructor(
    protected previsionPsaService: PrevisionPsaService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.previsionPsaService.delete(id).subscribe(() => {
      this.eventManager.broadcast('previsionPsaListModification');
      this.activeModal.close();
    });
  }
}
