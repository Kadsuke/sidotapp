import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeoSecteurComponentsPage, GeoSecteurDeleteDialog, GeoSecteurUpdatePage } from './geo-secteur.page-object';

const expect = chai.expect;

describe('GeoSecteur e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geoSecteurComponentsPage: GeoSecteurComponentsPage;
  let geoSecteurUpdatePage: GeoSecteurUpdatePage;
  let geoSecteurDeleteDialog: GeoSecteurDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeoSecteurs', async () => {
    await navBarPage.goToEntity('geo-secteur');
    geoSecteurComponentsPage = new GeoSecteurComponentsPage();
    await browser.wait(ec.visibilityOf(geoSecteurComponentsPage.title), 5000);
    expect(await geoSecteurComponentsPage.getTitle()).to.eq('sidotApp.geoSecteur.home.title');
    await browser.wait(ec.or(ec.visibilityOf(geoSecteurComponentsPage.entities), ec.visibilityOf(geoSecteurComponentsPage.noResult)), 1000);
  });

  it('should load create GeoSecteur page', async () => {
    await geoSecteurComponentsPage.clickOnCreateButton();
    geoSecteurUpdatePage = new GeoSecteurUpdatePage();
    expect(await geoSecteurUpdatePage.getPageTitle()).to.eq('sidotApp.geoSecteur.home.createOrEditLabel');
    await geoSecteurUpdatePage.cancel();
  });

  it('should create and save GeoSecteurs', async () => {
    const nbButtonsBeforeCreate = await geoSecteurComponentsPage.countDeleteButtons();

    await geoSecteurComponentsPage.clickOnCreateButton();

    await promise.all([geoSecteurUpdatePage.setLibelleInput('libelle'), geoSecteurUpdatePage.geolocaliteSelectLastOption()]);

    expect(await geoSecteurUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await geoSecteurUpdatePage.save();
    expect(await geoSecteurUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geoSecteurComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GeoSecteur', async () => {
    const nbButtonsBeforeDelete = await geoSecteurComponentsPage.countDeleteButtons();
    await geoSecteurComponentsPage.clickOnLastDeleteButton();

    geoSecteurDeleteDialog = new GeoSecteurDeleteDialog();
    expect(await geoSecteurDeleteDialog.getDialogTitle()).to.eq('sidotApp.geoSecteur.delete.question');
    await geoSecteurDeleteDialog.clickOnConfirmButton();

    expect(await geoSecteurComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
