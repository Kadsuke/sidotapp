import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TypeAnalyseDetailComponent } from 'app/entities/type-analyse/type-analyse-detail.component';
import { TypeAnalyse } from 'app/shared/model/type-analyse.model';

describe('Component Tests', () => {
  describe('TypeAnalyse Management Detail Component', () => {
    let comp: TypeAnalyseDetailComponent;
    let fixture: ComponentFixture<TypeAnalyseDetailComponent>;
    const route = ({ data: of({ typeAnalyse: new TypeAnalyse(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TypeAnalyseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypeAnalyseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeAnalyseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeAnalyse on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeAnalyse).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
