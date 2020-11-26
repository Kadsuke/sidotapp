import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { RefAnneeUpdateComponent } from 'app/entities/ref-annee/ref-annee-update.component';
import { RefAnneeService } from 'app/entities/ref-annee/ref-annee.service';
import { RefAnnee } from 'app/shared/model/ref-annee.model';

describe('Component Tests', () => {
  describe('RefAnnee Management Update Component', () => {
    let comp: RefAnneeUpdateComponent;
    let fixture: ComponentFixture<RefAnneeUpdateComponent>;
    let service: RefAnneeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [RefAnneeUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RefAnneeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RefAnneeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RefAnneeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RefAnnee(123);
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
        const entity = new RefAnnee();
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
