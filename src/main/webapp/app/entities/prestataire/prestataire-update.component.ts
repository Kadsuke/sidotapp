import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPrestataire, Prestataire } from 'app/shared/model/prestataire.model';
import { PrestataireService } from './prestataire.service';

@Component({
  selector: 'jhi-prestataire-update',
  templateUrl: './prestataire-update.component.html',
})
export class PrestataireUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
    responsable: [],
    contact: [],
  });

  constructor(protected prestataireService: PrestataireService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ prestataire }) => {
      this.updateForm(prestataire);
    });
  }

  updateForm(prestataire: IPrestataire): void {
    this.editForm.patchValue({
      id: prestataire.id,
      libelle: prestataire.libelle,
      responsable: prestataire.responsable,
      contact: prestataire.contact,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const prestataire = this.createFromForm();
    if (prestataire.id !== undefined) {
      this.subscribeToSaveResponse(this.prestataireService.update(prestataire));
    } else {
      this.subscribeToSaveResponse(this.prestataireService.create(prestataire));
    }
  }

  private createFromForm(): IPrestataire {
    return {
      ...new Prestataire(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      responsable: this.editForm.get(['responsable'])!.value,
      contact: this.editForm.get(['contact'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrestataire>>): void {
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
