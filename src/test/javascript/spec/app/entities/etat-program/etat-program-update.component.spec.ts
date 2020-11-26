import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { EtatProgramUpdateComponent } from 'app/entities/etat-program/etat-program-update.component';
import { EtatProgramService } from 'app/entities/etat-program/etat-program.service';
import { EtatProgram } from 'app/shared/model/etat-program.model';

describe('Component Tests', () => {
  describe('EtatProgram Management Update Component', () => {
    let comp: EtatProgramUpdateComponent;
    let fixture: ComponentFixture<EtatProgramUpdateComponent>;
    let service: EtatProgramService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [EtatProgramUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(EtatProgramUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(EtatProgramUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(EtatProgramService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new EtatProgram(123);
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
        const entity = new EtatProgram();
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
