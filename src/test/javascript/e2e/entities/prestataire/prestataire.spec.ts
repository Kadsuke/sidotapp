import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PrestataireComponentsPage, PrestataireDeleteDialog, PrestataireUpdatePage } from './prestataire.page-object';

const expect = chai.expect;

describe('Prestataire e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let prestataireComponentsPage: PrestataireComponentsPage;
  let prestataireUpdatePage: PrestataireUpdatePage;
  let prestataireDeleteDialog: PrestataireDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Prestataires', async () => {
    await navBarPage.goToEntity('prestataire');
    prestataireComponentsPage = new PrestataireComponentsPage();
    await browser.wait(ec.visibilityOf(prestataireComponentsPage.title), 5000);
    expect(await prestataireComponentsPage.getTitle()).to.eq('sidotApp.prestataire.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(prestataireComponentsPage.entities), ec.visibilityOf(prestataireComponentsPage.noResult)),
      1000
    );
  });

  it('should load create Prestataire page', async () => {
    await prestataireComponentsPage.clickOnCreateButton();
    prestataireUpdatePage = new PrestataireUpdatePage();
    expect(await prestataireUpdatePage.getPageTitle()).to.eq('sidotApp.prestataire.home.createOrEditLabel');
    await prestataireUpdatePage.cancel();
  });

  it('should create and save Prestataires', async () => {
    const nbButtonsBeforeCreate = await prestataireComponentsPage.countDeleteButtons();

    await prestataireComponentsPage.clickOnCreateButton();

    await promise.all([
      prestataireUpdatePage.setLibelleInput('libelle'),
      prestataireUpdatePage.setResponsableInput('responsable'),
      prestataireUpdatePage.setContactInput('contact'),
    ]);

    expect(await prestataireUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');
    expect(await prestataireUpdatePage.getResponsableInput()).to.eq(
      'responsable',
      'Expected Responsable value to be equals to responsable'
    );
    expect(await prestataireUpdatePage.getContactInput()).to.eq('contact', 'Expected Contact value to be equals to contact');

    await prestataireUpdatePage.save();
    expect(await prestataireUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await prestataireComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Prestataire', async () => {
    const nbButtonsBeforeDelete = await prestataireComponentsPage.countDeleteButtons();
    await prestataireComponentsPage.clickOnLastDeleteButton();

    prestataireDeleteDialog = new PrestataireDeleteDialog();
    expect(await prestataireDeleteDialog.getDialogTitle()).to.eq('sidotApp.prestataire.delete.question');
    await prestataireDeleteDialog.clickOnConfirmButton();

    expect(await prestataireComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
