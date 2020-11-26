import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeoLocaliteComponentsPage, GeoLocaliteDeleteDialog, GeoLocaliteUpdatePage } from './geo-localite.page-object';

const expect = chai.expect;

describe('GeoLocalite e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geoLocaliteComponentsPage: GeoLocaliteComponentsPage;
  let geoLocaliteUpdatePage: GeoLocaliteUpdatePage;
  let geoLocaliteDeleteDialog: GeoLocaliteDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeoLocalites', async () => {
    await navBarPage.goToEntity('geo-localite');
    geoLocaliteComponentsPage = new GeoLocaliteComponentsPage();
    await browser.wait(ec.visibilityOf(geoLocaliteComponentsPage.title), 5000);
    expect(await geoLocaliteComponentsPage.getTitle()).to.eq('sidotApp.geoLocalite.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(geoLocaliteComponentsPage.entities), ec.visibilityOf(geoLocaliteComponentsPage.noResult)),
      1000
    );
  });

  it('should load create GeoLocalite page', async () => {
    await geoLocaliteComponentsPage.clickOnCreateButton();
    geoLocaliteUpdatePage = new GeoLocaliteUpdatePage();
    expect(await geoLocaliteUpdatePage.getPageTitle()).to.eq('sidotApp.geoLocalite.home.createOrEditLabel');
    await geoLocaliteUpdatePage.cancel();
  });

  it('should create and save GeoLocalites', async () => {
    const nbButtonsBeforeCreate = await geoLocaliteComponentsPage.countDeleteButtons();

    await geoLocaliteComponentsPage.clickOnCreateButton();

    await promise.all([geoLocaliteUpdatePage.setLibelleInput('libelle'), geoLocaliteUpdatePage.geocommuneSelectLastOption()]);

    expect(await geoLocaliteUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await geoLocaliteUpdatePage.save();
    expect(await geoLocaliteUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geoLocaliteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GeoLocalite', async () => {
    const nbButtonsBeforeDelete = await geoLocaliteComponentsPage.countDeleteButtons();
    await geoLocaliteComponentsPage.clickOnLastDeleteButton();

    geoLocaliteDeleteDialog = new GeoLocaliteDeleteDialog();
    expect(await geoLocaliteDeleteDialog.getDialogTitle()).to.eq('sidotApp.geoLocalite.delete.question');
    await geoLocaliteDeleteDialog.clickOnConfirmButton();

    expect(await geoLocaliteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
