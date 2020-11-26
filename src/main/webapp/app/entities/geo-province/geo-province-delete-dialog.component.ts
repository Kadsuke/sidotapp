import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeoProvince } from 'app/shared/model/geo-province.model';
import { GeoProvinceService } from './geo-province.service';

@Component({
  templateUrl: './geo-province-delete-dialog.component.html',
})
export class GeoProvinceDeleteDialogComponent {
  geoProvince?: IGeoProvince;

  constructor(
    protected geoProvinceService: GeoProvinceService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geoProvinceService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geoProvinceListModification');
      this.activeModal.close();
    });
  }
}
