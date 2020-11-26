import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITypeBeneficiaire } from 'app/shared/model/type-beneficiaire.model';

@Component({
  selector: 'jhi-type-beneficiaire-detail',
  templateUrl: './type-beneficiaire-detail.component.html',
})
export class TypeBeneficiaireDetailComponent implements OnInit {
  typeBeneficiaire: ITypeBeneficiaire | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ typeBeneficiaire }) => (this.typeBeneficiaire = typeBeneficiaire));
  }

  previousState(): void {
    window.history.back();
  }
}
