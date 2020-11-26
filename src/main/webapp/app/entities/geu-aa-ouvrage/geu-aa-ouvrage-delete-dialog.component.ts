import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';
import { GeuAaOuvrageService } from './geu-aa-ouvrage.service';

@Component({
  templateUrl: './geu-aa-ouvrage-delete-dialog.component.html',
})
export class GeuAaOuvrageDeleteDialogComponent {
  geuAaOuvrage?: IGeuAaOuvrage;

  constructor(
    protected geuAaOuvrageService: GeuAaOuvrageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geuAaOuvrageService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geuAaOuvrageListModification');
      this.activeModal.close();
    });
  }
}
