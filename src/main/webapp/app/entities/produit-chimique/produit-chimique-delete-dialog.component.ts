import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IProduitChimique } from 'app/shared/model/produit-chimique.model';
import { ProduitChimiqueService } from './produit-chimique.service';

@Component({
  templateUrl: './produit-chimique-delete-dialog.component.html',
})
export class ProduitChimiqueDeleteDialogComponent {
  produitChimique?: IProduitChimique;

  constructor(
    protected produitChimiqueService: ProduitChimiqueService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.produitChimiqueService.delete(id).subscribe(() => {
      this.eventManager.broadcast('produitChimiqueListModification');
      this.activeModal.close();
    });
  }
}
