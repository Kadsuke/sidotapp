import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeoSection } from 'app/shared/model/geo-section.model';
import { GeoSectionService } from './geo-section.service';

@Component({
  templateUrl: './geo-section-delete-dialog.component.html',
})
export class GeoSectionDeleteDialogComponent {
  geoSection?: IGeoSection;

  constructor(
    protected geoSectionService: GeoSectionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geoSectionService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geoSectionListModification');
      this.activeModal.close();
    });
  }
}
