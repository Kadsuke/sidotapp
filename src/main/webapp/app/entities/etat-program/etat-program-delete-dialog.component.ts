import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEtatProgram } from 'app/shared/model/etat-program.model';
import { EtatProgramService } from './etat-program.service';

@Component({
  templateUrl: './etat-program-delete-dialog.component.html',
})
export class EtatProgramDeleteDialogComponent {
  etatProgram?: IEtatProgram;

  constructor(
    protected etatProgramService: EtatProgramService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.etatProgramService.delete(id).subscribe(() => {
      this.eventManager.broadcast('etatProgramListModification');
      this.activeModal.close();
    });
  }
}
