import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { EtatOuvrageUpdateComponent } from 'app/entities/etat-ouvrage/etat-ouvrage-update.component';
import { EtatOuvrageService } from 'app/entities/etat-ouvrage/etat-ouvrage.service';
import { EtatOuvrage } from 'app/shared/model/etat-ouvrage.model';

describe('Component Tests', () => {
  describe('EtatOuvrage Management Update Component', () => {
    let comp: EtatOuvrageUpdateComponent;
    let fixture: ComponentFixture<EtatOuvrageUpdateComponent>;
    let service: EtatOuvrageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [EtatOuvrageUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EtatOuvrageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EtatOuvrageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EtatOuvrageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EtatOuvrage(123);
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
        const entity = new EtatOuvrage();
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
