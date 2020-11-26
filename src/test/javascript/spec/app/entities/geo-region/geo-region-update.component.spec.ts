import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoRegionUpdateComponent } from 'app/entities/geo-region/geo-region-update.component';
import { GeoRegionService } from 'app/entities/geo-region/geo-region.service';
import { GeoRegion } from 'app/shared/model/geo-region.model';

describe('Component Tests', () => {
  describe('GeoRegion Management Update Component', () => {
    let comp: GeoRegionUpdateComponent;
    let fixture: ComponentFixture<GeoRegionUpdateComponent>;
    let service: GeoRegionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoRegionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeoRegionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeoRegionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeoRegionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeoRegion(123);
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
        const entity = new GeoRegion();
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
