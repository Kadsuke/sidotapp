import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuPrevisionSTEPUpdateComponent } from 'app/entities/geu-prevision-step/geu-prevision-step-update.component';
import { GeuPrevisionSTEPService } from 'app/entities/geu-prevision-step/geu-prevision-step.service';
import { GeuPrevisionSTEP } from 'app/shared/model/geu-prevision-step.model';

describe('Component Tests', () => {
  describe('GeuPrevisionSTEP Management Update Component', () => {
    let comp: GeuPrevisionSTEPUpdateComponent;
    let fixture: ComponentFixture<GeuPrevisionSTEPUpdateComponent>;
    let service: GeuPrevisionSTEPService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuPrevisionSTEPUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeuPrevisionSTEPUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeuPrevisionSTEPUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeuPrevisionSTEPService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeuPrevisionSTEP(123);
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
        const entity = new GeuPrevisionSTEP();
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
