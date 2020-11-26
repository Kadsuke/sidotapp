import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { NatureOuvrageDetailComponent } from 'app/entities/nature-ouvrage/nature-ouvrage-detail.component';
import { NatureOuvrage } from 'app/shared/model/nature-ouvrage.model';

describe('Component Tests', () => {
  describe('NatureOuvrage Management Detail Component', () => {
    let comp: NatureOuvrageDetailComponent;
    let fixture: ComponentFixture<NatureOuvrageDetailComponent>;
    const route = ({ data: of({ natureOuvrage: new NatureOuvrage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [NatureOuvrageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(NatureOuvrageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(NatureOuvrageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load natureOuvrage on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.natureOuvrage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
