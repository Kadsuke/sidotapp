import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { TypeInterventionComponentsPage, TypeInterventionDeleteDialog, TypeInterventionUpdatePage } from './type-intervention.page-object';

const expect = chai.expect;

describe('TypeIntervention e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let typeInterventionComponentsPage: TypeInterventionComponentsPage;
  let typeInterventionUpdatePage: TypeInterventionUpdatePage;
  let typeInterventionDeleteDialog: TypeInterventionDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load TypeInterventions', async () => {
    await navBarPage.goToEntity('type-intervention');
    typeInterventionComponentsPage = new TypeInterventionComponentsPage();
    await browser.wait(ec.visibilityOf(typeInterventionComponentsPage.title), 5000);
    expect(await typeInterventionComponentsPage.getTitle()).to.eq('sidotApp.typeIntervention.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(typeInterventionComponentsPage.entities), ec.visibilityOf(typeInterventionComponentsPage.noResult)),
      1000
    );
  });

  it('should load create TypeIntervention page', async () => {
    await typeInterventionComponentsPage.clickOnCreateButton();
    typeInterventionUpdatePage = new TypeInterventionUpdatePage();
    expect(await typeInterventionUpdatePage.getPageTitle()).to.eq('sidotApp.typeIntervention.home.createOrEditLabel');
    await typeInterventionUpdatePage.cancel();
  });

  it('should create and save TypeInterventions', async () => {
    const nbButtonsBeforeCreate = await typeInterventionComponentsPage.countDeleteButtons();

    await typeInterventionComponentsPage.clickOnCreateButton();

    await promise.all([typeInterventionUpdatePage.setLibelleInput('libelle')]);

    expect(await typeInterventionUpdatePage.getLibelleInput()).to.eq('libelle', 'Expected Libelle value to be equals to libelle');

    await typeInterventionUpdatePage.save();
    expect(await typeInterventionUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await typeInterventionComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last TypeIntervention', async () => {
    const nbButtonsBeforeDelete = await typeInterventionComponentsPage.countDeleteButtons();
    await typeInterventionComponentsPage.clickOnLastDeleteButton();

    typeInterventionDeleteDialog = new TypeInterventionDeleteDialog();
    expect(await typeInterventionDeleteDialog.getDialogTitle()).to.eq('sidotApp.typeIntervention.delete.question');
    await typeInterventionDeleteDialog.clickOnConfirmButton();

    expect(await typeInterventionComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
