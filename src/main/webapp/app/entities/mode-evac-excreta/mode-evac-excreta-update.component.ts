import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IModeEvacExcreta, ModeEvacExcreta } from 'app/shared/model/mode-evac-excreta.model';
import { ModeEvacExcretaService } from './mode-evac-excreta.service';

@Component({
  selector: 'jhi-mode-evac-excreta-update',
  templateUrl: './mode-evac-excreta-update.component.html',
})
export class ModeEvacExcretaUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
  });

  constructor(
    protected modeEvacExcretaService: ModeEvacExcretaService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modeEvacExcreta }) => {
      this.updateForm(modeEvacExcreta);
    });
  }

  updateForm(modeEvacExcreta: IModeEvacExcreta): void {
    this.editForm.patchValue({
      id: modeEvacExcreta.id,
      libelle: modeEvacExcreta.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const modeEvacExcreta = this.createFromForm();
    if (modeEvacExcreta.id !== undefined) {
      this.subscribeToSaveResponse(this.modeEvacExcretaService.update(modeEvacExcreta));
    } else {
      this.subscribeToSaveResponse(this.modeEvacExcretaService.create(modeEvacExcreta));
    }
  }

  private createFromForm(): IModeEvacExcreta {
    return {
      ...new ModeEvacExcreta(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModeEvacExcreta>>): void {
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
