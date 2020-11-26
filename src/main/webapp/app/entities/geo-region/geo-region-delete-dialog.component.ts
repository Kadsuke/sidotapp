import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeoRegion } from 'app/shared/model/geo-region.model';
import { GeoRegionService } from './geo-region.service';

@Component({
  templateUrl: './geo-region-delete-dialog.component.html',
})
export class GeoRegionDeleteDialogComponent {
  geoRegion?: IGeoRegion;

  constructor(protected geoRegionService: GeoRegionService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geoRegionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geoRegionListModification');
      this.activeModal.close();
    });
  }
}
