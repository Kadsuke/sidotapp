import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IGeuPrevisionSTEP, GeuPrevisionSTEP } from 'app/shared/model/geu-prevision-step.model';
import { GeuPrevisionSTEPService } from './geu-prevision-step.service';
import { IGeuSTEP } from 'app/shared/model/geu-step.model';
import { GeuSTEPService } from 'app/entities/geu-step/geu-step.service';

@Component({
  selector: 'jhi-geu-prevision-step-update',
  templateUrl: './geu-prevision-step-update.component.html',
})
export class GeuPrevisionSTEPUpdateComponent implements OnInit {
  isSaving = false;
  geusteps: IGeuSTEP[] = [];

  editForm = this.fb.group({
    id: [],
    datePrevStep: [null, [Validators.required]],
    volumePrevStep: [null, [Validators.required]],
    geustepId: [],
  });

  constructor(
    protected geuPrevisionSTEPService: GeuPrevisionSTEPService,
    protected geuSTEPService: GeuSTEPService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuPrevisionSTEP }) => {
      if (!geuPrevisionSTEP.id) {
        const today = moment().startOf('day');
        geuPrevisionSTEP.datePrevStep = today;
      }

      this.updateForm(geuPrevisionSTEP);

      this.geuSTEPService.query().subscribe((res: HttpResponse<IGeuSTEP[]>) => (this.geusteps = res.body || []));
    });
  }

  updateForm(geuPrevisionSTEP: IGeuPrevisionSTEP): void {
    this.editForm.patchValue({
      id: geuPrevisionSTEP.id,
      datePrevStep: geuPrevisionSTEP.datePrevStep ? geuPrevisionSTEP.datePrevStep.format(DATE_TIME_FORMAT) : null,
      volumePrevStep: geuPrevisionSTEP.volumePrevStep,
      geustepId: geuPrevisionSTEP.geustepId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geuPrevisionSTEP = this.createFromForm();
    if (geuPrevisionSTEP.id !== undefined) {
      this.subscribeToSaveResponse(this.geuPrevisionSTEPService.update(geuPrevisionSTEP));
    } else {
      this.subscribeToSaveResponse(this.geuPrevisionSTEPService.create(geuPrevisionSTEP));
    }
  }

  private createFromForm(): IGeuPrevisionSTEP {
    return {
      ...new GeuPrevisionSTEP(),
      id: this.editForm.get(['id'])!.value,
      datePrevStep: this.editForm.get(['datePrevStep'])!.value
        ? moment(this.editForm.get(['datePrevStep'])!.value, DATE_TIME_FORMAT)
        : undefined,
      volumePrevStep: this.editForm.get(['volumePrevStep'])!.value,
      geustepId: this.editForm.get(['geustepId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeuPrevisionSTEP>>): void {
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

  trackById(index: number, item: IGeuSTEP): any {
    return item.id;
  }
}
