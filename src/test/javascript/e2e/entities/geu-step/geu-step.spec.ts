import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeuSTEPComponentsPage, GeuSTEPDeleteDialog, GeuSTEPUpdatePage } from './geu-step.page-object';

const expect = chai.expect;

describe('GeuSTEP e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geuSTEPComponentsPage: GeuSTEPComponentsPage;
  let geuSTEPUpdatePage: GeuSTEPUpdatePage;
  let geuSTEPDeleteDialog: GeuSTEPDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeuSTEPS', async () => {
    await navBarPage.goToEntity('geu-step');
    geuSTEPComponentsPage = new GeuSTEPComponentsPage();
    await browser.wait(ec.visibilityOf(geuSTEPComponentsPage.title), 5000);
    expect(await geuSTEPComponentsPage.getTitle()).to.eq('sidotApp.geuSTEP.home.title');
    await browser.wait(ec.or(ec.visibilityOf(geuSTEPComponentsPage.entities), ec.visibilityOf(geuSTEPComponentsPage.noResult)), 1000);
  });

  it('should load create GeuSTEP page', async () => {
    await geuSTEPComponentsPage.clickOnCreateButton();
    geuSTEPUpdatePage = new GeuSTEPUpdatePage();
    expect(await geuSTEPUpdatePage.getPageTitle()).to.eq('sidotApp.geuSTEP.home.createOrEditLabel');
    await geuSTEPUpdatePage.cancel();
  });

  it('should create and save GeuSTEPS', async () => {
    const nbButtonsBeforeCreate = await geuSTEPComponentsPage.countDeleteButtons();

    await geuSTEPComponentsPage.clickOnCreateButton();

    await promise.all([
      geuSTEPUpdatePage.setLibelSTEPInput('libelSTEP'),
      geuSTEPUpdatePage.setResponsableInput('responsable'),
      geuSTEPUpdatePage.setContactInput('contact'),
    ]);

    expect(await geuSTEPUpdatePage.getLibelSTEPInput()).to.eq('libelSTEP', 'Expected LibelSTEP value to be equals to libelSTEP');
    expect(await geuSTEPUpdatePage.getResponsableInput()).to.eq('responsable', 'Expected Responsable value to be equals to responsable');
    expect(await geuSTEPUpdatePage.getContactInput()).to.eq('contact', 'Expected Contact value to be equals to contact');

    await geuSTEPUpdatePage.save();
    expect(await geuSTEPUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geuSTEPComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GeuSTEP', async () => {
    const nbButtonsBeforeDelete = await geuSTEPComponentsPage.countDeleteButtons();
    await geuSTEPComponentsPage.clickOnLastDeleteButton();

    geuSTEPDeleteDialog = new GeuSTEPDeleteDialog();
    expect(await geuSTEPDeleteDialog.getDialogTitle()).to.eq('sidotApp.geuSTEP.delete.question');
    await geuSTEPDeleteDialog.clickOnConfirmButton();

    expect(await geuSTEPComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
