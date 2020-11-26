import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPrefabricant, Prefabricant } from 'app/shared/model/prefabricant.model';
import { PrefabricantService } from './prefabricant.service';

@Component({
  selector: 'jhi-prefabricant-update',
  templateUrl: './prefabricant-update.component.html',
})
export class PrefabricantUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
  });

  constructor(protected prefabricantService: PrefabricantService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ prefabricant }) => {
      this.updateForm(prefabricant);
    });
  }

  updateForm(prefabricant: IPrefabricant): void {
    this.editForm.patchValue({
      id: prefabricant.id,
      libelle: prefabricant.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const prefabricant = this.createFromForm();
    if (prefabricant.id !== undefined) {
      this.subscribeToSaveResponse(this.prefabricantService.update(prefabricant));
    } else {
      this.subscribeToSaveResponse(this.prefabricantService.create(prefabricant));
    }
  }

  private createFromForm(): IPrefabricant {
    return {
      ...new Prefabricant(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrefabricant>>): void {
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
