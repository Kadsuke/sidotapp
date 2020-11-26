import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuPrevisionSTBVUpdateComponent } from 'app/entities/geu-prevision-stbv/geu-prevision-stbv-update.component';
import { GeuPrevisionSTBVService } from 'app/entities/geu-prevision-stbv/geu-prevision-stbv.service';
import { GeuPrevisionSTBV } from 'app/shared/model/geu-prevision-stbv.model';

describe('Component Tests', () => {
  describe('GeuPrevisionSTBV Management Update Component', () => {
    let comp: GeuPrevisionSTBVUpdateComponent;
    let fixture: ComponentFixture<GeuPrevisionSTBVUpdateComponent>;
    let service: GeuPrevisionSTBVService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuPrevisionSTBVUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeuPrevisionSTBVUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeuPrevisionSTBVUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeuPrevisionSTBVService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeuPrevisionSTBV(123);
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
        const entity = new GeuPrevisionSTBV();
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
