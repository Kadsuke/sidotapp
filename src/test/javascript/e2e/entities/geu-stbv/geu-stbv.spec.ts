import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeuSTBVComponentsPage, GeuSTBVDeleteDialog, GeuSTBVUpdatePage } from './geu-stbv.page-object';

const expect = chai.expect;

describe('GeuSTBV e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geuSTBVComponentsPage: GeuSTBVComponentsPage;
  let geuSTBVUpdatePage: GeuSTBVUpdatePage;
  let geuSTBVDeleteDialog: GeuSTBVDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeuSTBVS', async () => {
    await navBarPage.goToEntity('geu-stbv');
    geuSTBVComponentsPage = new GeuSTBVComponentsPage();
    await browser.wait(ec.visibilityOf(geuSTBVComponentsPage.title), 5000);
    expect(await geuSTBVComponentsPage.getTitle()).to.eq('sidotApp.geuSTBV.home.title');
    await browser.wait(ec.or(ec.visibilityOf(geuSTBVComponentsPage.entities), ec.visibilityOf(geuSTBVComponentsPage.noResult)), 1000);
  });

  it('should load create GeuSTBV page', async () => {
    await geuSTBVComponentsPage.clickOnCreateButton();
    geuSTBVUpdatePage = new GeuSTBVUpdatePage();
    expect(await geuSTBVUpdatePage.getPageTitle()).to.eq('sidotApp.geuSTBV.home.createOrEditLabel');
    await geuSTBVUpdatePage.cancel();
  });

  it('should create and save GeuSTBVS', async () => {
    const nbButtonsBeforeCreate = await geuSTBVComponentsPage.countDeleteButtons();

    await geuSTBVComponentsPage.clickOnCreateButton();

    await promise.all([
      geuSTBVUpdatePage.setLibelStbvInput('libelStbv'),
      geuSTBVUpdatePage.setResponsableInput('responsable'),
      geuSTBVUpdatePage.setContactInput('contact'),
    ]);

    expect(await geuSTBVUpdatePage.getLibelStbvInput()).to.eq('libelStbv', 'Expected LibelStbv value to be equals to libelStbv');
    expect(await geuSTBVUpdatePage.getResponsableInput()).to.eq('responsable', 'Expected Responsable value to be equals to responsable');
    expect(await geuSTBVUpdatePage.getContactInput()).to.eq('contact', 'Expected Contact value to be equals to contact');

    await geuSTBVUpdatePage.save();
    expect(await geuSTBVUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geuSTBVComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GeuSTBV', async () => {
    const nbButtonsBeforeDelete = await geuSTBVComponentsPage.countDeleteButtons();
    await geuSTBVComponentsPage.clickOnLastDeleteButton();

    geuSTBVDeleteDialog = new GeuSTBVDeleteDialog();
    expect(await geuSTBVDeleteDialog.getDialogTitle()).to.eq('sidotApp.geuSTBV.delete.question');
    await geuSTBVDeleteDialog.clickOnConfirmButton();

    expect(await geuSTBVComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
