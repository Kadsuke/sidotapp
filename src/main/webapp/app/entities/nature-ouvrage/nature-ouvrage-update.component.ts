import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { INatureOuvrage, NatureOuvrage } from 'app/shared/model/nature-ouvrage.model';
import { NatureOuvrageService } from './nature-ouvrage.service';

@Component({
  selector: 'jhi-nature-ouvrage-update',
  templateUrl: './nature-ouvrage-update.component.html',
})
export class NatureOuvrageUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
  });

  constructor(protected natureOuvrageService: NatureOuvrageService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ natureOuvrage }) => {
      this.updateForm(natureOuvrage);
    });
  }

  updateForm(natureOuvrage: INatureOuvrage): void {
    this.editForm.patchValue({
      id: natureOuvrage.id,
      libelle: natureOuvrage.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const natureOuvrage = this.createFromForm();
    if (natureOuvrage.id !== undefined) {
      this.subscribeToSaveResponse(this.natureOuvrageService.update(natureOuvrage));
    } else {
      this.subscribeToSaveResponse(this.natureOuvrageService.create(natureOuvrage));
    }
  }

  private createFromForm(): INatureOuvrage {
    return {
      ...new NatureOuvrage(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<INatureOuvrage>>): void {
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
