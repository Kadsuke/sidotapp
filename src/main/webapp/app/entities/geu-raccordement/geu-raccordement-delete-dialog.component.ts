import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeuRaccordement } from 'app/shared/model/geu-raccordement.model';
import { GeuRaccordementService } from './geu-raccordement.service';

@Component({
  templateUrl: './geu-raccordement-delete-dialog.component.html',
})
export class GeuRaccordementDeleteDialogComponent {
  geuRaccordement?: IGeuRaccordement;

  constructor(
    protected geuRaccordementService: GeuRaccordementService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geuRaccordementService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geuRaccordementListModification');
      this.activeModal.close();
    });
  }
}
