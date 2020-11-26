import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeoCommuneComponentsPage, GeoCommuneDeleteDialog, GeoCommuneUpdatePage } from './geo-commune.page-object';

const expect = chai.expect;

describe('GeoCommune e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geoCommuneComponentsPage: GeoCommuneComponentsPage;
  let geoCommuneUpdatePage: GeoCommuneUpdatePage;
  let geoCommuneDeleteDialog: GeoCommuneDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeoCommunes', async () => {
    await navBarPage.goToEntity('geo-commune');
    geoCommuneComponentsPage = new GeoCommuneComponentsPage();
    await browser.wait(ec.visibilityOf(geoCommuneComponentsPage.title), 5000);
    expect(await geoCommuneComponentsPage.getTitle()).to.eq('sidotApp.geoCommune.home.title');
    await browser.wait(ec.or(ec.visibilityOf(geoCommuneComponentsPage.entities), ec.visibilityOf(geoCommuneComponentsPage.noResult)), 1000);
  });

  it('should load create GeoCommune page', async () => {
    await geoCommuneComponentsPage.clickOnCreateButton();
    geoCommuneUpdatePage = new GeoCommuneUpdatePage();
    expect(await geoCommuneUpdatePage.getPageTitle()).to.eq('sidotApp.geoCommune.home.createOrEditLabel');
    await geoCommuneUpdatePage.cancel();
  });

  it('should create and save GeoCommunes', async () => {
    const nbButtonsBeforeCreate = await geoCommuneComponentsPage.countDeleteButtons();

    await geoCommuneComponentsPage.clickOnCreateButton();

    await promise.all([
      geoCommuneUpdatePage.setLibelleInput('libelle'),
      geoCommuneUpdatePage.geoprovinceSelectLastOption(),
      geoCommuneUpdatePage.geotypecommuneSelectLastOption(),
    ]);

    expect(await geoCommuneUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await geoCommuneUpdatePage.save();
    expect(await geoCommuneUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geoCommuneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GeoCommune', async () => {
    const nbButtonsBeforeDelete = await geoCommuneComponentsPage.countDeleteButtons();
    await geoCommuneComponentsPage.clickOnLastDeleteButton();

    geoCommuneDeleteDialog = new GeoCommuneDeleteDialog();
    expect(await geoCommuneDeleteDialog.getDialogTitle()).to.eq('sidotApp.geoCommune.delete.question');
    await geoCommuneDeleteDialog.clickOnConfirmButton();

    expect(await geoCommuneComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
