import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { CaracteristiqueOuvrageService } from 'app/entities/caracteristique-ouvrage/caracteristique-ouvrage.service';
import { ICaracteristiqueOuvrage, CaracteristiqueOuvrage } from 'app/shared/model/caracteristique-ouvrage.model';

describe('Service Tests', () => {
  describe('CaracteristiqueOuvrage Service', () => {
    let injector: TestBed;
    let service: CaracteristiqueOuvrageService;
    let httpMock: HttpTestingController;
    let elemDefault: ICaracteristiqueOuvrage;
    let expectedResult: ICaracteristiqueOuvrage | ICaracteristiqueOuvrage[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CaracteristiqueOuvrageService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new CaracteristiqueOuvrage(0, 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a CaracteristiqueOuvrage', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new CaracteristiqueOuvrage()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a CaracteristiqueOuvrage', () => {
        const returnedFromService = Object.assign(
          {
            libelle: 'BBBBBB',
            unite: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of CaracteristiqueOuvrage', () => {
        const returnedFromService = Object.assign(
          {
            libelle: 'BBBBBB',
            unite: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a CaracteristiqueOuvrage', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
