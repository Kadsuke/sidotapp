import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuPSAUpdateComponent } from 'app/entities/geu-psa/geu-psa-update.component';
import { GeuPSAService } from 'app/entities/geu-psa/geu-psa.service';
import { GeuPSA } from 'app/shared/model/geu-psa.model';

describe('Component Tests', () => {
  describe('GeuPSA Management Update Component', () => {
    let comp: GeuPSAUpdateComponent;
    let fixture: ComponentFixture<GeuPSAUpdateComponent>;
    let service: GeuPSAService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuPSAUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeuPSAUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeuPSAUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeuPSAService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeuPSA(123);
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
        const entity = new GeuPSA();
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
