import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuSTEPUpdateComponent } from 'app/entities/geu-step/geu-step-update.component';
import { GeuSTEPService } from 'app/entities/geu-step/geu-step.service';
import { GeuSTEP } from 'app/shared/model/geu-step.model';

describe('Component Tests', () => {
  describe('GeuSTEP Management Update Component', () => {
    let comp: GeuSTEPUpdateComponent;
    let fixture: ComponentFixture<GeuSTEPUpdateComponent>;
    let service: GeuSTEPService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuSTEPUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeuSTEPUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeuSTEPUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeuSTEPService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeuSTEP(123);
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
        const entity = new GeuSTEP();
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
