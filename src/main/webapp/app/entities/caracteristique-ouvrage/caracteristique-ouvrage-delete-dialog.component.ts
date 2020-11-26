import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICaracteristiqueOuvrage } from 'app/shared/model/caracteristique-ouvrage.model';
import { CaracteristiqueOuvrageService } from './caracteristique-ouvrage.service';

@Component({
  templateUrl: './caracteristique-ouvrage-delete-dialog.component.html',
})
export class CaracteristiqueOuvrageDeleteDialogComponent {
  caracteristiqueOuvrage?: ICaracteristiqueOuvrage;

  constructor(
    protected caracteristiqueOuvrageService: CaracteristiqueOuvrageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.caracteristiqueOuvrageService.delete(id).subscribe(() => {
      this.eventManager.broadcast('caracteristiqueOuvrageListModification');
      this.activeModal.close();
    });
  }
}
