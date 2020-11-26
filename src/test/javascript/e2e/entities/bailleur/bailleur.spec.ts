import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { BailleurComponentsPage, BailleurDeleteDialog, BailleurUpdatePage } from './bailleur.page-object';

const expect = chai.expect;

describe('Bailleur e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let bailleurComponentsPage: BailleurComponentsPage;
  let bailleurUpdatePage: BailleurUpdatePage;
  let bailleurDeleteDialog: BailleurDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Bailleurs', async () => {
    await navBarPage.goToEntity('bailleur');
    bailleurComponentsPage = new BailleurComponentsPage();
    await browser.wait(ec.visibilityOf(bailleurComponentsPage.title), 5000);
    expect(await bailleurComponentsPage.getTitle()).to.eq('sidotApp.bailleur.home.title');
    await browser.wait(ec.or(ec.visibilityOf(bailleurComponentsPage.entities), ec.visibilityOf(bailleurComponentsPage.noResult)), 1000);
  });

  it('should load create Bailleur page', async () => {
    await bailleurComponentsPage.clickOnCreateButton();
    bailleurUpdatePage = new BailleurUpdatePage();
    expect(await bailleurUpdatePage.getPageTitle()).to.eq('sidotApp.bailleur.home.createOrEditLabel');
    await bailleurUpdatePage.cancel();
  });

  it('should create and save Bailleurs', async () => {
    const nbButtonsBeforeCreate = await bailleurComponentsPage.countDeleteButtons();

    await bailleurComponentsPage.clickOnCreateButton();

    await promise.all([
      bailleurUpdatePage.setLibelleInput('libelle'),
      bailleurUpdatePage.setResponsbaleInput('responsbale'),
      bailleurUpdatePage.setContactInput('contact'),
    ]);

    expect(await bailleurUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');
    expect(await bailleurUpdatePage.getResponsbaleInput()).to.eq('responsbale', 'Expected Responsbale value to be equals to responsbale');
    expect(await bailleurUpdatePage.getContactInput()).to.eq('contact', 'Expected Contact value to be equals to contact');

    await bailleurUpdatePage.save();
    expect(await bailleurUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await bailleurComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Bailleur', async () => {
    const nbButtonsBeforeDelete = await bailleurComponentsPage.countDeleteButtons();
    await bailleurComponentsPage.clickOnLastDeleteButton();

    bailleurDeleteDialog = new BailleurDeleteDialog();
    expect(await bailleurDeleteDialog.getDialogTitle()).to.eq('sidotApp.bailleur.delete.question');
    await bailleurDeleteDialog.clickOnConfirmButton();

    expect(await bailleurComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
