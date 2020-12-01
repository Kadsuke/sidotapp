import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SidotTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { PrevisionAssainissementColDeleteDialogComponent } from 'app/entities/prevision-assainissement-col/prevision-assainissement-col-delete-dialog.component';
import { PrevisionAssainissementColService } from 'app/entities/prevision-assainissement-col/prevision-assainissement-col.service';

describe('Component Tests', () => {
  describe('PrevisionAssainissementCol Management Delete Component', () => {
    let comp: PrevisionAssainissementColDeleteDialogComponent;
    let fixture: ComponentFixture<PrevisionAssainissementColDeleteDialogComponent>;
    let service: PrevisionAssainissementColService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [PrevisionAssainissementColDeleteDialogComponent],
      })
        .overrideTemplate(PrevisionAssainissementColDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PrevisionAssainissementColDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrevisionAssainissementColService);
      mockEventManager = TestBed.get(JhiEventManager);
      mockActiveModal = TestBed.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.closeSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));

      it('Should not call delete service on clear', () => {
        // GIVEN
        spyOn(service, 'delete');

        // WHEN
        comp.cancel();

        // THEN
        expect(service.delete).not.toHaveBeenCalled();
        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
      });
    });
  });
});
