import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SiteComponentsPage, SiteDeleteDialog, SiteUpdatePage } from './site.page-object';

const expect = chai.expect;

describe('Site e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let siteComponentsPage: SiteComponentsPage;
  let siteUpdatePage: SiteUpdatePage;
  let siteDeleteDialog: SiteDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Sites', async () => {
    await navBarPage.goToEntity('site');
    siteComponentsPage = new SiteComponentsPage();
    await browser.wait(ec.visibilityOf(siteComponentsPage.title), 5000);
    expect(await siteComponentsPage.getTitle()).to.eq('sidotApp.site.home.title');
    await browser.wait(ec.or(ec.visibilityOf(siteComponentsPage.entities), ec.visibilityOf(siteComponentsPage.noResult)), 1000);
  });

  it('should load create Site page', async () => {
    await siteComponentsPage.clickOnCreateButton();
    siteUpdatePage = new SiteUpdatePage();
    expect(await siteUpdatePage.getPageTitle()).to.eq('sidotApp.site.home.createOrEditLabel');
    await siteUpdatePage.cancel();
  });

  it('should create and save Sites', async () => {
    const nbButtonsBeforeCreate = await siteComponentsPage.countDeleteButtons();

    await siteComponentsPage.clickOnCreateButton();

    await promise.all([
      siteUpdatePage.setLibelleInput('libelle'),
      siteUpdatePage.setResponsableInput('responsable'),
      siteUpdatePage.setContactInput('contact'),
      siteUpdatePage.centreSelectLastOption(),
    ]);

    expect(await siteUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');
    expect(await siteUpdatePage.getResponsableInput()).to.eq('responsable', 'Expected Responsable value to be equals to responsable');
    expect(await siteUpdatePage.getContactInput()).to.eq('contact', 'Expected Contact value to be equals to contact');

    await siteUpdatePage.save();
    expect(await siteUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await siteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Site', async () => {
    const nbButtonsBeforeDelete = await siteComponentsPage.countDeleteButtons();
    await siteComponentsPage.clickOnLastDeleteButton();

    siteDeleteDialog = new SiteDeleteDialog();
    expect(await siteDeleteDialog.getDialogTitle()).to.eq('sidotApp.site.delete.question');
    await siteDeleteDialog.clickOnConfirmButton();

    expect(await siteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
