import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeuPrevisionSTEPComponentsPage, GeuPrevisionSTEPDeleteDialog, GeuPrevisionSTEPUpdatePage } from './geu-prevision-step.page-object';

const expect = chai.expect;

describe('GeuPrevisionSTEP e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geuPrevisionSTEPComponentsPage: GeuPrevisionSTEPComponentsPage;
  let geuPrevisionSTEPUpdatePage: GeuPrevisionSTEPUpdatePage;
  let geuPrevisionSTEPDeleteDialog: GeuPrevisionSTEPDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeuPrevisionSTEPS', async () => {
    await navBarPage.goToEntity('geu-prevision-step');
    geuPrevisionSTEPComponentsPage = new GeuPrevisionSTEPComponentsPage();
    await browser.wait(ec.visibilityOf(geuPrevisionSTEPComponentsPage.title), 5000);
    expect(await geuPrevisionSTEPComponentsPage.getTitle()).to.eq('sidotApp.geuPrevisionSTEP.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(geuPrevisionSTEPComponentsPage.entities), ec.visibilityOf(geuPrevisionSTEPComponentsPage.noResult)),
      1000
    );
  });

  it('should load create GeuPrevisionSTEP page', async () => {
    await geuPrevisionSTEPComponentsPage.clickOnCreateButton();
    geuPrevisionSTEPUpdatePage = new GeuPrevisionSTEPUpdatePage();
    expect(await geuPrevisionSTEPUpdatePage.getPageTitle()).to.eq('sidotApp.geuPrevisionSTEP.home.createOrEditLabel');
    await geuPrevisionSTEPUpdatePage.cancel();
  });

  it('should create and save GeuPrevisionSTEPS', async () => {
    const nbButtonsBeforeCreate = await geuPrevisionSTEPComponentsPage.countDeleteButtons();

    await geuPrevisionSTEPComponentsPage.clickOnCreateButton();

    await promise.all([
      geuPrevisionSTEPUpdatePage.setDatePrevStepInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      geuPrevisionSTEPUpdatePage.setVolumePrevStepInput('volumePrevStep'),
      geuPrevisionSTEPUpdatePage.geustepSelectLastOption(),
    ]);

    expect(await geuPrevisionSTEPUpdatePage.getDatePrevStepInput()).to.contain(
      '2001-01-01T02:30',
      'Expected datePrevStep value to be equals to 2000-12-31'
    );
    expect(await geuPrevisionSTEPUpdatePage.getVolumePrevStepInput()).to.eq(
      'volumePrevStep',
      'Expected VolumePrevStep value to be equals to volumePrevStep'
    );

    await geuPrevisionSTEPUpdatePage.save();
    expect(await geuPrevisionSTEPUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geuPrevisionSTEPComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last GeuPrevisionSTEP', async () => {
    const nbButtonsBeforeDelete = await geuPrevisionSTEPComponentsPage.countDeleteButtons();
    await geuPrevisionSTEPComponentsPage.clickOnLastDeleteButton();

    geuPrevisionSTEPDeleteDialog = new GeuPrevisionSTEPDeleteDialog();
    expect(await geuPrevisionSTEPDeleteDialog.getDialogTitle()).to.eq('sidotApp.geuPrevisionSTEP.delete.question');
    await geuPrevisionSTEPDeleteDialog.clickOnConfirmButton();

    expect(await geuPrevisionSTEPComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
