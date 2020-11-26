import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEtatProjet } from 'app/shared/model/etat-projet.model';
import { EtatProjetService } from './etat-projet.service';

@Component({
  templateUrl: './etat-projet-delete-dialog.component.html',
})
export class EtatProjetDeleteDialogComponent {
  etatProjet?: IEtatProjet;

  constructor(
    protected etatProjetService: EtatProjetService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.etatProjetService.delete(id).subscribe(() => {
      this.eventManager.broadcast('etatProjetListModification');
      this.activeModal.close();
    });
  }
}
