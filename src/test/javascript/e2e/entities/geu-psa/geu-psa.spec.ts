import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeuPSAComponentsPage, GeuPSADeleteDialog, GeuPSAUpdatePage } from './geu-psa.page-object';

const expect = chai.expect;

describe('GeuPSA e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geuPSAComponentsPage: GeuPSAComponentsPage;
  let geuPSAUpdatePage: GeuPSAUpdatePage;
  let geuPSADeleteDialog: GeuPSADeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeuPSAS', async () => {
    await navBarPage.goToEntity('geu-psa');
    geuPSAComponentsPage = new GeuPSAComponentsPage();
    await browser.wait(ec.visibilityOf(geuPSAComponentsPage.title), 5000);
    expect(await geuPSAComponentsPage.getTitle()).to.eq('sidotApp.geuPSA.home.title');
    await browser.wait(ec.or(ec.visibilityOf(geuPSAComponentsPage.entities), ec.visibilityOf(geuPSAComponentsPage.noResult)), 1000);
  });

  it('should load create GeuPSA page', async () => {
    await geuPSAComponentsPage.clickOnCreateButton();
    geuPSAUpdatePage = new GeuPSAUpdatePage();
    expect(await geuPSAUpdatePage.getPageTitle()).to.eq('sidotApp.geuPSA.home.createOrEditLabel');
    await geuPSAUpdatePage.cancel();
  });

  it('should create and save GeuPSAS', async () => {
    const nbButtonsBeforeCreate = await geuPSAComponentsPage.countDeleteButtons();

    await geuPSAComponentsPage.clickOnCreateButton();

    await promise.all([
      geuPSAUpdatePage.setDateElaborationInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      geuPSAUpdatePage.setDateMiseEnOeuvreInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      geuPSAUpdatePage.geocommuneSelectLastOption(),
    ]);

    expect(await geuPSAUpdatePage.getDateElaborationInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateElaboration value to be equals to 2000-12-31'
    );
    expect(await geuPSAUpdatePage.getDateMiseEnOeuvreInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateMiseEnOeuvre value to be equals to 2000-12-31'
    );

    await geuPSAUpdatePage.save();
    expect(await geuPSAUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geuPSAComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GeuPSA', async () => {
    const nbButtonsBeforeDelete = await geuPSAComponentsPage.countDeleteButtons();
    await geuPSAComponentsPage.clickOnLastDeleteButton();

    geuPSADeleteDialog = new GeuPSADeleteDialog();
    expect(await geuPSADeleteDialog.getDialogTitle()).to.eq('sidotApp.geuPSA.delete.question');
    await geuPSADeleteDialog.clickOnConfirmButton();

    expect(await geuPSAComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
