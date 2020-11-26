import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEtatOuvrage } from 'app/shared/model/etat-ouvrage.model';
import { EtatOuvrageService } from './etat-ouvrage.service';

@Component({
  templateUrl: './etat-ouvrage-delete-dialog.component.html',
})
export class EtatOuvrageDeleteDialogComponent {
  etatOuvrage?: IEtatOuvrage;

  constructor(
    protected etatOuvrageService: EtatOuvrageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.etatOuvrageService.delete(id).subscribe(() => {
      this.eventManager.broadcast('etatOuvrageListModification');
      this.activeModal.close();
    });
  }
}
