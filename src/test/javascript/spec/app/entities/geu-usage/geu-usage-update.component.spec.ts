import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuUsageUpdateComponent } from 'app/entities/geu-usage/geu-usage-update.component';
import { GeuUsageService } from 'app/entities/geu-usage/geu-usage.service';
import { GeuUsage } from 'app/shared/model/geu-usage.model';

describe('Component Tests', () => {
  describe('GeuUsage Management Update Component', () => {
    let comp: GeuUsageUpdateComponent;
    let fixture: ComponentFixture<GeuUsageUpdateComponent>;
    let service: GeuUsageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuUsageUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeuUsageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeuUsageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeuUsageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeuUsage(123);
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
        const entity = new GeuUsage();
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
