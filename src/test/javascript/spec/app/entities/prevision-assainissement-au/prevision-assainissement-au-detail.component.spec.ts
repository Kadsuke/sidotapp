import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { PrevisionAssainissementAuDetailComponent } from 'app/entities/prevision-assainissement-au/prevision-assainissement-au-detail.component';
import { PrevisionAssainissementAu } from 'app/shared/model/prevision-assainissement-au.model';

describe('Component Tests', () => {
  describe('PrevisionAssainissementAu Management Detail Component', () => {
    let comp: PrevisionAssainissementAuDetailComponent;
    let fixture: ComponentFixture<PrevisionAssainissementAuDetailComponent>;
    const route = ({ data: of({ previsionAssainissementAu: new PrevisionAssainissementAu(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [PrevisionAssainissementAuDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PrevisionAssainissementAuDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PrevisionAssainissementAuDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load previsionAssainissementAu on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.previsionAssainissementAu).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
