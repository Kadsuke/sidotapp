import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INatureOuvrage } from 'app/shared/model/nature-ouvrage.model';
import { NatureOuvrageService } from './nature-ouvrage.service';

@Component({
  templateUrl: './nature-ouvrage-delete-dialog.component.html',
})
export class NatureOuvrageDeleteDialogComponent {
  natureOuvrage?: INatureOuvrage;

  constructor(
    protected natureOuvrageService: NatureOuvrageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.natureOuvrageService.delete(id).subscribe(() => {
      this.eventManager.broadcast('natureOuvrageListModification');
      this.activeModal.close();
    });
  }
}
