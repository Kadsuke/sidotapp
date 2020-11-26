import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TacheronsComponentsPage, TacheronsDeleteDialog, TacheronsUpdatePage } from './tacherons.page-object';

const expect = chai.expect;

describe('Tacherons e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let tacheronsComponentsPage: TacheronsComponentsPage;
  let tacheronsUpdatePage: TacheronsUpdatePage;
  let tacheronsDeleteDialog: TacheronsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Tacherons', async () => {
    await navBarPage.goToEntity('tacherons');
    tacheronsComponentsPage = new TacheronsComponentsPage();
    await browser.wait(ec.visibilityOf(tacheronsComponentsPage.title), 5000);
    expect(await tacheronsComponentsPage.getTitle()).to.eq('sidotApp.tacherons.home.title');
    await browser.wait(ec.or(ec.visibilityOf(tacheronsComponentsPage.entities), ec.visibilityOf(tacheronsComponentsPage.noResult)), 1000);
  });

  it('should load create Tacherons page', async () => {
    await tacheronsComponentsPage.clickOnCreateButton();
    tacheronsUpdatePage = new TacheronsUpdatePage();
    expect(await tacheronsUpdatePage.getPageTitle()).to.eq('sidotApp.tacherons.home.createOrEditLabel');
    await tacheronsUpdatePage.cancel();
  });

  it('should create and save Tacherons', async () => {
    const nbButtonsBeforeCreate = await tacheronsComponentsPage.countDeleteButtons();

    await tacheronsComponentsPage.clickOnCreateButton();

    await promise.all([
      tacheronsUpdatePage.setNomInput('nom'),
      tacheronsUpdatePage.setTelInput('tel'),
      tacheronsUpdatePage.setAdresseInput('adresse'),
    ]);

    expect(await tacheronsUpdatePage.getNomInput()).to.eq('nom', 'Expected Nom value to be equals to nom');
    expect(await tacheronsUpdatePage.getTelInput()).to.eq('tel', 'Expected Tel value to be equals to tel');
    expect(await tacheronsUpdatePage.getAdresseInput()).to.eq('adresse', 'Expected Adresse value to be equals to adresse');

    await tacheronsUpdatePage.save();
    expect(await tacheronsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await tacheronsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Tacherons', async () => {
    const nbButtonsBeforeDelete = await tacheronsComponentsPage.countDeleteButtons();
    await tacheronsComponentsPage.clickOnLastDeleteButton();

    tacheronsDeleteDialog = new TacheronsDeleteDialog();
    expect(await tacheronsDeleteDialog.getDialogTitle()).to.eq('sidotApp.tacherons.delete.question');
    await tacheronsDeleteDialog.clickOnConfirmButton();

    expect(await tacheronsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
