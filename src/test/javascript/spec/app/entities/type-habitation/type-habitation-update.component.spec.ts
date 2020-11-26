import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TypeHabitationUpdateComponent } from 'app/entities/type-habitation/type-habitation-update.component';
import { TypeHabitationService } from 'app/entities/type-habitation/type-habitation.service';
import { TypeHabitation } from 'app/shared/model/type-habitation.model';

describe('Component Tests', () => {
  describe('TypeHabitation Management Update Component', () => {
    let comp: TypeHabitationUpdateComponent;
    let fixture: ComponentFixture<TypeHabitationUpdateComponent>;
    let service: TypeHabitationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TypeHabitationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeHabitationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeHabitationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeHabitationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeHabitation(123);
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
        const entity = new TypeHabitation();
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
