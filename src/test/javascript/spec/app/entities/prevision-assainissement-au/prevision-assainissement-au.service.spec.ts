import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PrevisionAssainissementAuService } from 'app/entities/prevision-assainissement-au/prevision-assainissement-au.service';
import { IPrevisionAssainissementAu, PrevisionAssainissementAu } from 'app/shared/model/prevision-assainissement-au.model';

describe('Service Tests', () => {
  describe('PrevisionAssainissementAu Service', () => {
    let injector: TestBed;
    let service: PrevisionAssainissementAuService;
    let httpMock: HttpTestingController;
    let elemDefault: IPrevisionAssainissementAu;
    let expectedResult: IPrevisionAssainissementAu | IPrevisionAssainissementAu[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PrevisionAssainissementAuService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new PrevisionAssainissementAu(0, 0, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a PrevisionAssainissementAu', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new PrevisionAssainissementAu()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a PrevisionAssainissementAu', () => {
        const returnedFromService = Object.assign(
          {
            nbLatrine: 1,
            nbPuisard: 1,
            nbPublic: 1,
            nbScolaire: 1,
            centreDeSante: 1,
            population: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of PrevisionAssainissementAu', () => {
        const returnedFromService = Object.assign(
          {
            nbLatrine: 1,
            nbPuisard: 1,
            nbPublic: 1,
            nbScolaire: 1,
            centreDeSante: 1,
            population: 1,
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

      it('should delete a PrevisionAssainissementAu', () => {
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
