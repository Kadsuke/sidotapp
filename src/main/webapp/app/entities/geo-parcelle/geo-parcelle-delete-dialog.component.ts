import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeoParcelle } from 'app/shared/model/geo-parcelle.model';
import { GeoParcelleService } from './geo-parcelle.service';

@Component({
  templateUrl: './geo-parcelle-delete-dialog.component.html',
})
export class GeoParcelleDeleteDialogComponent {
  geoParcelle?: IGeoParcelle;

  constructor(
    protected geoParcelleService: GeoParcelleService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geoParcelleService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geoParcelleListModification');
      this.activeModal.close();
    });
  }
}
