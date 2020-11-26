import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeoLot } from 'app/shared/model/geo-lot.model';
import { GeoLotService } from './geo-lot.service';

@Component({
  templateUrl: './geo-lot-delete-dialog.component.html',
})
export class GeoLotDeleteDialogComponent {
  geoLot?: IGeoLot;

  constructor(protected geoLotService: GeoLotService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geoLotService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geoLotListModification');
      this.activeModal.close();
    });
  }
}
