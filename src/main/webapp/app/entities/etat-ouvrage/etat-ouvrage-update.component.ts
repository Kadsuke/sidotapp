import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEtatOuvrage, EtatOuvrage } from 'app/shared/model/etat-ouvrage.model';
import { EtatOuvrageService } from './etat-ouvrage.service';

@Component({
  selector: 'jhi-etat-ouvrage-update',
  templateUrl: './etat-ouvrage-update.component.html',
})
export class EtatOuvrageUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
  });

  constructor(protected etatOuvrageService: EtatOuvrageService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ etatOuvrage }) => {
      this.updateForm(etatOuvrage);
    });
  }

  updateForm(etatOuvrage: IEtatOuvrage): void {
    this.editForm.patchValue({
      id: etatOuvrage.id,
      libelle: etatOuvrage.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const etatOuvrage = this.createFromForm();
    if (etatOuvrage.id !== undefined) {
      this.subscribeToSaveResponse(this.etatOuvrageService.update(etatOuvrage));
    } else {
      this.subscribeToSaveResponse(this.etatOuvrageService.create(etatOuvrage));
    }
  }

  private createFromForm(): IEtatOuvrage {
    return {
      ...new EtatOuvrage(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEtatOuvrage>>): void {
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
