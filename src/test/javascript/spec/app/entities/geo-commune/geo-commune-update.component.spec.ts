import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoCommuneUpdateComponent } from 'app/entities/geo-commune/geo-commune-update.component';
import { GeoCommuneService } from 'app/entities/geo-commune/geo-commune.service';
import { GeoCommune } from 'app/shared/model/geo-commune.model';

describe('Component Tests', () => {
  describe('GeoCommune Management Update Component', () => {
    let comp: GeoCommuneUpdateComponent;
    let fixture: ComponentFixture<GeoCommuneUpdateComponent>;
    let service: GeoCommuneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoCommuneUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeoCommuneUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeoCommuneUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeoCommuneService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeoCommune(123);
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
        const entity = new GeoCommune();
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
