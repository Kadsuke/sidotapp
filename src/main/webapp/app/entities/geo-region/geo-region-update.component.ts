import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeoRegion, GeoRegion } from 'app/shared/model/geo-region.model';
import { GeoRegionService } from './geo-region.service';

@Component({
  selector: 'jhi-geo-region-update',
  templateUrl: './geo-region-update.component.html',
})
export class GeoRegionUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
  });

  constructor(protected geoRegionService: GeoRegionService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoRegion }) => {
      this.updateForm(geoRegion);
    });
  }

  updateForm(geoRegion: IGeoRegion): void {
    this.editForm.patchValue({
      id: geoRegion.id,
      libelle: geoRegion.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geoRegion = this.createFromForm();
    if (geoRegion.id !== undefined) {
      this.subscribeToSaveResponse(this.geoRegionService.update(geoRegion));
    } else {
      this.subscribeToSaveResponse(this.geoRegionService.create(geoRegion));
    }
  }

  private createFromForm(): IGeoRegion {
    return {
      ...new GeoRegion(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeoRegion>>): void {
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
