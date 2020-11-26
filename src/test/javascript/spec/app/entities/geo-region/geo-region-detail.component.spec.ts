import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoRegionDetailComponent } from 'app/entities/geo-region/geo-region-detail.component';
import { GeoRegion } from 'app/shared/model/geo-region.model';

describe('Component Tests', () => {
  describe('GeoRegion Management Detail Component', () => {
    let comp: GeoRegionDetailComponent;
    let fixture: ComponentFixture<GeoRegionDetailComponent>;
    const route = ({ data: of({ geoRegion: new GeoRegion(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoRegionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeoRegionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeoRegionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geoRegion on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geoRegion).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
