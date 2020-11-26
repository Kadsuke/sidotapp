import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICategorieRessources } from 'app/shared/model/categorie-ressources.model';

@Component({
  selector: 'jhi-categorie-ressources-detail',
  templateUrl: './categorie-ressources-detail.component.html',
})
export class CategorieRessourcesDetailComponent implements OnInit {
  categorieRessources: ICategorieRessources | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ categorieRessources }) => (this.categorieRessources = categorieRessources));
  }

  previousState(): void {
    window.history.back();
  }
}
