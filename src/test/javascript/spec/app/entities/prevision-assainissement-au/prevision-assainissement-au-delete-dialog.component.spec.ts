import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { SidotTestModule } from '../../../test.module';
import { MockEventManager } from '../../../helpers/mock-event-manager.service';
import { MockActiveModal } from '../../../helpers/mock-active-modal.service';
import { PrevisionAssainissementAuDeleteDialogComponent } from 'app/entities/prevision-assainissement-au/prevision-assainissement-au-delete-dialog.component';
import { PrevisionAssainissementAuService } from 'app/entities/prevision-assainissement-au/prevision-assainissement-au.service';

describe('Component Tests', () => {
  describe('PrevisionAssainissementAu Management Delete Component', () => {
    let comp: PrevisionAssainissementAuDeleteDialogComponent;
    let fixture: ComponentFixture<PrevisionAssainissementAuDeleteDialogComponent>;
    let service: PrevisionAssainissementAuService;
    let mockEventManager: MockEventManager;
    let mockActiveModal: MockActiveModal;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [PrevisionAssainissementAuDeleteDialogComponent],
      })
        .overrideTemplate(PrevisionAssainissementAuDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PrevisionAssainissementAuDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrevisionAssainissementAuService);
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
