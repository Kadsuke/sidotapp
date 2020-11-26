import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeoSecteur, GeoSecteur } from 'app/shared/model/geo-secteur.model';
import { GeoSecteurService } from './geo-secteur.service';
import { IGeoLocalite } from 'app/shared/model/geo-localite.model';
import { GeoLocaliteService } from 'app/entities/geo-localite/geo-localite.service';

@Component({
  selector: 'jhi-geo-secteur-update',
  templateUrl: './geo-secteur-update.component.html',
})
export class GeoSecteurUpdateComponent implements OnInit {
  isSaving = false;
  geolocalites: IGeoLocalite[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [],
    geolocaliteId: [],
  });

  constructor(
    protected geoSecteurService: GeoSecteurService,
    protected geoLocaliteService: GeoLocaliteService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geoSecteur }) => {
      this.updateForm(geoSecteur);

      this.geoLocaliteService.query().subscribe((res: HttpResponse<IGeoLocalite[]>) => (this.geolocalites = res.body || []));
    });
  }

  updateForm(geoSecteur: IGeoSecteur): void {
    this.editForm.patchValue({
      id: geoSecteur.id,
      libelle: geoSecteur.libelle,
      geolocaliteId: geoSecteur.geolocaliteId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geoSecteur = this.createFromForm();
    if (geoSecteur.id !== undefined) {
      this.subscribeToSaveResponse(this.geoSecteurService.update(geoSecteur));
    } else {
      this.subscribeToSaveResponse(this.geoSecteurService.create(geoSecteur));
    }
  }

  private createFromForm(): IGeoSecteur {
    return {
      ...new GeoSecteur(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      geolocaliteId: this.editForm.get(['geolocaliteId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeoSecteur>>): void {
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

  trackById(index: number, item: IGeoLocalite): any {
    return item.id;
  }
}
