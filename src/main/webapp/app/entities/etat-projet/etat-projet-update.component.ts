import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEtatProjet, EtatProjet } from 'app/shared/model/etat-projet.model';
import { EtatProjetService } from './etat-projet.service';

@Component({
  selector: 'jhi-etat-projet-update',
  templateUrl: './etat-projet-update.component.html',
})
export class EtatProjetUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
  });

  constructor(protected etatProjetService: EtatProjetService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ etatProjet }) => {
      this.updateForm(etatProjet);
    });
  }

  updateForm(etatProjet: IEtatProjet): void {
    this.editForm.patchValue({
      id: etatProjet.id,
      libelle: etatProjet.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const etatProjet = this.createFromForm();
    if (etatProjet.id !== undefined) {
      this.subscribeToSaveResponse(this.etatProjetService.update(etatProjet));
    } else {
      this.subscribeToSaveResponse(this.etatProjetService.create(etatProjet));
    }
  }

  private createFromForm(): IEtatProjet {
    return {
      ...new EtatProjet(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEtatProjet>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
