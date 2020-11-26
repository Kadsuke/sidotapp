import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TypeOuvrageUpdateComponent } from 'app/entities/type-ouvrage/type-ouvrage-update.component';
import { TypeOuvrageService } from 'app/entities/type-ouvrage/type-ouvrage.service';
import { TypeOuvrage } from 'app/shared/model/type-ouvrage.model';

describe('Component Tests', () => {
  describe('TypeOuvrage Management Update Component', () => {
    let comp: TypeOuvrageUpdateComponent;
    let fixture: ComponentFixture<TypeOuvrageUpdateComponent>;
    let service: TypeOuvrageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TypeOuvrageUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeOuvrageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeOuvrageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeOuvrageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeOuvrage(123);
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
        const entity = new TypeOuvrage();
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
