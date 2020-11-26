import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { DirectionRegionaleUpdateComponent } from 'app/entities/direction-regionale/direction-regionale-update.component';
import { DirectionRegionaleService } from 'app/entities/direction-regionale/direction-regionale.service';
import { DirectionRegionale } from 'app/shared/model/direction-regionale.model';

describe('Component Tests', () => {
  describe('DirectionRegionale Management Update Component', () => {
    let comp: DirectionRegionaleUpdateComponent;
    let fixture: ComponentFixture<DirectionRegionaleUpdateComponent>;
    let service: DirectionRegionaleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [DirectionRegionaleUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(DirectionRegionaleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(DirectionRegionaleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(DirectionRegionaleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new DirectionRegionale(123);
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
        const entity = new DirectionRegionale();
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
