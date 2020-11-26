import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { RefPeriodiciteDetailComponent } from 'app/entities/ref-periodicite/ref-periodicite-detail.component';
import { RefPeriodicite } from 'app/shared/model/ref-periodicite.model';

describe('Component Tests', () => {
  describe('RefPeriodicite Management Detail Component', () => {
    let comp: RefPeriodiciteDetailComponent;
    let fixture: ComponentFixture<RefPeriodiciteDetailComponent>;
    const route = ({ data: of({ refPeriodicite: new RefPeriodicite(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [RefPeriodiciteDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(RefPeriodiciteDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(RefPeriodiciteDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load refPeriodicite on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.refPeriodicite).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
