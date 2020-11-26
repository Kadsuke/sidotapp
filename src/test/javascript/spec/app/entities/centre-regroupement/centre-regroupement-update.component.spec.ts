import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { CentreRegroupementUpdateComponent } from 'app/entities/centre-regroupement/centre-regroupement-update.component';
import { CentreRegroupementService } from 'app/entities/centre-regroupement/centre-regroupement.service';
import { CentreRegroupement } from 'app/shared/model/centre-regroupement.model';

describe('Component Tests', () => {
  describe('CentreRegroupement Management Update Component', () => {
    let comp: CentreRegroupementUpdateComponent;
    let fixture: ComponentFixture<CentreRegroupementUpdateComponent>;
    let service: CentreRegroupementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [CentreRegroupementUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CentreRegroupementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CentreRegroupementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CentreRegroupementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CentreRegroupement(123);
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
        const entity = new CentreRegroupement();
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
