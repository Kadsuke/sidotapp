import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeoProvince, GeoProvince } from 'app/shared/model/geo-province.model';
import { GeoProvinceService } from './geo-province.service';
import { IGeoRegion } from 'app/shared/model/geo-region.model';
import { GeoRegionService } from 'app/entities/geo-region/geo-region.service';

@Component({
  selector: 'jhi-geo-province-update',
  templateUrl: './geo-province-update.component.html',
})
export class GeoProvinceUpdateComponent implements OnInit {
  isSaving = false;
  georegions: IGeoRegion[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [],
    georegionId: [],
  });

  constructor(
    protected geoProvinceService: GeoProvinceService,
    protected geoRegionService: GeoRegionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoProvince }) => {
      this.updateForm(geoProvince);

      this.geoRegionService.query().subscribe((res: HttpResponse<IGeoRegion[]>) => (this.georegions = res.body || []));
    });
  }

  updateForm(geoProvince: IGeoProvince): void {
    this.editForm.patchValue({
      id: geoProvince.id,
      libelle: geoProvince.libelle,
      georegionId: geoProvince.georegionId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geoProvince = this.createFromForm();
    if (geoProvince.id !== undefined) {
      this.subscribeToSaveResponse(this.geoProvinceService.update(geoProvince));
    } else {
      this.subscribeToSaveResponse(this.geoProvinceService.create(geoProvince));
    }
  }

  private createFromForm(): IGeoProvince {
    return {
      ...new GeoProvince(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      georegionId: this.editForm.get(['georegionId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeoProvince>>): void {
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

  trackById(index: number, item: IGeoRegion): any {
    return item.id;
  }
}
