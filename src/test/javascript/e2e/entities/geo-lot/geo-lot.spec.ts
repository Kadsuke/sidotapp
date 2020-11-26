import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeoLotComponentsPage, GeoLotDeleteDialog, GeoLotUpdatePage } from './geo-lot.page-object';

const expect = chai.expect;

describe('GeoLot e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geoLotComponentsPage: GeoLotComponentsPage;
  let geoLotUpdatePage: GeoLotUpdatePage;
  let geoLotDeleteDialog: GeoLotDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeoLots', async () => {
    await navBarPage.goToEntity('geo-lot');
    geoLotComponentsPage = new GeoLotComponentsPage();
    await browser.wait(ec.visibilityOf(geoLotComponentsPage.title), 5000);
    expect(await geoLotComponentsPage.getTitle()).to.eq('sidotApp.geoLot.home.title');
    await browser.wait(ec.or(ec.visibilityOf(geoLotComponentsPage.entities), ec.visibilityOf(geoLotComponentsPage.noResult)), 1000);
  });

  it('should load create GeoLot page', async () => {
    await geoLotComponentsPage.clickOnCreateButton();
    geoLotUpdatePage = new GeoLotUpdatePage();
    expect(await geoLotUpdatePage.getPageTitle()).to.eq('sidotApp.geoLot.home.createOrEditLabel');
    await geoLotUpdatePage.cancel();
  });

  it('should create and save GeoLots', async () => {
    const nbButtonsBeforeCreate = await geoLotComponentsPage.countDeleteButtons();

    await geoLotComponentsPage.clickOnCreateButton();

    await promise.all([geoLotUpdatePage.setLibelleInput('libelle'), geoLotUpdatePage.geosectionSelectLastOption()]);

    expect(await geoLotUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await geoLotUpdatePage.save();
    expect(await geoLotUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geoLotComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GeoLot', async () => {
    const nbButtonsBeforeDelete = await geoLotComponentsPage.countDeleteButtons();
    await geoLotComponentsPage.clickOnLastDeleteButton();

    geoLotDeleteDialog = new GeoLotDeleteDialog();
    expect(await geoLotDeleteDialog.getDialogTitle()).to.eq('sidotApp.geoLot.delete.question');
    await geoLotDeleteDialog.clickOnConfirmButton();

    expect(await geoLotComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
