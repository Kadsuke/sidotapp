import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { TypeOuvrageDetailComponent } from 'app/entities/type-ouvrage/type-ouvrage-detail.component';
import { TypeOuvrage } from 'app/shared/model/type-ouvrage.model';

describe('Component Tests', () => {
  describe('TypeOuvrage Management Detail Component', () => {
    let comp: TypeOuvrageDetailComponent;
    let fixture: ComponentFixture<TypeOuvrageDetailComponent>;
    const route = ({ data: of({ typeOuvrage: new TypeOuvrage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [TypeOuvrageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TypeOuvrageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TypeOuvrageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load typeOuvrage on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.typeOuvrage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
