import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeoTypeCommuneComponentsPage, GeoTypeCommuneDeleteDialog, GeoTypeCommuneUpdatePage } from './geo-type-commune.page-object';

const expect = chai.expect;

describe('GeoTypeCommune e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geoTypeCommuneComponentsPage: GeoTypeCommuneComponentsPage;
  let geoTypeCommuneUpdatePage: GeoTypeCommuneUpdatePage;
  let geoTypeCommuneDeleteDialog: GeoTypeCommuneDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeoTypeCommunes', async () => {
    await navBarPage.goToEntity('geo-type-commune');
    geoTypeCommuneComponentsPage = new GeoTypeCommuneComponentsPage();
    await browser.wait(ec.visibilityOf(geoTypeCommuneComponentsPage.title), 5000);
    expect(await geoTypeCommuneComponentsPage.getTitle()).to.eq('sidotApp.geoTypeCommune.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(geoTypeCommuneComponentsPage.entities), ec.visibilityOf(geoTypeCommuneComponentsPage.noResult)),
      1000
    );
  });

  it('should load create GeoTypeCommune page', async () => {
    await geoTypeCommuneComponentsPage.clickOnCreateButton();
    geoTypeCommuneUpdatePage = new GeoTypeCommuneUpdatePage();
    expect(await geoTypeCommuneUpdatePage.getPageTitle()).to.eq('sidotApp.geoTypeCommune.home.createOrEditLabel');
    await geoTypeCommuneUpdatePage.cancel();
  });

  it('should create and save GeoTypeCommunes', async () => {
    const nbButtonsBeforeCreate = await geoTypeCommuneComponentsPage.countDeleteButtons();

    await geoTypeCommuneComponentsPage.clickOnCreateButton();

    await promise.all([geoTypeCommuneUpdatePage.setLibelleInput('libelle')]);

    expect(await geoTypeCommuneUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await geoTypeCommuneUpdatePage.save();
    expect(await geoTypeCommuneUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geoTypeCommuneComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last GeoTypeCommune', async () => {
    const nbButtonsBeforeDelete = await geoTypeCommuneComponentsPage.countDeleteButtons();
    await geoTypeCommuneComponentsPage.clickOnLastDeleteButton();

    geoTypeCommuneDeleteDialog = new GeoTypeCommuneDeleteDialog();
    expect(await geoTypeCommuneDeleteDialog.getDialogTitle()).to.eq('sidotApp.geoTypeCommune.delete.question');
    await geoTypeCommuneDeleteDialog.clickOnConfirmButton();

    expect(await geoTypeCommuneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
