import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ITacherons, Tacherons } from 'app/shared/model/tacherons.model';
import { TacheronsService } from './tacherons.service';

@Component({
  selector: 'jhi-tacherons-update',
  templateUrl: './tacherons-update.component.html',
})
export class TacheronsUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nom: [],
    tel: [],
    adresse: [],
  });

  constructor(protected tacheronsService: TacheronsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ tacherons }) => {
      this.updateForm(tacherons);
    });
  }

  updateForm(tacherons: ITacherons): void {
    this.editForm.patchValue({
      id: tacherons.id,
      nom: tacherons.nom,
      tel: tacherons.tel,
      adresse: tacherons.adresse,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const tacherons = this.createFromForm();
    if (tacherons.id !== undefined) {
      this.subscribeToSaveResponse(this.tacheronsService.update(tacherons));
    } else {
      this.subscribeToSaveResponse(this.tacheronsService.create(tacherons));
    }
  }

  private createFromForm(): ITacherons {
    return {
      ...new Tacherons(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      tel: this.editForm.get(['tel'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITacherons>>): void {
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
