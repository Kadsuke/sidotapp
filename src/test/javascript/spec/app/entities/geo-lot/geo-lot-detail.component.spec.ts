import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoLotDetailComponent } from 'app/entities/geo-lot/geo-lot-detail.component';
import { GeoLot } from 'app/shared/model/geo-lot.model';

describe('Component Tests', () => {
  describe('GeoLot Management Detail Component', () => {
    let comp: GeoLotDetailComponent;
    let fixture: ComponentFixture<GeoLotDetailComponent>;
    const route = ({ data: of({ geoLot: new GeoLot(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoLotDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeoLotDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeoLotDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geoLot on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geoLot).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
