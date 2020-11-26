import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoTypeCommuneUpdateComponent } from 'app/entities/geo-type-commune/geo-type-commune-update.component';
import { GeoTypeCommuneService } from 'app/entities/geo-type-commune/geo-type-commune.service';
import { GeoTypeCommune } from 'app/shared/model/geo-type-commune.model';

describe('Component Tests', () => {
  describe('GeoTypeCommune Management Update Component', () => {
    let comp: GeoTypeCommuneUpdateComponent;
    let fixture: ComponentFixture<GeoTypeCommuneUpdateComponent>;
    let service: GeoTypeCommuneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoTypeCommuneUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeoTypeCommuneUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeoTypeCommuneUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeoTypeCommuneService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeoTypeCommune(123);
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
        const entity = new GeoTypeCommune();
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
