import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IGeuPSA, GeuPSA } from 'app/shared/model/geu-psa.model';
import { GeuPSAService } from './geu-psa.service';
import { IGeoCommune } from 'app/shared/model/geo-commune.model';
import { GeoCommuneService } from 'app/entities/geo-commune/geo-commune.service';

@Component({
  selector: 'jhi-geu-psa-update',
  templateUrl: './geu-psa-update.component.html',
})
export class GeuPSAUpdateComponent implements OnInit {
  isSaving = false;
  geocommunes: IGeoCommune[] = [];

  editForm = this.fb.group({
    id: [],
    dateElaboration: [null, [Validators.required]],
    dateMiseEnOeuvre: [null, [Validators.required]],
    geocommuneId: [],
  });

  constructor(
    protected geuPSAService: GeuPSAService,
    protected geoCommuneService: GeoCommuneService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuPSA }) => {
      if (!geuPSA.id) {
        const today = moment().startOf('day');
        geuPSA.dateElaboration = today;
        geuPSA.dateMiseEnOeuvre = today;
      }

      this.updateForm(geuPSA);

      this.geoCommuneService.query().subscribe((res: HttpResponse<IGeoCommune[]>) => (this.geocommunes = res.body || []));
    });
  }

  updateForm(geuPSA: IGeuPSA): void {
    this.editForm.patchValue({
      id: geuPSA.id,
      dateElaboration: geuPSA.dateElaboration ? geuPSA.dateElaboration.format(DATE_TIME_FORMAT) : null,
      dateMiseEnOeuvre: geuPSA.dateMiseEnOeuvre ? geuPSA.dateMiseEnOeuvre.format(DATE_TIME_FORMAT) : null,
      geocommuneId: geuPSA.geocommuneId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geuPSA = this.createFromForm();
    if (geuPSA.id !== undefined) {
      this.subscribeToSaveResponse(this.geuPSAService.update(geuPSA));
    } else {
      this.subscribeToSaveResponse(this.geuPSAService.create(geuPSA));
    }
  }

  private createFromForm(): IGeuPSA {
    return {
      ...new GeuPSA(),
      id: this.editForm.get(['id'])!.value,
      dateElaboration: this.editForm.get(['dateElaboration'])!.value
        ? moment(this.editForm.get(['dateElaboration'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dateMiseEnOeuvre: this.editForm.get(['dateMiseEnOeuvre'])!.value
        ? moment(this.editForm.get(['dateMiseEnOeuvre'])!.value, DATE_TIME_FORMAT)
        : undefined,
      geocommuneId: this.editForm.get(['geocommuneId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeuPSA>>): void {
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

  trackById(index: number, item: IGeoCommune): any {
    return item.id;
  }
}
