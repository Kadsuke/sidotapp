import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEtatEquipement } from 'app/shared/model/etat-equipement.model';
import { EtatEquipementService } from './etat-equipement.service';

@Component({
  templateUrl: './etat-equipement-delete-dialog.component.html',
})
export class EtatEquipementDeleteDialogComponent {
  etatEquipement?: IEtatEquipement;

  constructor(
    protected etatEquipementService: EtatEquipementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.etatEquipementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('etatEquipementListModification');
      this.activeModal.close();
    });
  }
}
