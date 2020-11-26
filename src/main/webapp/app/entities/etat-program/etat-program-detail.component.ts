import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEtatProgram } from 'app/shared/model/etat-program.model';

@Component({
  selector: 'jhi-etat-program-detail',
  templateUrl: './etat-program-detail.component.html',
})
export class EtatProgramDetailComponent implements OnInit {
  etatProgram: IEtatProgram | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ etatProgram }) => (this.etatProgram = etatProgram));
  }

  previousState(): void {
    window.history.back();
  }
}
