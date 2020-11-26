import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeoCommune, GeoCommune } from 'app/shared/model/geo-commune.model';
import { GeoCommuneService } from './geo-commune.service';
import { IGeoProvince } from 'app/shared/model/geo-province.model';
import { GeoProvinceService } from 'app/entities/geo-province/geo-province.service';
import { IGeoTypeCommune } from 'app/shared/model/geo-type-commune.model';
import { GeoTypeCommuneService } from 'app/entities/geo-type-commune/geo-type-commune.service';

type SelectableEntity = IGeoProvince | IGeoTypeCommune;

@Component({
  selector: 'jhi-geo-commune-update',
  templateUrl: './geo-commune-update.component.html',
})
export class GeoCommuneUpdateComponent implements OnInit {
  isSaving = false;
  geoprovinces: IGeoProvince[] = [];
  geotypecommunes: IGeoTypeCommune[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [],
    geoprovinceId: [],
    geotypecommuneId: [],
  });

  constructor(
    protected geoCommuneService: GeoCommuneService,
    protected geoProvinceService: GeoProvinceService,
    protected geoTypeCommuneService: GeoTypeCommuneService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoCommune }) => {
      this.updateForm(geoCommune);

      this.geoProvinceService.query().subscribe((res: HttpResponse<IGeoProvince[]>) => (this.geoprovinces = res.body || []));

      this.geoTypeCommuneService.query().subscribe((res: HttpResponse<IGeoTypeCommune[]>) => (this.geotypecommunes = res.body || []));
    });
  }

  updateForm(geoCommune: IGeoCommune): void {
    this.editForm.patchValue({
      id: geoCommune.id,
      libelle: geoCommune.libelle,
      geoprovinceId: geoCommune.geoprovinceId,
      geotypecommuneId: geoCommune.geotypecommuneId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geoCommune = this.createFromForm();
    if (geoCommune.id !== undefined) {
      this.subscribeToSaveResponse(this.geoCommuneService.update(geoCommune));
    } else {
      this.subscribeToSaveResponse(this.geoCommuneService.create(geoCommune));
    }
  }

  private createFromForm(): IGeoCommune {
    return {
      ...new GeoCommune(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      geoprovinceId: this.editForm.get(['geoprovinceId'])!.value,
      geotypecommuneId: this.editForm.get(['geotypecommuneId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeoCommune>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
