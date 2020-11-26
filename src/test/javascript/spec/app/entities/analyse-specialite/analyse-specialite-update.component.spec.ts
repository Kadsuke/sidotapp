import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { AnalyseSpecialiteUpdateComponent } from 'app/entities/analyse-specialite/analyse-specialite-update.component';
import { AnalyseSpecialiteService } from 'app/entities/analyse-specialite/analyse-specialite.service';
import { AnalyseSpecialite } from 'app/shared/model/analyse-specialite.model';

describe('Component Tests', () => {
  describe('AnalyseSpecialite Management Update Component', () => {
    let comp: AnalyseSpecialiteUpdateComponent;
    let fixture: ComponentFixture<AnalyseSpecialiteUpdateComponent>;
    let service: AnalyseSpecialiteService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [AnalyseSpecialiteUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AnalyseSpecialiteUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AnalyseSpecialiteUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AnalyseSpecialiteService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AnalyseSpecialite(123);
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
        const entity = new AnalyseSpecialite();
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
