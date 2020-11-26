import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { SourceApprovEpDetailComponent } from 'app/entities/source-approv-ep/source-approv-ep-detail.component';
import { SourceApprovEp } from 'app/shared/model/source-approv-ep.model';

describe('Component Tests', () => {
  describe('SourceApprovEp Management Detail Component', () => {
    let comp: SourceApprovEpDetailComponent;
    let fixture: ComponentFixture<SourceApprovEpDetailComponent>;
    const route = ({ data: of({ sourceApprovEp: new SourceApprovEp(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [SourceApprovEpDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SourceApprovEpDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SourceApprovEpDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load sourceApprovEp on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.sourceApprovEp).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
