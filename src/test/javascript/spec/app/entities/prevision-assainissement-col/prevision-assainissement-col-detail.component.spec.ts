import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { PrevisionAssainissementColDetailComponent } from 'app/entities/prevision-assainissement-col/prevision-assainissement-col-detail.component';
import { PrevisionAssainissementCol } from 'app/shared/model/prevision-assainissement-col.model';

describe('Component Tests', () => {
  describe('PrevisionAssainissementCol Management Detail Component', () => {
    let comp: PrevisionAssainissementColDetailComponent;
    let fixture: ComponentFixture<PrevisionAssainissementColDetailComponent>;
    const route = ({ data: of({ previsionAssainissementCol: new PrevisionAssainissementCol(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [PrevisionAssainissementColDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PrevisionAssainissementColDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PrevisionAssainissementColDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load previsionAssainissementCol on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.previsionAssainissementCol).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
