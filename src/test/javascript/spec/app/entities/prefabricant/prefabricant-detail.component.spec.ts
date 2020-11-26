import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SidotTestModule } from '../../../test.module';
import { PrefabricantDetailComponent } from 'app/entities/prefabricant/prefabricant-detail.component';
import { Prefabricant } from 'app/shared/model/prefabricant.model';

describe('Component Tests', () => {
  describe('Prefabricant Management Detail Component', () => {
    let comp: PrefabricantDetailComponent;
    let fixture: ComponentFixture<PrefabricantDetailComponent>;
    const route = ({ data: of({ prefabricant: new Prefabricant(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SidotTestModule],
        declarations: [PrefabricantDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(PrefabricantDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PrefabricantDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load prefabricant on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.prefabricant).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
