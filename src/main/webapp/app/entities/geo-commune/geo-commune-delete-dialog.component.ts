import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeoCommune } from 'app/shared/model/geo-commune.model';
import { GeoCommuneService } from './geo-commune.service';

@Component({
  templateUrl: './geo-commune-delete-dialog.component.html',
})
export class GeoCommuneDeleteDialogComponent {
  geoCommune?: IGeoCommune;

  constructor(
    protected geoCommuneService: GeoCommuneService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geoCommuneService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geoCommuneListModification');
      this.activeModal.close();
    });
  }
}
