import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { RefPeriodiciteUpdateComponent } from 'app/entities/ref-periodicite/ref-periodicite-update.component';
import { RefPeriodiciteService } from 'app/entities/ref-periodicite/ref-periodicite.service';
import { RefPeriodicite } from 'app/shared/model/ref-periodicite.model';

describe('Component Tests', () => {
  describe('RefPeriodicite Management Update Component', () => {
    let comp: RefPeriodiciteUpdateComponent;
    let fixture: ComponentFixture<RefPeriodiciteUpdateComponent>;
    let service: RefPeriodiciteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [RefPeriodiciteUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RefPeriodiciteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RefPeriodiciteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RefPeriodiciteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RefPeriodicite(123);
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
        const entity = new RefPeriodicite();
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
