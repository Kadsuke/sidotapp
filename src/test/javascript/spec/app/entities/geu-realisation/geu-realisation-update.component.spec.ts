import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuRealisationUpdateComponent } from 'app/entities/geu-realisation/geu-realisation-update.component';
import { GeuRealisationService } from 'app/entities/geu-realisation/geu-realisation.service';
import { GeuRealisation } from 'app/shared/model/geu-realisation.model';

describe('Component Tests', () => {
  describe('GeuRealisation Management Update Component', () => {
    let comp: GeuRealisationUpdateComponent;
    let fixture: ComponentFixture<GeuRealisationUpdateComponent>;
    let service: GeuRealisationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuRealisationUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeuRealisationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeuRealisationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeuRealisationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeuRealisation(123);
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
        const entity = new GeuRealisation();
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
