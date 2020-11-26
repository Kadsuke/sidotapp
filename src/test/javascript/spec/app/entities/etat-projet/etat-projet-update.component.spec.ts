import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { EtatProjetUpdateComponent } from 'app/entities/etat-projet/etat-projet-update.component';
import { EtatProjetService } from 'app/entities/etat-projet/etat-projet.service';
import { EtatProjet } from 'app/shared/model/etat-projet.model';

describe('Component Tests', () => {
  describe('EtatProjet Management Update Component', () => {
    let comp: EtatProjetUpdateComponent;
    let fixture: ComponentFixture<EtatProjetUpdateComponent>;
    let service: EtatProjetService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [EtatProjetUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EtatProjetUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EtatProjetUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EtatProjetService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EtatProjet(123);
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
        const entity = new EtatProjet();
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
