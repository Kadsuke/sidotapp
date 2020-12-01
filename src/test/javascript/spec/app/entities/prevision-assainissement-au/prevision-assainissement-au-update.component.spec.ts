import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { PrevisionAssainissementAuUpdateComponent } from 'app/entities/prevision-assainissement-au/prevision-assainissement-au-update.component';
import { PrevisionAssainissementAuService } from 'app/entities/prevision-assainissement-au/prevision-assainissement-au.service';
import { PrevisionAssainissementAu } from 'app/shared/model/prevision-assainissement-au.model';

describe('Component Tests', () => {
  describe('PrevisionAssainissementAu Management Update Component', () => {
    let comp: PrevisionAssainissementAuUpdateComponent;
    let fixture: ComponentFixture<PrevisionAssainissementAuUpdateComponent>;
    let service: PrevisionAssainissementAuService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [PrevisionAssainissementAuUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(PrevisionAssainissementAuUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrevisionAssainissementAuUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrevisionAssainissementAuService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new PrevisionAssainissementAu(123);
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
        const entity = new PrevisionAssainissementAu();
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
