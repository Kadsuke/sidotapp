import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { RefMoisUpdateComponent } from 'app/entities/ref-mois/ref-mois-update.component';
import { RefMoisService } from 'app/entities/ref-mois/ref-mois.service';
import { RefMois } from 'app/shared/model/ref-mois.model';

describe('Component Tests', () => {
  describe('RefMois Management Update Component', () => {
    let comp: RefMoisUpdateComponent;
    let fixture: ComponentFixture<RefMoisUpdateComponent>;
    let service: RefMoisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [RefMoisUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RefMoisUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RefMoisUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RefMoisService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RefMois(123);
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
        const entity = new RefMois();
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
