import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { PrestataireUpdateComponent } from 'app/entities/prestataire/prestataire-update.component';
import { PrestataireService } from 'app/entities/prestataire/prestataire.service';
import { Prestataire } from 'app/shared/model/prestataire.model';

describe('Component Tests', () => {
  describe('Prestataire Management Update Component', () => {
    let comp: PrestataireUpdateComponent;
    let fixture: ComponentFixture<PrestataireUpdateComponent>;
    let service: PrestataireService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [PrestataireUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PrestataireUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrestataireUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrestataireService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Prestataire(123);
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
        const entity = new Prestataire();
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
