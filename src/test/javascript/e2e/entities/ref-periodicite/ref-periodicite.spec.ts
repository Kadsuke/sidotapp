import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { RefPeriodiciteComponentsPage, RefPeriodiciteDeleteDialog, RefPeriodiciteUpdatePage } from './ref-periodicite.page-object';

const expect = chai.expect;

describe('RefPeriodicite e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let refPeriodiciteComponentsPage: RefPeriodiciteComponentsPage;
  let refPeriodiciteUpdatePage: RefPeriodiciteUpdatePage;
  let refPeriodiciteDeleteDialog: RefPeriodiciteDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load RefPeriodicites', async () => {
    await navBarPage.goToEntity('ref-periodicite');
    refPeriodiciteComponentsPage = new RefPeriodiciteComponentsPage();
    await browser.wait(ec.visibilityOf(refPeriodiciteComponentsPage.title), 5000);
    expect(await refPeriodiciteComponentsPage.getTitle()).to.eq('sidotApp.refPeriodicite.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(refPeriodiciteComponentsPage.entities), ec.visibilityOf(refPeriodiciteComponentsPage.noResult)),
      1000
    );
  });

  it('should load create RefPeriodicite page', async () => {
    await refPeriodiciteComponentsPage.clickOnCreateButton();
    refPeriodiciteUpdatePage = new RefPeriodiciteUpdatePage();
    expect(await refPeriodiciteUpdatePage.getPageTitle()).to.eq('sidotApp.refPeriodicite.home.createOrEditLabel');
    await refPeriodiciteUpdatePage.cancel();
  });

  it('should create and save RefPeriodicites', async () => {
    const nbButtonsBeforeCreate = await refPeriodiciteComponentsPage.countDeleteButtons();

    await refPeriodiciteComponentsPage.clickOnCreateButton();

    await promise.all([refPeriodiciteUpdatePage.setLibelleInput('libelle')]);

    expect(await refPeriodiciteUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await refPeriodiciteUpdatePage.save();
    expect(await refPeriodiciteUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await refPeriodiciteComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last RefPeriodicite', async () => {
    const nbButtonsBeforeDelete = await refPeriodiciteComponentsPage.countDeleteButtons();
    await refPeriodiciteComponentsPage.clickOnLastDeleteButton();

    refPeriodiciteDeleteDialog = new RefPeriodiciteDeleteDialog();
    expect(await refPeriodiciteDeleteDialog.getDialogTitle()).to.eq('sidotApp.refPeriodicite.delete.question');
    await refPeriodiciteDeleteDialog.clickOnConfirmButton();

    expect(await refPeriodiciteComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
