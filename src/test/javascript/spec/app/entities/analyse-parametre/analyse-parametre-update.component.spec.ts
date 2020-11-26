import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { AnalyseParametreUpdateComponent } from 'app/entities/analyse-parametre/analyse-parametre-update.component';
import { AnalyseParametreService } from 'app/entities/analyse-parametre/analyse-parametre.service';
import { AnalyseParametre } from 'app/shared/model/analyse-parametre.model';

describe('Component Tests', () => {
  describe('AnalyseParametre Management Update Component', () => {
    let comp: AnalyseParametreUpdateComponent;
    let fixture: ComponentFixture<AnalyseParametreUpdateComponent>;
    let service: AnalyseParametreService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [AnalyseParametreUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(AnalyseParametreUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(AnalyseParametreUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(AnalyseParametreService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new AnalyseParametre(123);
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
        const entity = new AnalyseParametre();
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
