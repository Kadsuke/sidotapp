import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TypeBeneficiaireDetailComponent } from 'app/entities/type-beneficiaire/type-beneficiaire-detail.component';
import { TypeBeneficiaire } from 'app/shared/model/type-beneficiaire.model';

describe('Component Tests', () => {
  describe('TypeBeneficiaire Management Detail Component', () => {
    let comp: TypeBeneficiaireDetailComponent;
    let fixture: ComponentFixture<TypeBeneficiaireDetailComponent>;
    const route = ({ data: of({ typeBeneficiaire: new TypeBeneficiaire(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TypeBeneficiaireDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypeBeneficiaireDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeBeneficiaireDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeBeneficiaire on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeBeneficiaire).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
