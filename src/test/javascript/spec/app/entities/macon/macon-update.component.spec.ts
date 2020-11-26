import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { MaconUpdateComponent } from 'app/entities/macon/macon-update.component';
import { MaconService } from 'app/entities/macon/macon.service';
import { Macon } from 'app/shared/model/macon.model';

describe('Component Tests', () => {
  describe('Macon Management Update Component', () => {
    let comp: MaconUpdateComponent;
    let fixture: ComponentFixture<MaconUpdateComponent>;
    let service: MaconService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [MaconUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(MaconUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MaconUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MaconService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Macon(123);
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
        const entity = new Macon();
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
