import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeuSTEP, GeuSTEP } from 'app/shared/model/geu-step.model';
import { GeuSTEPService } from './geu-step.service';

@Component({
  selector: 'jhi-geu-step-update',
  templateUrl: './geu-step-update.component.html',
})
export class GeuSTEPUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelSTEP: [],
    responsable: [],
    contact: [],
  });

  constructor(protected geuSTEPService: GeuSTEPService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuSTEP }) => {
      this.updateForm(geuSTEP);
    });
  }

  updateForm(geuSTEP: IGeuSTEP): void {
    this.editForm.patchValue({
      id: geuSTEP.id,
      libelSTEP: geuSTEP.libelSTEP,
      responsable: geuSTEP.responsable,
      contact: geuSTEP.contact,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geuSTEP = this.createFromForm();
    if (geuSTEP.id !== undefined) {
      this.subscribeToSaveResponse(this.geuSTEPService.update(geuSTEP));
    } else {
      this.subscribeToSaveResponse(this.geuSTEPService.create(geuSTEP));
    }
  }

  private createFromForm(): IGeuSTEP {
    return {
      ...new GeuSTEP(),
      id: this.editForm.get(['id'])!.value,
      libelSTEP: this.editForm.get(['libelSTEP'])!.value,
      responsable: this.editForm.get(['responsable'])!.value,
      contact: this.editForm.get(['contact'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeuSTEP>>): void {
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
