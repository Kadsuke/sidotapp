import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IGeuAaOuvrage, GeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';
import { GeuAaOuvrageService } from './geu-aa-ouvrage.service';
import { IGeoParcelle } from 'app/shared/model/geo-parcelle.model';
import { GeoParcelleService } from 'app/entities/geo-parcelle/geo-parcelle.service';
import { INatureOuvrage } from 'app/shared/model/nature-ouvrage.model';
import { NatureOuvrageService } from 'app/entities/nature-ouvrage/nature-ouvrage.service';
import { ITypeHabitation } from 'app/shared/model/type-habitation.model';
import { TypeHabitationService } from 'app/entities/type-habitation/type-habitation.service';
import { ISourceApprovEp } from 'app/shared/model/source-approv-ep.model';
import { SourceApprovEpService } from 'app/entities/source-approv-ep/source-approv-ep.service';
import { IModeEvacuationEauUsee } from 'app/shared/model/mode-evacuation-eau-usee.model';
import { ModeEvacuationEauUseeService } from 'app/entities/mode-evacuation-eau-usee/mode-evacuation-eau-usee.service';
import { IModeEvacExcreta } from 'app/shared/model/mode-evac-excreta.model';
import { ModeEvacExcretaService } from 'app/entities/mode-evac-excreta/mode-evac-excreta.service';
import { IMacon } from 'app/shared/model/macon.model';
import { MaconService } from 'app/entities/macon/macon.service';
import { IPrefabricant } from 'app/shared/model/prefabricant.model';
import { PrefabricantService } from 'app/entities/prefabricant/prefabricant.service';

type SelectableEntity =
  | IGeoParcelle
  | INatureOuvrage
  | ITypeHabitation
  | ISourceApprovEp
  | IModeEvacuationEauUsee
  | IModeEvacExcreta
  | IMacon
  | IPrefabricant;

@Component({
  selector: 'jhi-geu-aa-ouvrage-update',
  templateUrl: './geu-aa-ouvrage-update.component.html',
})
export class GeuAaOuvrageUpdateComponent implements OnInit {
  isSaving = false;
  geoparcelles: IGeoParcelle[] = [];
  natureouvrages: INatureOuvrage[] = [];
  typehabitations: ITypeHabitation[] = [];
  sourceapproveps: ISourceApprovEp[] = [];
  modeevacuationeauusees: IModeEvacuationEauUsee[] = [];
  modeevacexcretas: IModeEvacExcreta[] = [];
  macons: IMacon[] = [];
  prefabricants: IPrefabricant[] = [];

  editForm = this.fb.group({
    id: [],
    refOuvrage: [],
    prjAppuis: [null, [Validators.required]],
    numCompteur: [null, [Validators.required]],
    nomBenef: [null, [Validators.required]],
    prenomBenef: [null, [Validators.required]],
    professionBenef: [null, [Validators.required]],
    nbUsagers: [null, [Validators.required]],
    codeUn: [null, [Validators.required]],
    dateRemiseDevis: [null, [Validators.required]],
    dateDebutTravaux: [null, [Validators.required]],
    dateFinTravaux: [null, [Validators.required]],
    numNomRue: [null, [Validators.required]],
    numNomPorte: [null, [Validators.required]],
    menage: [null, [Validators.required]],
    subvOnea: [null, [Validators.required]],
    subvProjet: [null, [Validators.required]],
    autreSubv: [null, [Validators.required]],
    refBonFourniture: [null, [Validators.required]],
    realisPorte: [null, [Validators.required]],
    realisToles: [null, [Validators.required]],
    animateur: [null, [Validators.required]],
    superviseur: [null, [Validators.required]],
    controleur: [null, [Validators.required]],
    geoparcelleId: [],
    natureouvrageId: [],
    typehabitationId: [],
    sourceapprovepId: [],
    modeevacuationeauuseeId: [],
    modeevacexcretaId: [],
    maconId: [],
    prefabricantId: [],
  });

  constructor(
    protected geuAaOuvrageService: GeuAaOuvrageService,
    protected geoParcelleService: GeoParcelleService,
    protected natureOuvrageService: NatureOuvrageService,
    protected typeHabitationService: TypeHabitationService,
    protected sourceApprovEpService: SourceApprovEpService,
    protected modeEvacuationEauUseeService: ModeEvacuationEauUseeService,
    protected modeEvacExcretaService: ModeEvacExcretaService,
    protected maconService: MaconService,
    protected prefabricantService: PrefabricantService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ geuAaOuvrage }) => {
      if (!geuAaOuvrage.id) {
        const today = moment().startOf('day');
        geuAaOuvrage.dateRemiseDevis = today;
        geuAaOuvrage.dateDebutTravaux = today;
        geuAaOuvrage.dateFinTravaux = today;
      }

      this.updateForm(geuAaOuvrage);

      this.geoParcelleService.query().subscribe((res: HttpResponse<IGeoParcelle[]>) => (this.geoparcelles = res.body || []));

      this.natureOuvrageService.query().subscribe((res: HttpResponse<INatureOuvrage[]>) => (this.natureouvrages = res.body || []));

      this.typeHabitationService.query().subscribe((res: HttpResponse<ITypeHabitation[]>) => (this.typehabitations = res.body || []));

      this.sourceApprovEpService.query().subscribe((res: HttpResponse<ISourceApprovEp[]>) => (this.sourceapproveps = res.body || []));

      this.modeEvacuationEauUseeService
        .query()
        .subscribe((res: HttpResponse<IModeEvacuationEauUsee[]>) => (this.modeevacuationeauusees = res.body || []));

      this.modeEvacExcretaService.query().subscribe((res: HttpResponse<IModeEvacExcreta[]>) => (this.modeevacexcretas = res.body || []));

      this.maconService.query().subscribe((res: HttpResponse<IMacon[]>) => (this.macons = res.body || []));

      this.prefabricantService.query().subscribe((res: HttpResponse<IPrefabricant[]>) => (this.prefabricants = res.body || []));
    });
  }

  updateForm(geuAaOuvrage: IGeuAaOuvrage): void {
    this.editForm.patchValue({
      id: geuAaOuvrage.id,
      refOuvrage: geuAaOuvrage.refOuvrage,
      prjAppuis: geuAaOuvrage.prjAppuis,
      numCompteur: geuAaOuvrage.numCompteur,
      nomBenef: geuAaOuvrage.nomBenef,
      prenomBenef: geuAaOuvrage.prenomBenef,
      professionBenef: geuAaOuvrage.professionBenef,
      nbUsagers: geuAaOuvrage.nbUsagers,
      codeUn: geuAaOuvrage.codeUn,
      dateRemiseDevis: geuAaOuvrage.dateRemiseDevis ? geuAaOuvrage.dateRemiseDevis.format(DATE_TIME_FORMAT) : null,
      dateDebutTravaux: geuAaOuvrage.dateDebutTravaux ? geuAaOuvrage.dateDebutTravaux.format(DATE_TIME_FORMAT) : null,
      dateFinTravaux: geuAaOuvrage.dateFinTravaux ? geuAaOuvrage.dateFinTravaux.format(DATE_TIME_FORMAT) : null,
      numNomRue: geuAaOuvrage.numNomRue,
      numNomPorte: geuAaOuvrage.numNomPorte,
      menage: geuAaOuvrage.menage,
      subvOnea: geuAaOuvrage.subvOnea,
      subvProjet: geuAaOuvrage.subvProjet,
      autreSubv: geuAaOuvrage.autreSubv,
      refBonFourniture: geuAaOuvrage.refBonFourniture,
      realisPorte: geuAaOuvrage.realisPorte,
      realisToles: geuAaOuvrage.realisToles,
      animateur: geuAaOuvrage.animateur,
      superviseur: geuAaOuvrage.superviseur,
      controleur: geuAaOuvrage.controleur,
      geoparcelleId: geuAaOuvrage.geoparcelleId,
      natureouvrageId: geuAaOuvrage.natureouvrageId,
      typehabitationId: geuAaOuvrage.typehabitationId,
      sourceapprovepId: geuAaOuvrage.sourceapprovepId,
      modeevacuationeauuseeId: geuAaOuvrage.modeevacuationeauuseeId,
      modeevacexcretaId: geuAaOuvrage.modeevacexcretaId,
      maconId: geuAaOuvrage.maconId,
      prefabricantId: geuAaOuvrage.prefabricantId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const geuAaOuvrage = this.createFromForm();
    if (geuAaOuvrage.id !== undefined) {
      this.subscribeToSaveResponse(this.geuAaOuvrageService.update(geuAaOuvrage));
    } else {
      this.subscribeToSaveResponse(this.geuAaOuvrageService.create(geuAaOuvrage));
    }
  }

  private createFromForm(): IGeuAaOuvrage {
    return {
      ...new GeuAaOuvrage(),
      id: this.editForm.get(['id'])!.value,
      refOuvrage: this.editForm.get(['refOuvrage'])!.value,
      prjAppuis: this.editForm.get(['prjAppuis'])!.value,
      numCompteur: this.editForm.get(['numCompteur'])!.value,
      nomBenef: this.editForm.get(['nomBenef'])!.value,
      prenomBenef: this.editForm.get(['prenomBenef'])!.value,
      professionBenef: this.editForm.get(['professionBenef'])!.value,
      nbUsagers: this.editForm.get(['nbUsagers'])!.value,
      codeUn: this.editForm.get(['codeUn'])!.value,
      dateRemiseDevis: this.editForm.get(['dateRemiseDevis'])!.value
        ? moment(this.editForm.get(['dateRemiseDevis'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dateDebutTravaux: this.editForm.get(['dateDebutTravaux'])!.value
        ? moment(this.editForm.get(['dateDebutTravaux'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dateFinTravaux: this.editForm.get(['dateFinTravaux'])!.value
        ? moment(this.editForm.get(['dateFinTravaux'])!.value, DATE_TIME_FORMAT)
        : undefined,
      numNomRue: this.editForm.get(['numNomRue'])!.value,
      numNomPorte: this.editForm.get(['numNomPorte'])!.value,
      menage: this.editForm.get(['menage'])!.value,
      subvOnea: this.editForm.get(['subvOnea'])!.value,
      subvProjet: this.editForm.get(['subvProjet'])!.value,
      autreSubv: this.editForm.get(['autreSubv'])!.value,
      refBonFourniture: this.editForm.get(['refBonFourniture'])!.value,
      realisPorte: this.editForm.get(['realisPorte'])!.value,
      realisToles: this.editForm.get(['realisToles'])!.value,
      animateur: this.editForm.get(['animateur'])!.value,
      superviseur: this.editForm.get(['superviseur'])!.value,
      controleur: this.editForm.get(['controleur'])!.value,
      geoparcelleId: this.editForm.get(['geoparcelleId'])!.value,
      natureouvrageId: this.editForm.get(['natureouvrageId'])!.value,
      typehabitationId: this.editForm.get(['typehabitationId'])!.value,
      sourceapprovepId: this.editForm.get(['sourceapprovepId'])!.value,
      modeevacuationeauuseeId: this.editForm.get(['modeevacuationeauuseeId'])!.value,
      modeevacexcretaId: this.editForm.get(['modeevacexcretaId'])!.value,
      maconId: this.editForm.get(['maconId'])!.value,
      prefabricantId: this.editForm.get(['prefabricantId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGeuAaOuvrage>>): void {
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
