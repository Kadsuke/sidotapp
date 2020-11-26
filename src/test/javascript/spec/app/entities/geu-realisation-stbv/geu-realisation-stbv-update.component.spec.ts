import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuRealisationSTBVUpdateComponent } from 'app/entities/geu-realisation-stbv/geu-realisation-stbv-update.component';
import { GeuRealisationSTBVService } from 'app/entities/geu-realisation-stbv/geu-realisation-stbv.service';
import { GeuRealisationSTBV } from 'app/shared/model/geu-realisation-stbv.model';

describe('Component Tests', () => {
  describe('GeuRealisationSTBV Management Update Component', () => {
    let comp: GeuRealisationSTBVUpdateComponent;
    let fixture: ComponentFixture<GeuRealisationSTBVUpdateComponent>;
    let service: GeuRealisationSTBVService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuRealisationSTBVUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeuRealisationSTBVUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeuRealisationSTBVUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeuRealisationSTBVService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeuRealisationSTBV(123);
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
        const entity = new GeuRealisationSTBV();
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
