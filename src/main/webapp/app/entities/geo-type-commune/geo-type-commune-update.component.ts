import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeoTypeCommune, GeoTypeCommune } from 'app/shared/model/geo-type-commune.model';
import { GeoTypeCommuneService } from './geo-type-commune.service';

@Component({
  selector: 'jhi-geo-type-commune-update',
  templateUrl: './geo-type-commune-update.component.html',
})
export class GeoTypeCommuneUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
  });

  constructor(protected geoTypeCommuneService: GeoTypeCommuneService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoTypeCommune }) => {
      this.updateForm(geoTypeCommune);
    });
  }

  updateForm(geoTypeCommune: IGeoTypeCommune): void {
    this.editForm.patchValue({
      id: geoTypeCommune.id,
      libelle: geoTypeCommune.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geoTypeCommune = this.createFromForm();
    if (geoTypeCommune.id !== undefined) {
      this.subscribeToSaveResponse(this.geoTypeCommuneService.update(geoTypeCommune));
    } else {
      this.subscribeToSaveResponse(this.geoTypeCommuneService.create(geoTypeCommune));
    }
  }

  private createFromForm(): IGeoTypeCommune {
    return {
      ...new GeoTypeCommune(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeoTypeCommune>>): void {
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
