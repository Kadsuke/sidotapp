import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITacherons } from 'app/shared/model/tacherons.model';
import { TacheronsService } from './tacherons.service';

@Component({
  templateUrl: './tacherons-delete-dialog.component.html',
})
export class TacheronsDeleteDialogComponent {
  tacherons?: ITacherons;

  constructor(protected tacheronsService: TacheronsService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.tacheronsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('tacheronsListModification');
      this.activeModal.close();
    });
  }
}
