import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TypeAnalyseUpdateComponent } from 'app/entities/type-analyse/type-analyse-update.component';
import { TypeAnalyseService } from 'app/entities/type-analyse/type-analyse.service';
import { TypeAnalyse } from 'app/shared/model/type-analyse.model';

describe('Component Tests', () => {
  describe('TypeAnalyse Management Update Component', () => {
    let comp: TypeAnalyseUpdateComponent;
    let fixture: ComponentFixture<TypeAnalyseUpdateComponent>;
    let service: TypeAnalyseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TypeAnalyseUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(TypeAnalyseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TypeAnalyseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TypeAnalyseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TypeAnalyse(123);
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
        const entity = new TypeAnalyse();
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
