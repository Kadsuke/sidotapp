import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IMacon, Macon } from 'app/shared/model/macon.model';
import { MaconService } from './macon.service';

@Component({
  selector: 'jhi-macon-update',
  templateUrl: './macon-update.component.html',
})
export class MaconUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
  });

  constructor(protected maconService: MaconService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ macon }) => {
      this.updateForm(macon);
    });
  }

  updateForm(macon: IMacon): void {
    this.editForm.patchValue({
      id: macon.id,
      libelle: macon.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const macon = this.createFromForm();
    if (macon.id !== undefined) {
      this.subscribeToSaveResponse(this.maconService.update(macon));
    } else {
      this.subscribeToSaveResponse(this.maconService.create(macon));
    }
  }

  private createFromForm(): IMacon {
    return {
      ...new Macon(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMacon>>): void {
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
