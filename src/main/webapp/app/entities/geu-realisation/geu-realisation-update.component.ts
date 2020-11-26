import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IGeuRealisation, GeuRealisation } from 'app/shared/model/geu-realisation.model';
import { GeuRealisationService } from './geu-realisation.service';
import { IGeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';
import { GeuAaOuvrageService } from 'app/entities/geu-aa-ouvrage/geu-aa-ouvrage.service';

@Component({
  selector: 'jhi-geu-realisation-update',
  templateUrl: './geu-realisation-update.component.html',
})
export class GeuRealisationUpdateComponent implements OnInit {
  isSaving = false;
  geuaaouvrages: IGeuAaOuvrage[] = [];

  editForm = this.fb.group({
    id: [],
    nbRealisation: [],
    geuaaouvrageId: [],
  });

  constructor(
    protected geuRealisationService: GeuRealisationService,
    protected geuAaOuvrageService: GeuAaOuvrageService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuRealisation }) => {
      this.updateForm(geuRealisation);

      this.geuAaOuvrageService.query().subscribe((res: HttpResponse<IGeuAaOuvrage[]>) => (this.geuaaouvrages = res.body || []));
    });
  }

  updateForm(geuRealisation: IGeuRealisation): void {
    this.editForm.patchValue({
      id: geuRealisation.id,
      nbRealisation: geuRealisation.nbRealisation,
      geuaaouvrageId: geuRealisation.geuaaouvrageId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geuRealisation = this.createFromForm();
    if (geuRealisation.id !== undefined) {
      this.subscribeToSaveResponse(this.geuRealisationService.update(geuRealisation));
    } else {
      this.subscribeToSaveResponse(this.geuRealisationService.create(geuRealisation));
    }
  }

  private createFromForm(): IGeuRealisation {
    return {
      ...new GeuRealisation(),
      id: this.editForm.get(['id'])!.value,
      nbRealisation: this.editForm.get(['nbRealisation'])!.value,
      geuaaouvrageId: this.editForm.get(['geuaaouvrageId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeuRealisation>>): void {
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

  trackById(index: number, item: IGeuAaOuvrage): any {
    return item.id;
  }
}
