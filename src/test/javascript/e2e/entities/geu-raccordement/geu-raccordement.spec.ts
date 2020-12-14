import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeuRaccordementComponentsPage, GeuRaccordementDeleteDialog, GeuRaccordementUpdatePage } from './geu-raccordement.page-object';

const expect = chai.expect;

describe('GeuRaccordement e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geuRaccordementComponentsPage: GeuRaccordementComponentsPage;
  let geuRaccordementUpdatePage: GeuRaccordementUpdatePage;
  let geuRaccordementDeleteDialog: GeuRaccordementDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeuRaccordements', async () => {
    await navBarPage.goToEntity('geu-raccordement');
    geuRaccordementComponentsPage = new GeuRaccordementComponentsPage();
    await browser.wait(ec.visibilityOf(geuRaccordementComponentsPage.title), 5000);
    expect(await geuRaccordementComponentsPage.getTitle()).to.eq('sidotApp.geuRaccordement.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(geuRaccordementComponentsPage.entities), ec.visibilityOf(geuRaccordementComponentsPage.noResult)),
      1000
    );
  });

  it('should load create GeuRaccordement page', async () => {
    await geuRaccordementComponentsPage.clickOnCreateButton();
    geuRaccordementUpdatePage = new GeuRaccordementUpdatePage();
    expect(await geuRaccordementUpdatePage.getPageTitle()).to.eq('sidotApp.geuRaccordement.home.createOrEditLabel');
    await geuRaccordementUpdatePage.cancel();
  });

  it('should create and save GeuRaccordements', async () => {
    const nbButtonsBeforeCreate = await geuRaccordementComponentsPage.countDeleteButtons();

    await geuRaccordementComponentsPage.clickOnCreateButton();

    await promise.all([
      geuRaccordementUpdatePage.setNumAbonnementInput('5'),
      geuRaccordementUpdatePage.setNomInput('nom'),
      geuRaccordementUpdatePage.setPrenomInput('prenom'),
      geuRaccordementUpdatePage.setAdresseInput('adresse'),
      geuRaccordementUpdatePage.setProprietaireParacelleInput('proprietaireParacelle'),
      geuRaccordementUpdatePage.setEntrepriseInput('entreprise'),
      geuRaccordementUpdatePage.setAutreUsageInput('autreUsage'),
      geuRaccordementUpdatePage.geoparcelleSelectLastOption(),
      geuRaccordementUpdatePage.agentSelectLastOption(),
      geuRaccordementUpdatePage.tacheronsSelectLastOption(),
      geuRaccordementUpdatePage.geuusageSelectLastOption(),
    ]);

    expect(await geuRaccordementUpdatePage.getNumAbonnementInput()).to.eq('5', 'Expected numAbonnement value to be equals to 5');
    expect(await geuRaccordementUpdatePage.getNomInput()).to.eq('nom', 'Expected Nom value to be equals to nom');
    expect(await geuRaccordementUpdatePage.getPrenomInput()).to.eq('prenom', 'Expected Prenom value to be equals to prenom');
    expect(await geuRaccordementUpdatePage.getAdresseInput()).to.eq('adresse', 'Expected Adresse value to be equals to adresse');
    expect(await geuRaccordementUpdatePage.getProprietaireParacelleInput()).to.eq(
      'proprietaireParacelle',
      'Expected ProprietaireParacelle value to be equals to proprietaireParacelle'
    );
    expect(await geuRaccordementUpdatePage.getEntrepriseInput()).to.eq(
      'entreprise',
      'Expected Entreprise value to be equals to entreprise'
    );
    expect(await geuRaccordementUpdatePage.getAutreUsageInput()).to.eq(
      'autreUsage',
      'Expected AutreUsage value to be equals to autreUsage'
    );

    await geuRaccordementUpdatePage.save();
    expect(await geuRaccordementUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geuRaccordementComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last GeuRaccordement', async () => {
    const nbButtonsBeforeDelete = await geuRaccordementComponentsPage.countDeleteButtons();
    await geuRaccordementComponentsPage.clickOnLastDeleteButton();

    geuRaccordementDeleteDialog = new GeuRaccordementDeleteDialog();
    expect(await geuRaccordementDeleteDialog.getDialogTitle()).to.eq('sidotApp.geuRaccordement.delete.question');
    await geuRaccordementDeleteDialog.clickOnConfirmButton();

    expect(await geuRaccordementComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
