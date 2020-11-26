import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeoSection, GeoSection } from 'app/shared/model/geo-section.model';
import { GeoSectionService } from './geo-section.service';
import { IGeoSecteur } from 'app/shared/model/geo-secteur.model';
import { GeoSecteurService } from 'app/entities/geo-secteur/geo-secteur.service';

@Component({
  selector: 'jhi-geo-section-update',
  templateUrl: './geo-section-update.component.html',
})
export class GeoSectionUpdateComponent implements OnInit {
  isSaving = false;
  geosecteurs: IGeoSecteur[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [],
    geosecteurId: [],
  });

  constructor(
    protected geoSectionService: GeoSectionService,
    protected geoSecteurService: GeoSecteurService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoSection }) => {
      this.updateForm(geoSection);

      this.geoSecteurService.query().subscribe((res: HttpResponse<IGeoSecteur[]>) => (this.geosecteurs = res.body || []));
    });
  }

  updateForm(geoSection: IGeoSection): void {
    this.editForm.patchValue({
      id: geoSection.id,
      libelle: geoSection.libelle,
      geosecteurId: geoSection.geosecteurId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geoSection = this.createFromForm();
    if (geoSection.id !== undefined) {
      this.subscribeToSaveResponse(this.geoSectionService.update(geoSection));
    } else {
      this.subscribeToSaveResponse(this.geoSectionService.create(geoSection));
    }
  }

  private createFromForm(): IGeoSection {
    return {
      ...new GeoSection(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      geosecteurId: this.editForm.get(['geosecteurId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeoSection>>): void {
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

  trackById(index: number, item: IGeoSecteur): any {
    return item.id;
  }
}
