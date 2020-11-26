import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoParcelleUpdateComponent } from 'app/entities/geo-parcelle/geo-parcelle-update.component';
import { GeoParcelleService } from 'app/entities/geo-parcelle/geo-parcelle.service';
import { GeoParcelle } from 'app/shared/model/geo-parcelle.model';

describe('Component Tests', () => {
  describe('GeoParcelle Management Update Component', () => {
    let comp: GeoParcelleUpdateComponent;
    let fixture: ComponentFixture<GeoParcelleUpdateComponent>;
    let service: GeoParcelleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoParcelleUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeoParcelleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeoParcelleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeoParcelleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeoParcelle(123);
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
        const entity = new GeoParcelle();
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
