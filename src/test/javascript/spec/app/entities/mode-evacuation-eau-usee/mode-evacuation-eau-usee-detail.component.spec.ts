import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { ModeEvacuationEauUseeDetailComponent } from 'app/entities/mode-evacuation-eau-usee/mode-evacuation-eau-usee-detail.component';
import { ModeEvacuationEauUsee } from 'app/shared/model/mode-evacuation-eau-usee.model';

describe('Component Tests', () => {
  describe('ModeEvacuationEauUsee Management Detail Component', () => {
    let comp: ModeEvacuationEauUseeDetailComponent;
    let fixture: ComponentFixture<ModeEvacuationEauUseeDetailComponent>;
    const route = ({ data: of({ modeEvacuationEauUsee: new ModeEvacuationEauUsee(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [ModeEvacuationEauUseeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ModeEvacuationEauUseeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ModeEvacuationEauUseeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load modeEvacuationEauUsee on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.modeEvacuationEauUsee).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
