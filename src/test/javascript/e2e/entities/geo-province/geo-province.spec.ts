import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeoProvinceComponentsPage, GeoProvinceDeleteDialog, GeoProvinceUpdatePage } from './geo-province.page-object';

const expect = chai.expect;

describe('GeoProvince e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geoProvinceComponentsPage: GeoProvinceComponentsPage;
  let geoProvinceUpdatePage: GeoProvinceUpdatePage;
  let geoProvinceDeleteDialog: GeoProvinceDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeoProvinces', async () => {
    await navBarPage.goToEntity('geo-province');
    geoProvinceComponentsPage = new GeoProvinceComponentsPage();
    await browser.wait(ec.visibilityOf(geoProvinceComponentsPage.title), 5000);
    expect(await geoProvinceComponentsPage.getTitle()).to.eq('sidotApp.geoProvince.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(geoProvinceComponentsPage.entities), ec.visibilityOf(geoProvinceComponentsPage.noResult)),
      1000
    );
  });

  it('should load create GeoProvince page', async () => {
    await geoProvinceComponentsPage.clickOnCreateButton();
    geoProvinceUpdatePage = new GeoProvinceUpdatePage();
    expect(await geoProvinceUpdatePage.getPageTitle()).to.eq('sidotApp.geoProvince.home.createOrEditLabel');
    await geoProvinceUpdatePage.cancel();
  });

  it('should create and save GeoProvinces', async () => {
    const nbButtonsBeforeCreate = await geoProvinceComponentsPage.countDeleteButtons();

    await geoProvinceComponentsPage.clickOnCreateButton();

    await promise.all([geoProvinceUpdatePage.setLibelleInput('libelle'), geoProvinceUpdatePage.georegionSelectLastOption()]);

    expect(await geoProvinceUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await geoProvinceUpdatePage.save();
    expect(await geoProvinceUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geoProvinceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GeoProvince', async () => {
    const nbButtonsBeforeDelete = await geoProvinceComponentsPage.countDeleteButtons();
    await geoProvinceComponentsPage.clickOnLastDeleteButton();

    geoProvinceDeleteDialog = new GeoProvinceDeleteDialog();
    expect(await geoProvinceDeleteDialog.getDialogTitle()).to.eq('sidotApp.geoProvince.delete.question');
    await geoProvinceDeleteDialog.clickOnConfirmButton();

    expect(await geoProvinceComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
