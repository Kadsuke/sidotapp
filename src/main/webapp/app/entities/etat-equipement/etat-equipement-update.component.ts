import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IEtatEquipement, EtatEquipement } from 'app/shared/model/etat-equipement.model';
import { EtatEquipementService } from './etat-equipement.service';

@Component({
  selector: 'jhi-etat-equipement-update',
  templateUrl: './etat-equipement-update.component.html',
})
export class EtatEquipementUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
  });

  constructor(protected etatEquipementService: EtatEquipementService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ etatEquipement }) => {
      this.updateForm(etatEquipement);
    });
  }

  updateForm(etatEquipement: IEtatEquipement): void {
    this.editForm.patchValue({
      id: etatEquipement.id,
      libelle: etatEquipement.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const etatEquipement = this.createFromForm();
    if (etatEquipement.id !== undefined) {
      this.subscribeToSaveResponse(this.etatEquipementService.update(etatEquipement));
    } else {
      this.subscribeToSaveResponse(this.etatEquipementService.create(etatEquipement));
    }
  }

  private createFromForm(): IEtatEquipement {
    return {
      ...new EtatEquipement(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEtatEquipement>>): void {
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
