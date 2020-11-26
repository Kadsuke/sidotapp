import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoLotUpdateComponent } from 'app/entities/geo-lot/geo-lot-update.component';
import { GeoLotService } from 'app/entities/geo-lot/geo-lot.service';
import { GeoLot } from 'app/shared/model/geo-lot.model';

describe('Component Tests', () => {
  describe('GeoLot Management Update Component', () => {
    let comp: GeoLotUpdateComponent;
    let fixture: ComponentFixture<GeoLotUpdateComponent>;
    let service: GeoLotService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoLotUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeoLotUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeoLotUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeoLotService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeoLot(123);
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
        const entity = new GeoLot();
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
