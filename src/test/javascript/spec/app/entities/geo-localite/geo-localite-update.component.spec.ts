import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoLocaliteUpdateComponent } from 'app/entities/geo-localite/geo-localite-update.component';
import { GeoLocaliteService } from 'app/entities/geo-localite/geo-localite.service';
import { GeoLocalite } from 'app/shared/model/geo-localite.model';

describe('Component Tests', () => {
  describe('GeoLocalite Management Update Component', () => {
    let comp: GeoLocaliteUpdateComponent;
    let fixture: ComponentFixture<GeoLocaliteUpdateComponent>;
    let service: GeoLocaliteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoLocaliteUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeoLocaliteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeoLocaliteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeoLocaliteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeoLocalite(123);
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
        const entity = new GeoLocalite();
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
