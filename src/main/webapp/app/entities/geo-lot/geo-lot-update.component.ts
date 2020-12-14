import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeoLot, GeoLot } from 'app/shared/model/geo-lot.model';
import { GeoLotService } from './geo-lot.service';
import { IGeoSection } from 'app/shared/model/geo-section.model';
import { GeoSectionService } from 'app/entities/geo-section/geo-section.service';

@Component({
  selector: 'jhi-geo-lot-update',
  templateUrl: './geo-lot-update.component.html',
})
export class GeoLotUpdateComponent implements OnInit {
  isSaving = false;
  geosections: IGeoSection[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
    geosectionId: [],
  });

  constructor(
    protected geoLotService: GeoLotService,
    protected geoSectionService: GeoSectionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoLot }) => {
      this.updateForm(geoLot);

      this.geoSectionService.query().subscribe((res: HttpResponse<IGeoSection[]>) => (this.geosections = res.body || []));
    });
  }

  updateForm(geoLot: IGeoLot): void {
    this.editForm.patchValue({
      id: geoLot.id,
      libelle: geoLot.libelle,
      geosectionId: geoLot.geosectionId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geoLot = this.createFromForm();
    if (geoLot.id !== undefined) {
      this.subscribeToSaveResponse(this.geoLotService.update(geoLot));
    } else {
      this.subscribeToSaveResponse(this.geoLotService.create(geoLot));
    }
  }

  private createFromForm(): IGeoLot {
    return {
      ...new GeoLot(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      geosectionId: this.editForm.get(['geosectionId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeoLot>>): void {
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

  trackById(index: number, item: IGeoSection): any {
    return item.id;
  }
}
