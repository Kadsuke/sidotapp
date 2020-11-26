import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IProduitChimique } from 'app/shared/model/produit-chimique.model';

@Component({
  selector: 'jhi-produit-chimique-detail',
  templateUrl: './produit-chimique-detail.component.html',
})
export class ProduitChimiqueDetailComponent implements OnInit {
  produitChimique: IProduitChimique | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ produitChimique }) => (this.produitChimique = produitChimique));
  }

  previousState(): void {
    window.history.back();
  }
}
