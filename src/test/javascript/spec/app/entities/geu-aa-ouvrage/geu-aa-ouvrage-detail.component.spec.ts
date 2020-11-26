import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { GeuAaOuvrageDetailComponent } from 'app/entities/geu-aa-ouvrage/geu-aa-ouvrage-detail.component';
import { GeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';

describe('Component Tests', () => {
  describe('GeuAaOuvrage Management Detail Component', () => {
    let comp: GeuAaOuvrageDetailComponent;
    let fixture: ComponentFixture<GeuAaOuvrageDetailComponent>;
    const route = ({ data: of({ geuAaOuvrage: new GeuAaOuvrage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [GeuAaOuvrageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(GeuAaOuvrageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GeuAaOuvrageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load geuAaOuvrage on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.geuAaOuvrage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
