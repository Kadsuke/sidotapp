import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeoSecteurDetailComponent } from 'app/entities/geo-secteur/geo-secteur-detail.component';
import { GeoSecteur } from 'app/shared/model/geo-secteur.model';

describe('Component Tests', () => {
  describe('GeoSecteur Management Detail Component', () => {
    let comp: GeoSecteurDetailComponent;
    let fixture: ComponentFixture<GeoSecteurDetailComponent>;
    const route = ({ data: of({ geoSecteur: new GeoSecteur(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeoSecteurDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeoSecteurDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeoSecteurDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geoSecteur on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geoSecteur).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
