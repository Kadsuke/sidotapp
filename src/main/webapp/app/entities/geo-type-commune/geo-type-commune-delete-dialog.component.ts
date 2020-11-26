import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeoTypeCommune } from 'app/shared/model/geo-type-commune.model';
import { GeoTypeCommuneService } from './geo-type-commune.service';

@Component({
  templateUrl: './geo-type-commune-delete-dialog.component.html',
})
export class GeoTypeCommuneDeleteDialogComponent {
  geoTypeCommune?: IGeoTypeCommune;

  constructor(
    protected geoTypeCommuneService: GeoTypeCommuneService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geoTypeCommuneService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geoTypeCommuneListModification');
      this.activeModal.close();
    });
  }
}
