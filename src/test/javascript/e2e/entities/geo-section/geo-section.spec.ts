import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeoSectionComponentsPage, GeoSectionDeleteDialog, GeoSectionUpdatePage } from './geo-section.page-object';

const expect = chai.expect;

describe('GeoSection e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geoSectionComponentsPage: GeoSectionComponentsPage;
  let geoSectionUpdatePage: GeoSectionUpdatePage;
  let geoSectionDeleteDialog: GeoSectionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeoSections', async () => {
    await navBarPage.goToEntity('geo-section');
    geoSectionComponentsPage = new GeoSectionComponentsPage();
    await browser.wait(ec.visibilityOf(geoSectionComponentsPage.title), 5000);
    expect(await geoSectionComponentsPage.getTitle()).to.eq('sidotApp.geoSection.home.title');
    await browser.wait(ec.or(ec.visibilityOf(geoSectionComponentsPage.entities), ec.visibilityOf(geoSectionComponentsPage.noResult)), 1000);
  });

  it('should load create GeoSection page', async () => {
    await geoSectionComponentsPage.clickOnCreateButton();
    geoSectionUpdatePage = new GeoSectionUpdatePage();
    expect(await geoSectionUpdatePage.getPageTitle()).to.eq('sidotApp.geoSection.home.createOrEditLabel');
    await geoSectionUpdatePage.cancel();
  });

  it('should create and save GeoSections', async () => {
    const nbButtonsBeforeCreate = await geoSectionComponentsPage.countDeleteButtons();

    await geoSectionComponentsPage.clickOnCreateButton();

    await promise.all([geoSectionUpdatePage.setLibelleInput('libelle'), geoSectionUpdatePage.geosecteurSelectLastOption()]);

    expect(await geoSectionUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await geoSectionUpdatePage.save();
    expect(await geoSectionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geoSectionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GeoSection', async () => {
    const nbButtonsBeforeDelete = await geoSectionComponentsPage.countDeleteButtons();
    await geoSectionComponentsPage.clickOnLastDeleteButton();

    geoSectionDeleteDialog = new GeoSectionDeleteDialog();
    expect(await geoSectionDeleteDialog.getDialogTitle()).to.eq('sidotApp.geoSection.delete.question');
    await geoSectionDeleteDialog.clickOnConfirmButton();

    expect(await geoSectionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
