import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TacheronsUpdateComponent } from 'app/entities/tacherons/tacherons-update.component';
import { TacheronsService } from 'app/entities/tacherons/tacherons.service';
import { Tacherons } from 'app/shared/model/tacherons.model';

describe('Component Tests', () => {
  describe('Tacherons Management Update Component', () => {
    let comp: TacheronsUpdateComponent;
    let fixture: ComponentFixture<TacheronsUpdateComponent>;
    let service: TacheronsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TacheronsUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TacheronsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TacheronsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TacheronsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Tacherons(123);
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
        const entity = new Tacherons();
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
