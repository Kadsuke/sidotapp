import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EtatProgramComponentsPage, EtatProgramDeleteDialog, EtatProgramUpdatePage } from './etat-program.page-object';

const expect = chai.expect;

describe('EtatProgram e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let etatProgramComponentsPage: EtatProgramComponentsPage;
  let etatProgramUpdatePage: EtatProgramUpdatePage;
  let etatProgramDeleteDialog: EtatProgramDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load EtatPrograms', async () => {
    await navBarPage.goToEntity('etat-program');
    etatProgramComponentsPage = new EtatProgramComponentsPage();
    await browser.wait(ec.visibilityOf(etatProgramComponentsPage.title), 5000);
    expect(await etatProgramComponentsPage.getTitle()).to.eq('sidotApp.etatProgram.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(etatProgramComponentsPage.entities), ec.visibilityOf(etatProgramComponentsPage.noResult)),
      1000
    );
  });

  it('should load create EtatProgram page', async () => {
    await etatProgramComponentsPage.clickOnCreateButton();
    etatProgramUpdatePage = new EtatProgramUpdatePage();
    expect(await etatProgramUpdatePage.getPageTitle()).to.eq('sidotApp.etatProgram.home.createOrEditLabel');
    await etatProgramUpdatePage.cancel();
  });

  it('should create and save EtatPrograms', async () => {
    const nbButtonsBeforeCreate = await etatProgramComponentsPage.countDeleteButtons();

    await etatProgramComponentsPage.clickOnCreateButton();

    await promise.all([etatProgramUpdatePage.setLibelleInput('libelle')]);

    expect(await etatProgramUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await etatProgramUpdatePage.save();
    expect(await etatProgramUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await etatProgramComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last EtatProgram', async () => {
    const nbButtonsBeforeDelete = await etatProgramComponentsPage.countDeleteButtons();
    await etatProgramComponentsPage.clickOnLastDeleteButton();

    etatProgramDeleteDialog = new EtatProgramDeleteDialog();
    expect(await etatProgramDeleteDialog.getDialogTitle()).to.eq('sidotApp.etatProgram.delete.question');
    await etatProgramDeleteDialog.clickOnConfirmButton();

    expect(await etatProgramComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
