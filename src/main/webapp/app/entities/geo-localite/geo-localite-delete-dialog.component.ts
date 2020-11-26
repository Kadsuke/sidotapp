import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeoLocalite } from 'app/shared/model/geo-localite.model';
import { GeoLocaliteService } from './geo-localite.service';

@Component({
  templateUrl: './geo-localite-delete-dialog.component.html',
})
export class GeoLocaliteDeleteDialogComponent {
  geoLocalite?: IGeoLocalite;

  constructor(
    protected geoLocaliteService: GeoLocaliteService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geoLocaliteService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geoLocaliteListModification');
      this.activeModal.close();
    });
  }
}
