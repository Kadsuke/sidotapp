import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { CaracteristiqueOuvrageUpdateComponent } from 'app/entities/caracteristique-ouvrage/caracteristique-ouvrage-update.component';
import { CaracteristiqueOuvrageService } from 'app/entities/caracteristique-ouvrage/caracteristique-ouvrage.service';
import { CaracteristiqueOuvrage } from 'app/shared/model/caracteristique-ouvrage.model';

describe('Component Tests', () => {
  describe('CaracteristiqueOuvrage Management Update Component', () => {
    let comp: CaracteristiqueOuvrageUpdateComponent;
    let fixture: ComponentFixture<CaracteristiqueOuvrageUpdateComponent>;
    let service: CaracteristiqueOuvrageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [CaracteristiqueOuvrageUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(CaracteristiqueOuvrageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CaracteristiqueOuvrageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CaracteristiqueOuvrageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new CaracteristiqueOuvrage(123);
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
        const entity = new CaracteristiqueOuvrage();
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
