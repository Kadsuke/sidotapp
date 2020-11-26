import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IModeEvacuationEauUsee, ModeEvacuationEauUsee } from 'app/shared/model/mode-evacuation-eau-usee.model';
import { ModeEvacuationEauUseeService } from './mode-evacuation-eau-usee.service';

@Component({
  selector: 'jhi-mode-evacuation-eau-usee-update',
  templateUrl: './mode-evacuation-eau-usee-update.component.html',
})
export class ModeEvacuationEauUseeUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
  });

  constructor(
    protected modeEvacuationEauUseeService: ModeEvacuationEauUseeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modeEvacuationEauUsee }) => {
      this.updateForm(modeEvacuationEauUsee);
    });
  }

  updateForm(modeEvacuationEauUsee: IModeEvacuationEauUsee): void {
    this.editForm.patchValue({
      id: modeEvacuationEauUsee.id,
      libelle: modeEvacuationEauUsee.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const modeEvacuationEauUsee = this.createFromForm();
    if (modeEvacuationEauUsee.id !== undefined) {
      this.subscribeToSaveResponse(this.modeEvacuationEauUseeService.update(modeEvacuationEauUsee));
    } else {
      this.subscribeToSaveResponse(this.modeEvacuationEauUseeService.create(modeEvacuationEauUsee));
    }
  }

  private createFromForm(): IModeEvacuationEauUsee {
    return {
      ...new ModeEvacuationEauUsee(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModeEvacuationEauUsee>>): void {
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
