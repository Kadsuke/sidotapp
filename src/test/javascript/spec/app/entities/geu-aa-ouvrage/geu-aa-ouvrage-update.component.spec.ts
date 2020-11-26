import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuAaOuvrageUpdateComponent } from 'app/entities/geu-aa-ouvrage/geu-aa-ouvrage-update.component';
import { GeuAaOuvrageService } from 'app/entities/geu-aa-ouvrage/geu-aa-ouvrage.service';
import { GeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';

describe('Component Tests', () => {
  describe('GeuAaOuvrage Management Update Component', () => {
    let comp: GeuAaOuvrageUpdateComponent;
    let fixture: ComponentFixture<GeuAaOuvrageUpdateComponent>;
    let service: GeuAaOuvrageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuAaOuvrageUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeuAaOuvrageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeuAaOuvrageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeuAaOuvrageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeuAaOuvrage(123);
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
        const entity = new GeuAaOuvrage();
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
