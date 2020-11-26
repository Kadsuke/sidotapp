import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { EtatProjetComponentsPage, EtatProjetDeleteDialog, EtatProjetUpdatePage } from './etat-projet.page-object';

const expect = chai.expect;

describe('EtatProjet e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let etatProjetComponentsPage: EtatProjetComponentsPage;
  let etatProjetUpdatePage: EtatProjetUpdatePage;
  let etatProjetDeleteDialog: EtatProjetDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load EtatProjets', async () => {
    await navBarPage.goToEntity('etat-projet');
    etatProjetComponentsPage = new EtatProjetComponentsPage();
    await browser.wait(ec.visibilityOf(etatProjetComponentsPage.title), 5000);
    expect(await etatProjetComponentsPage.getTitle()).to.eq('sidotApp.etatProjet.home.title');
    await browser.wait(ec.or(ec.visibilityOf(etatProjetComponentsPage.entities), ec.visibilityOf(etatProjetComponentsPage.noResult)), 1000);
  });

  it('should load create EtatProjet page', async () => {
    await etatProjetComponentsPage.clickOnCreateButton();
    etatProjetUpdatePage = new EtatProjetUpdatePage();
    expect(await etatProjetUpdatePage.getPageTitle()).to.eq('sidotApp.etatProjet.home.createOrEditLabel');
    await etatProjetUpdatePage.cancel();
  });

  it('should create and save EtatProjets', async () => {
    const nbButtonsBeforeCreate = await etatProjetComponentsPage.countDeleteButtons();

    await etatProjetComponentsPage.clickOnCreateButton();

    await promise.all([etatProjetUpdatePage.setLibelleInput('libelle')]);

    expect(await etatProjetUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await etatProjetUpdatePage.save();
    expect(await etatProjetUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await etatProjetComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last EtatProjet', async () => {
    const nbButtonsBeforeDelete = await etatProjetComponentsPage.countDeleteButtons();
    await etatProjetComponentsPage.clickOnLastDeleteButton();

    etatProjetDeleteDialog = new EtatProjetDeleteDialog();
    expect(await etatProjetDeleteDialog.getDialogTitle()).to.eq('sidotApp.etatProjet.delete.question');
    await etatProjetDeleteDialog.clickOnConfirmButton();

    expect(await etatProjetComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
