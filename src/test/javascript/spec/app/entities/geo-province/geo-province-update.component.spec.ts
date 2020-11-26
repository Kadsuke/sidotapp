import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoProvinceUpdateComponent } from 'app/entities/geo-province/geo-province-update.component';
import { GeoProvinceService } from 'app/entities/geo-province/geo-province.service';
import { GeoProvince } from 'app/shared/model/geo-province.model';

describe('Component Tests', () => {
  describe('GeoProvince Management Update Component', () => {
    let comp: GeoProvinceUpdateComponent;
    let fixture: ComponentFixture<GeoProvinceUpdateComponent>;
    let service: GeoProvinceService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoProvinceUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeoProvinceUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeoProvinceUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeoProvinceService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeoProvince(123);
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
        const entity = new GeoProvince();
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
