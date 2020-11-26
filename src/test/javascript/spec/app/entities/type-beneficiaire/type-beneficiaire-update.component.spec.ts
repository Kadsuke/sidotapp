import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TypeBeneficiaireUpdateComponent } from 'app/entities/type-beneficiaire/type-beneficiaire-update.component';
import { TypeBeneficiaireService } from 'app/entities/type-beneficiaire/type-beneficiaire.service';
import { TypeBeneficiaire } from 'app/shared/model/type-beneficiaire.model';

describe('Component Tests', () => {
  describe('TypeBeneficiaire Management Update Component', () => {
    let comp: TypeBeneficiaireUpdateComponent;
    let fixture: ComponentFixture<TypeBeneficiaireUpdateComponent>;
    let service: TypeBeneficiaireService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TypeBeneficiaireUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeBeneficiaireUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeBeneficiaireUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeBeneficiaireService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeBeneficiaire(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeBeneficiaire();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
