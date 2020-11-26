import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEtatProjet } from 'app/shared/model/etat-projet.model';

@Component({
  selector: 'jhi-etat-projet-detail',
  templateUrl: './etat-projet-detail.component.html',
})
export class EtatProjetDetailComponent implements OnInit {
  etatProjet: IEtatProjet | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ etatProjet }) => (this.etatProjet = etatProjet));
  }

  previousState(): void {
    window.history.back();
  }
}
