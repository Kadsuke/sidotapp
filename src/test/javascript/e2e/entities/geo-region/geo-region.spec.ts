import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeoRegionComponentsPage, GeoRegionDeleteDialog, GeoRegionUpdatePage } from './geo-region.page-object';

const expect = chai.expect;

describe('GeoRegion e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geoRegionComponentsPage: GeoRegionComponentsPage;
  let geoRegionUpdatePage: GeoRegionUpdatePage;
  let geoRegionDeleteDialog: GeoRegionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeoRegions', async () => {
    await navBarPage.goToEntity('geo-region');
    geoRegionComponentsPage = new GeoRegionComponentsPage();
    await browser.wait(ec.visibilityOf(geoRegionComponentsPage.title), 5000);
    expect(await geoRegionComponentsPage.getTitle()).to.eq('sidotApp.geoRegion.home.title');
    await browser.wait(ec.or(ec.visibilityOf(geoRegionComponentsPage.entities), ec.visibilityOf(geoRegionComponentsPage.noResult)), 1000);
  });

  it('should load create GeoRegion page', async () => {
    await geoRegionComponentsPage.clickOnCreateButton();
    geoRegionUpdatePage = new GeoRegionUpdatePage();
    expect(await geoRegionUpdatePage.getPageTitle()).to.eq('sidotApp.geoRegion.home.createOrEditLabel');
    await geoRegionUpdatePage.cancel();
  });

  it('should create and save GeoRegions', async () => {
    const nbButtonsBeforeCreate = await geoRegionComponentsPage.countDeleteButtons();

    await geoRegionComponentsPage.clickOnCreateButton();

    await promise.all([geoRegionUpdatePage.setLibelleInput('libelle')]);

    expect(await geoRegionUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await geoRegionUpdatePage.save();
    expect(await geoRegionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geoRegionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GeoRegion', async () => {
    const nbButtonsBeforeDelete = await geoRegionComponentsPage.countDeleteButtons();
    await geoRegionComponentsPage.clickOnLastDeleteButton();

    geoRegionDeleteDialog = new GeoRegionDeleteDialog();
    expect(await geoRegionDeleteDialog.getDialogTitle()).to.eq('sidotApp.geoRegion.delete.question');
    await geoRegionDeleteDialog.clickOnConfirmButton();

    expect(await geoRegionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
