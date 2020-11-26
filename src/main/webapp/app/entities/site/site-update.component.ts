import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISite, Site } from 'app/shared/model/site.model';
import { SiteService } from './site.service';
import { ICentre } from 'app/shared/model/centre.model';
import { CentreService } from 'app/entities/centre/centre.service';

@Component({
  selector: 'jhi-site-update',
  templateUrl: './site-update.component.html',
})
export class SiteUpdateComponent implements OnInit {
  isSaving = false;
  centres: ICentre[] = [];

  editForm = this.fb.group({
    id: [],
    libelle: [],
    responsable: [],
    contact: [],
    centreId: [],
  });

  constructor(
    protected siteService: SiteService,
    protected centreService: CentreService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ site }) => {
      this.updateForm(site);

      this.centreService.query().subscribe((res: HttpResponse<ICentre[]>) => (this.centres = res.body || []));
    });
  }

  updateForm(site: ISite): void {
    this.editForm.patchValue({
      id: site.id,
      libelle: site.libelle,
      responsable: site.responsable,
      contact: site.contact,
      centreId: site.centreId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const site = this.createFromForm();
    if (site.id !== undefined) {
      this.subscribeToSaveResponse(this.siteService.update(site));
    } else {
      this.subscribeToSaveResponse(this.siteService.create(site));
    }
  }

  private createFromForm(): ISite {
    return {
      ...new Site(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
      responsable: this.editForm.get(['responsable'])!.value,
      contact: this.editForm.get(['contact'])!.value,
      centreId: this.editForm.get(['centreId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISite>>): void {
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

  trackById(index: number, item: ICentre): any {
    return item.id;
  }
}
