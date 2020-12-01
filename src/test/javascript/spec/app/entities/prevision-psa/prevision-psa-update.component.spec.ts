import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { PrevisionPsaUpdateComponent } from 'app/entities/prevision-psa/prevision-psa-update.component';
import { PrevisionPsaService } from 'app/entities/prevision-psa/prevision-psa.service';
import { PrevisionPsa } from 'app/shared/model/prevision-psa.model';

describe('Component Tests', () => {
  describe('PrevisionPsa Management Update Component', () => {
    let comp: PrevisionPsaUpdateComponent;
    let fixture: ComponentFixture<PrevisionPsaUpdateComponent>;
    let service: PrevisionPsaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [PrevisionPsaUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PrevisionPsaUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrevisionPsaUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrevisionPsaService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PrevisionPsa(123);
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
        const entity = new PrevisionPsa();
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
