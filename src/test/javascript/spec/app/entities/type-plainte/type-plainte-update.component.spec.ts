import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TypePlainteUpdateComponent } from 'app/entities/type-plainte/type-plainte-update.component';
import { TypePlainteService } from 'app/entities/type-plainte/type-plainte.service';
import { TypePlainte } from 'app/shared/model/type-plainte.model';

describe('Component Tests', () => {
  describe('TypePlainte Management Update Component', () => {
    let comp: TypePlainteUpdateComponent;
    let fixture: ComponentFixture<TypePlainteUpdateComponent>;
    let service: TypePlainteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TypePlainteUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypePlainteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypePlainteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypePlainteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypePlainte(123);
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
        const entity = new TypePlainte();
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
