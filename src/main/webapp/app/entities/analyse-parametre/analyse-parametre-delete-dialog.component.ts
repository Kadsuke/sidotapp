import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAnalyseParametre } from 'app/shared/model/analyse-parametre.model';
import { AnalyseParametreService } from './analyse-parametre.service';

@Component({
  templateUrl: './analyse-parametre-delete-dialog.component.html',
})
export class AnalyseParametreDeleteDialogComponent {
  analyseParametre?: IAnalyseParametre;

  constructor(
    protected analyseParametreService: AnalyseParametreService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.analyseParametreService.delete(id).subscribe(() => {
      this.eventManager.broadcast('analyseParametreListModification');
      this.activeModal.close();
    });
  }
}
