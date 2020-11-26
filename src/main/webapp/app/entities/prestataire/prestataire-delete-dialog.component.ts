import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPrestataire } from 'app/shared/model/prestataire.model';
import { PrestataireService } from './prestataire.service';

@Component({
  templateUrl: './prestataire-delete-dialog.component.html',
})
export class PrestataireDeleteDialogComponent {
  prestataire?: IPrestataire;

  constructor(
    protected prestataireService: PrestataireService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.prestataireService.delete(id).subscribe(() => {
      this.eventManager.broadcast('prestataireListModification');
      this.activeModal.close();
    });
  }
}
