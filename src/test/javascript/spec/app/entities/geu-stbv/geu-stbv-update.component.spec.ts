import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuSTBVUpdateComponent } from 'app/entities/geu-stbv/geu-stbv-update.component';
import { GeuSTBVService } from 'app/entities/geu-stbv/geu-stbv.service';
import { GeuSTBV } from 'app/shared/model/geu-stbv.model';

describe('Component Tests', () => {
  describe('GeuSTBV Management Update Component', () => {
    let comp: GeuSTBVUpdateComponent;
    let fixture: ComponentFixture<GeuSTBVUpdateComponent>;
    let service: GeuSTBVService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuSTBVUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeuSTBVUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeuSTBVUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeuSTBVService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeuSTBV(123);
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
        const entity = new GeuSTBV();
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
