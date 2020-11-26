import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGeoSecteur } from 'app/shared/model/geo-secteur.model';
import { GeoSecteurService } from './geo-secteur.service';

@Component({
  templateUrl: './geo-secteur-delete-dialog.component.html',
})
export class GeoSecteurDeleteDialogComponent {
  geoSecteur?: IGeoSecteur;

  constructor(
    protected geoSecteurService: GeoSecteurService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.geoSecteurService.delete(id).subscribe(() => {
      this.eventManager.broadcast('geoSecteurListModification');
      this.activeModal.close();
    });
  }
}
