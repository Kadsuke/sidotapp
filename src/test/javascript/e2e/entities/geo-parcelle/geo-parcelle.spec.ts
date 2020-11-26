import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeoParcelleComponentsPage, GeoParcelleDeleteDialog, GeoParcelleUpdatePage } from './geo-parcelle.page-object';

const expect = chai.expect;

describe('GeoParcelle e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geoParcelleComponentsPage: GeoParcelleComponentsPage;
  let geoParcelleUpdatePage: GeoParcelleUpdatePage;
  let geoParcelleDeleteDialog: GeoParcelleDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeoParcelles', async () => {
    await navBarPage.goToEntity('geo-parcelle');
    geoParcelleComponentsPage = new GeoParcelleComponentsPage();
    await browser.wait(ec.visibilityOf(geoParcelleComponentsPage.title), 5000);
    expect(await geoParcelleComponentsPage.getTitle()).to.eq('sidotApp.geoParcelle.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(geoParcelleComponentsPage.entities), ec.visibilityOf(geoParcelleComponentsPage.noResult)),
      1000
    );
  });

  it('should load create GeoParcelle page', async () => {
    await geoParcelleComponentsPage.clickOnCreateButton();
    geoParcelleUpdatePage = new GeoParcelleUpdatePage();
    expect(await geoParcelleUpdatePage.getPageTitle()).to.eq('sidotApp.geoParcelle.home.createOrEditLabel');
    await geoParcelleUpdatePage.cancel();
  });

  it('should create and save GeoParcelles', async () => {
    const nbButtonsBeforeCreate = await geoParcelleComponentsPage.countDeleteButtons();

    await geoParcelleComponentsPage.clickOnCreateButton();

    await promise.all([geoParcelleUpdatePage.setLibelleInput('libelle'), geoParcelleUpdatePage.geolotSelectLastOption()]);

    expect(await geoParcelleUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await geoParcelleUpdatePage.save();
    expect(await geoParcelleUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geoParcelleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GeoParcelle', async () => {
    const nbButtonsBeforeDelete = await geoParcelleComponentsPage.countDeleteButtons();
    await geoParcelleComponentsPage.clickOnLastDeleteButton();

    geoParcelleDeleteDialog = new GeoParcelleDeleteDialog();
    expect(await geoParcelleDeleteDialog.getDialogTitle()).to.eq('sidotApp.geoParcelle.delete.question');
    await geoParcelleDeleteDialog.clickOnConfirmButton();

    expect(await geoParcelleComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
