import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { AnalyseParametreComponentsPage, AnalyseParametreDeleteDialog, AnalyseParametreUpdatePage } from './analyse-parametre.page-object';

const expect = chai.expect;

describe('AnalyseParametre e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let analyseParametreComponentsPage: AnalyseParametreComponentsPage;
  let analyseParametreUpdatePage: AnalyseParametreUpdatePage;
  let analyseParametreDeleteDialog: AnalyseParametreDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load AnalyseParametres', async () => {
    await navBarPage.goToEntity('analyse-parametre');
    analyseParametreComponentsPage = new AnalyseParametreComponentsPage();
    await browser.wait(ec.visibilityOf(analyseParametreComponentsPage.title), 5000);
    expect(await analyseParametreComponentsPage.getTitle()).to.eq('sidotApp.analyseParametre.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(analyseParametreComponentsPage.entities), ec.visibilityOf(analyseParametreComponentsPage.noResult)),
      1000
    );
  });

  it('should load create AnalyseParametre page', async () => {
    await analyseParametreComponentsPage.clickOnCreateButton();
    analyseParametreUpdatePage = new AnalyseParametreUpdatePage();
    expect(await analyseParametreUpdatePage.getPageTitle()).to.eq('sidotApp.analyseParametre.home.createOrEditLabel');
    await analyseParametreUpdatePage.cancel();
  });

  it('should create and save AnalyseParametres', async () => {
    const nbButtonsBeforeCreate = await analyseParametreComponentsPage.countDeleteButtons();

    await analyseParametreComponentsPage.clickOnCreateButton();

    await promise.all([
      analyseParametreUpdatePage.setLibelleInput('libelle'),
      analyseParametreUpdatePage.analysespecialiteSelectLastOption(),
    ]);

    expect(await analyseParametreUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await analyseParametreUpdatePage.save();
    expect(await analyseParametreUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await analyseParametreComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last AnalyseParametre', async () => {
    const nbButtonsBeforeDelete = await analyseParametreComponentsPage.countDeleteButtons();
    await analyseParametreComponentsPage.clickOnLastDeleteButton();

    analyseParametreDeleteDialog = new AnalyseParametreDeleteDialog();
    expect(await analyseParametreDeleteDialog.getDialogTitle()).to.eq('sidotApp.analyseParametre.delete.question');
    await analyseParametreDeleteDialog.clickOnConfirmButton();

    expect(await analyseParametreComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
