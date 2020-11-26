import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IProduitChimique, ProduitChimique } from 'app/shared/model/produit-chimique.model';
import { ProduitChimiqueService } from './produit-chimique.service';

@Component({
  selector: 'jhi-produit-chimique-update',
  templateUrl: './produit-chimique-update.component.html',
})
export class ProduitChimiqueUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    libelle: [],
  });

  constructor(
    protected produitChimiqueService: ProduitChimiqueService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ produitChimique }) => {
      this.updateForm(produitChimique);
    });
  }

  updateForm(produitChimique: IProduitChimique): void {
    this.editForm.patchValue({
      id: produitChimique.id,
      libelle: produitChimique.libelle,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const produitChimique = this.createFromForm();
    if (produitChimique.id !== undefined) {
      this.subscribeToSaveResponse(this.produitChimiqueService.update(produitChimique));
    } else {
      this.subscribeToSaveResponse(this.produitChimiqueService.create(produitChimique));
    }
  }

  private createFromForm(): IProduitChimique {
    return {
      ...new ProduitChimique(),
      id: this.editForm.get(['id'])!.value,
      libelle: this.editForm.get(['libelle'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProduitChimique>>): void {
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
