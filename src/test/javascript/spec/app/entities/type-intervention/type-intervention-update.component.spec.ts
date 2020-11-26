import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TypeInterventionUpdateComponent } from 'app/entities/type-intervention/type-intervention-update.component';
import { TypeInterventionService } from 'app/entities/type-intervention/type-intervention.service';
import { TypeIntervention } from 'app/shared/model/type-intervention.model';

describe('Component Tests', () => {
  describe('TypeIntervention Management Update Component', () => {
    let comp: TypeInterventionUpdateComponent;
    let fixture: ComponentFixture<TypeInterventionUpdateComponent>;
    let service: TypeInterventionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TypeInterventionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeInterventionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeInterventionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeInterventionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeIntervention(123);
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
        const entity = new TypeIntervention();
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
