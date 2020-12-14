import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeuSTBV, GeuSTBV } from 'app/shared/model/geu-stbv.model';
import { GeuSTBVService } from './geu-stbv.service';

@Component({
  selector: 'jhi-geu-stbv-update',
  templateUrl: './geu-stbv-update.component.html',
})
export class GeuSTBVUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelStbv: [null, [Validators.required]],
    responsable: [null, [Validators.required]],
    contact: [null, [Validators.required]],
  });

  constructor(protected geuSTBVService: GeuSTBVService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuSTBV }) => {
      this.updateForm(geuSTBV);
    });
  }

  updateForm(geuSTBV: IGeuSTBV): void {
    this.editForm.patchValue({
      id: geuSTBV.id,
      libelStbv: geuSTBV.libelStbv,
      responsable: geuSTBV.responsable,
      contact: geuSTBV.contact,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geuSTBV = this.createFromForm();
    if (geuSTBV.id !== undefined) {
      this.subscribeToSaveResponse(this.geuSTBVService.update(geuSTBV));
    } else {
      this.subscribeToSaveResponse(this.geuSTBVService.create(geuSTBV));
    }
  }

  private createFromForm(): IGeuSTBV {
    return {
      ...new GeuSTBV(),
      id: this.editForm.get(['id'])!.value,
      libelStbv: this.editForm.get(['libelStbv'])!.value,
      responsable: this.editForm.get(['responsable'])!.value,
      contact: this.editForm.get(['contact'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeuSTBV>>): void {
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
