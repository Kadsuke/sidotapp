import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { RefSousDomaineUpdateComponent } from 'app/entities/ref-sous-domaine/ref-sous-domaine-update.component';
import { RefSousDomaineService } from 'app/entities/ref-sous-domaine/ref-sous-domaine.service';
import { RefSousDomaine } from 'app/shared/model/ref-sous-domaine.model';

describe('Component Tests', () => {
  describe('RefSousDomaine Management Update Component', () => {
    let comp: RefSousDomaineUpdateComponent;
    let fixture: ComponentFixture<RefSousDomaineUpdateComponent>;
    let service: RefSousDomaineService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [RefSousDomaineUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(RefSousDomaineUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RefSousDomaineUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RefSousDomaineService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new RefSousDomaine(123);
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
        const entity = new RefSousDomaine();
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
