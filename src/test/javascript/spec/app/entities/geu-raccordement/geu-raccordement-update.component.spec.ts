import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuRaccordementUpdateComponent } from 'app/entities/geu-raccordement/geu-raccordement-update.component';
import { GeuRaccordementService } from 'app/entities/geu-raccordement/geu-raccordement.service';
import { GeuRaccordement } from 'app/shared/model/geu-raccordement.model';

describe('Component Tests', () => {
  describe('GeuRaccordement Management Update Component', () => {
    let comp: GeuRaccordementUpdateComponent;
    let fixture: ComponentFixture<GeuRaccordementUpdateComponent>;
    let service: GeuRaccordementService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuRaccordementUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(GeuRaccordementUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(GeuRaccordementUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GeuRaccordementService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new GeuRaccordement(123);
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
        const entity = new GeuRaccordement();
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
