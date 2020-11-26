import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoSectionUpdateComponent } from 'app/entities/geo-section/geo-section-update.component';
import { GeoSectionService } from 'app/entities/geo-section/geo-section.service';
import { GeoSection } from 'app/shared/model/geo-section.model';

describe('Component Tests', () => {
  describe('GeoSection Management Update Component', () => {
    let comp: GeoSectionUpdateComponent;
    let fixture: ComponentFixture<GeoSectionUpdateComponent>;
    let service: GeoSectionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoSectionUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeoSectionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeoSectionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeoSectionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeoSection(123);
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
        const entity = new GeoSection();
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
