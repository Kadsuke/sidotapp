import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefDomaine } from 'app/shared/model/ref-domaine.model';
import { RefDomaineService } from './ref-domaine.service';

@Component({
  templateUrl: './ref-domaine-delete-dialog.component.html',
})
export class RefDomaineDeleteDialogComponent {
  refDomaine?: IRefDomaine;

  constructor(
    protected refDomaineService: RefDomaineService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.refDomaineService.delete(id).subscribe(() => {
      this.eventManager.broadcast('refDomaineListModification');
      this.activeModal.close();
    });
  }
}
