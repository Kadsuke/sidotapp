import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeuUsageComponentsPage, GeuUsageDeleteDialog, GeuUsageUpdatePage } from './geu-usage.page-object';

const expect = chai.expect;

describe('GeuUsage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geuUsageComponentsPage: GeuUsageComponentsPage;
  let geuUsageUpdatePage: GeuUsageUpdatePage;
  let geuUsageDeleteDialog: GeuUsageDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeuUsages', async () => {
    await navBarPage.goToEntity('geu-usage');
    geuUsageComponentsPage = new GeuUsageComponentsPage();
    await browser.wait(ec.visibilityOf(geuUsageComponentsPage.title), 5000);
    expect(await geuUsageComponentsPage.getTitle()).to.eq('sidotApp.geuUsage.home.title');
    await browser.wait(ec.or(ec.visibilityOf(geuUsageComponentsPage.entities), ec.visibilityOf(geuUsageComponentsPage.noResult)), 1000);
  });

  it('should load create GeuUsage page', async () => {
    await geuUsageComponentsPage.clickOnCreateButton();
    geuUsageUpdatePage = new GeuUsageUpdatePage();
    expect(await geuUsageUpdatePage.getPageTitle()).to.eq('sidotApp.geuUsage.home.createOrEditLabel');
    await geuUsageUpdatePage.cancel();
  });

  it('should create and save GeuUsages', async () => {
    const nbButtonsBeforeCreate = await geuUsageComponentsPage.countDeleteButtons();

    await geuUsageComponentsPage.clickOnCreateButton();

    await promise.all([geuUsageUpdatePage.setLibelleInput('libelle')]);

    expect(await geuUsageUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await geuUsageUpdatePage.save();
    expect(await geuUsageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geuUsageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GeuUsage', async () => {
    const nbButtonsBeforeDelete = await geuUsageComponentsPage.countDeleteButtons();
    await geuUsageComponentsPage.clickOnLastDeleteButton();

    geuUsageDeleteDialog = new GeuUsageDeleteDialog();
    expect(await geuUsageDeleteDialog.getDialogTitle()).to.eq('sidotApp.geuUsage.delete.question');
    await geuUsageDeleteDialog.clickOnConfirmButton();

    expect(await geuUsageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
