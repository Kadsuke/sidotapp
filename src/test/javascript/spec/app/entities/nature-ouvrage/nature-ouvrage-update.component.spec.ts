import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { NatureOuvrageUpdateComponent } from 'app/entities/nature-ouvrage/nature-ouvrage-update.component';
import { NatureOuvrageService } from 'app/entities/nature-ouvrage/nature-ouvrage.service';
import { NatureOuvrage } from 'app/shared/model/nature-ouvrage.model';

describe('Component Tests', () => {
  describe('NatureOuvrage Management Update Component', () => {
    let comp: NatureOuvrageUpdateComponent;
    let fixture: ComponentFixture<NatureOuvrageUpdateComponent>;
    let service: NatureOuvrageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [NatureOuvrageUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(NatureOuvrageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(NatureOuvrageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(NatureOuvrageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new NatureOuvrage(123);
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
        const entity = new NatureOuvrage();
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
