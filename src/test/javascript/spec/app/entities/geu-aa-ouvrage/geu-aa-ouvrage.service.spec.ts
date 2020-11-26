import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { GeuAaOuvrageService } from 'app/entities/geu-aa-ouvrage/geu-aa-ouvrage.service';
import { IGeuAaOuvrage, GeuAaOuvrage } from 'app/shared/model/geu-aa-ouvrage.model';

describe('Service Tests', () => {
  describe('GeuAaOuvrage Service', () => {
    let injector: TestBed;
    let service: GeuAaOuvrageService;
    let httpMock: HttpTestingController;
    let elemDefault: IGeuAaOuvrage;
    let expectedResult: IGeuAaOuvrage | IGeuAaOuvrage[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(GeuAaOuvrageService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new GeuAaOuvrage(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        currentDate,
        currentDate,
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        0,
        0,
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateRemiseDevis: currentDate.format(DATE_TIME_FORMAT),
            dateDebutTravaux: currentDate.format(DATE_TIME_FORMAT),
            dateFinTravaux: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a GeuAaOuvrage', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateRemiseDevis: currentDate.format(DATE_TIME_FORMAT),
            dateDebutTravaux: currentDate.format(DATE_TIME_FORMAT),
            dateFinTravaux: currentDate.format(DATE_TIME_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateRemiseDevis: currentDate,
            dateDebutTravaux: currentDate,
            dateFinTravaux: currentDate,
          },
          returnedFromService
        );

        service.create(new GeuAaOuvrage()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a GeuAaOuvrage', () => {
        const returnedFromService = Object.assign(
          {
            refOuvrage: 'BBBBBB',
            prjAppuis: 'BBBBBB',
            numCompteur: 'BBBBBB',
            nomBenef: 'BBBBBB',
            prenomBenef: 'BBBBBB',
            professionBenef: 'BBBBBB',
            nbUsagers: 1,
            codeUn: 'BBBBBB',
            dateRemiseDevis: currentDate.format(DATE_TIME_FORMAT),
            dateDebutTravaux: currentDate.format(DATE_TIME_FORMAT),
            dateFinTravaux: currentDate.format(DATE_TIME_FORMAT),
            numNomRue: 'BBBBBB',
            numNomPorte: 'BBBBBB',
            menage: 'BBBBBB',
            subvOnea: 1,
            subvProjet: 1,
            autreSubv: 1,
            refBonFourniture: 'BBBBBB',
            realisPorte: 1,
            realisToles: 1,
            animateur: 'BBBBBB',
            superviseur: 'BBBBBB',
            controleur: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateRemiseDevis: currentDate,
            dateDebutTravaux: currentDate,
            dateFinTravaux: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of GeuAaOuvrage', () => {
        const returnedFromService = Object.assign(
          {
            refOuvrage: 'BBBBBB',
            prjAppuis: 'BBBBBB',
            numCompteur: 'BBBBBB',
            nomBenef: 'BBBBBB',
            prenomBenef: 'BBBBBB',
            professionBenef: 'BBBBBB',
            nbUsagers: 1,
            codeUn: 'BBBBBB',
            dateRemiseDevis: currentDate.format(DATE_TIME_FORMAT),
            dateDebutTravaux: currentDate.format(DATE_TIME_FORMAT),
            dateFinTravaux: currentDate.format(DATE_TIME_FORMAT),
            numNomRue: 'BBBBBB',
            numNomPorte: 'BBBBBB',
            menage: 'BBBBBB',
            subvOnea: 1,
            subvProjet: 1,
            autreSubv: 1,
            refBonFourniture: 'BBBBBB',
            realisPorte: 1,
            realisToles: 1,
            animateur: 'BBBBBB',
            superviseur: 'BBBBBB',
            controleur: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateRemiseDevis: currentDate,
            dateDebutTravaux: currentDate,
            dateFinTravaux: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a GeuAaOuvrage', () => {
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
