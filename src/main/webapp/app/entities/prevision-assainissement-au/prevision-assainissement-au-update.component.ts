import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IPrevisionAssainissementAu, PrevisionAssainissementAu } from 'app/shared/model/prevision-assainissement-au.model';
import { PrevisionAssainissementAuService } from './prevision-assainissement-au.service';
import { IRefAnnee } from 'app/shared/model/ref-annee.model';
import { RefAnneeService } from 'app/entities/ref-annee/ref-annee.service';
import { ICentre } from 'app/shared/model/centre.model';
import { CentreService } from 'app/entities/centre/centre.service';

type SelectableEntity = IRefAnnee | ICentre;

@Component({
  selector: 'jhi-prevision-assainissement-au-update',
  templateUrl: './prevision-assainissement-au-update.component.html',
})
export class PrevisionAssainissementAuUpdateComponent implements OnInit {
  isSaving = false;
  refannees: IRefAnnee[] = [];
  centres: ICentre[] = [];

  editForm = this.fb.group({
    id: [],
    nbLatrine: [null, [Validators.required]],
    nbPuisard: [null, [Validators.required]],
    nbPublic: [null, [Validators.required]],
    nbScolaire: [null, [Validators.required]],
    centreDeSante: [null, [Validators.required]],
    population: [null, [Validators.required]],
    refanneeId: [],
    centreId: [],
  });

  constructor(
    protected previsionAssainissementAuService: PrevisionAssainissementAuService,
    protected refAnneeService: RefAnneeService,
    protected centreService: CentreService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ previsionAssainissementAu }) => {
      this.updateForm(previsionAssainissementAu);

      this.refAnneeService
        .query({ filter: 'previsionassainissementau-is-null' })
        .pipe(
          map((res: HttpResponse<IRefAnnee[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IRefAnnee[]) => {
          if (!previsionAssainissementAu.refanneeId) {
            this.refannees = resBody;
          } else {
            this.refAnneeService
              .find(previsionAssainissementAu.refanneeId)
              .pipe(
                map((subRes: HttpResponse<IRefAnnee>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IRefAnnee[]) => (this.refannees = concatRes));
          }
        });

      this.centreService
        .query({ filter: 'previsionassainissementau-is-null' })
        .pipe(
          map((res: HttpResponse<ICentre[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: ICentre[]) => {
          if (!previsionAssainissementAu.centreId) {
            this.centres = resBody;
          } else {
            this.centreService
              .find(previsionAssainissementAu.centreId)
              .pipe(
                map((subRes: HttpResponse<ICentre>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: ICentre[]) => (this.centres = concatRes));
          }
        });
    });
  }

  updateForm(previsionAssainissementAu: IPrevisionAssainissementAu): void {
    this.editForm.patchValue({
      id: previsionAssainissementAu.id,
      nbLatrine: previsionAssainissementAu.nbLatrine,
      nbPuisard: previsionAssainissementAu.nbPuisard,
      nbPublic: previsionAssainissementAu.nbPublic,
      nbScolaire: previsionAssainissementAu.nbScolaire,
      centreDeSante: previsionAssainissementAu.centreDeSante,
      population: previsionAssainissementAu.population,
      refanneeId: previsionAssainissementAu.refanneeId,
      centreId: previsionAssainissementAu.centreId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const previsionAssainissementAu = this.createFromForm();
    if (previsionAssainissementAu.id !== undefined) {
      this.subscribeToSaveResponse(this.previsionAssainissementAuService.update(previsionAssainissementAu));
    } else {
      this.subscribeToSaveResponse(this.previsionAssainissementAuService.create(previsionAssainissementAu));
    }
  }

  private createFromForm(): IPrevisionAssainissementAu {
    return {
      ...new PrevisionAssainissementAu(),
      id: this.editForm.get(['id'])!.value,
      nbLatrine: this.editForm.get(['nbLatrine'])!.value,
      nbPuisard: this.editForm.get(['nbPuisard'])!.value,
      nbPublic: this.editForm.get(['nbPublic'])!.value,
      nbScolaire: this.editForm.get(['nbScolaire'])!.value,
      centreDeSante: this.editForm.get(['centreDeSante'])!.value,
      population: this.editForm.get(['population'])!.value,
      refanneeId: this.editForm.get(['refanneeId'])!.value,
      centreId: this.editForm.get(['centreId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrevisionAssainissementAu>>): void {
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
