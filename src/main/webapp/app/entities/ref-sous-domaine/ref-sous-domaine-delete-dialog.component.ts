import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IRefSousDomaine } from 'app/shared/model/ref-sous-domaine.model';
import { RefSousDomaineService } from './ref-sous-domaine.service';

@Component({
  templateUrl: './ref-sous-domaine-delete-dialog.component.html',
})
export class RefSousDomaineDeleteDialogComponent {
  refSousDomaine?: IRefSousDomaine;

  constructor(
    protected refSousDomaineService: RefSousDomaineService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.refSousDomaineService.delete(id).subscribe(() => {
      this.eventManager.broadcast('refSousDomaineListModification');
      this.activeModal.close();
    });
  }
}
