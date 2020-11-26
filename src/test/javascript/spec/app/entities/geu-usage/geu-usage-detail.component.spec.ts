import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuUsageDetailComponent } from 'app/entities/geu-usage/geu-usage-detail.component';
import { GeuUsage } from 'app/shared/model/geu-usage.model';

describe('Component Tests', () => {
  describe('GeuUsage Management Detail Component', () => {
    let comp: GeuUsageDetailComponent;
    let fixture: ComponentFixture<GeuUsageDetailComponent>;
    const route = ({ data: of({ geuUsage: new GeuUsage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuUsageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeuUsageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeuUsageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geuUsage on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geuUsage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
