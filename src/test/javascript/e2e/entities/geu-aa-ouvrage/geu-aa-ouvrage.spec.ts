import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { GeuAaOuvrageComponentsPage, GeuAaOuvrageDeleteDialog, GeuAaOuvrageUpdatePage } from './geu-aa-ouvrage.page-object';

const expect = chai.expect;

describe('GeuAaOuvrage e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let geuAaOuvrageComponentsPage: GeuAaOuvrageComponentsPage;
  let geuAaOuvrageUpdatePage: GeuAaOuvrageUpdatePage;
  let geuAaOuvrageDeleteDialog: GeuAaOuvrageDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load GeuAaOuvrages', async () => {
    await navBarPage.goToEntity('geu-aa-ouvrage');
    geuAaOuvrageComponentsPage = new GeuAaOuvrageComponentsPage();
    await browser.wait(ec.visibilityOf(geuAaOuvrageComponentsPage.title), 5000);
    expect(await geuAaOuvrageComponentsPage.getTitle()).to.eq('sidotApp.geuAaOuvrage.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(geuAaOuvrageComponentsPage.entities), ec.visibilityOf(geuAaOuvrageComponentsPage.noResult)),
      1000
    );
  });

  it('should load create GeuAaOuvrage page', async () => {
    await geuAaOuvrageComponentsPage.clickOnCreateButton();
    geuAaOuvrageUpdatePage = new GeuAaOuvrageUpdatePage();
    expect(await geuAaOuvrageUpdatePage.getPageTitle()).to.eq('sidotApp.geuAaOuvrage.home.createOrEditLabel');
    await geuAaOuvrageUpdatePage.cancel();
  });

  it('should create and save GeuAaOuvrages', async () => {
    const nbButtonsBeforeCreate = await geuAaOuvrageComponentsPage.countDeleteButtons();

    await geuAaOuvrageComponentsPage.clickOnCreateButton();

    await promise.all([
      geuAaOuvrageUpdatePage.setRefOuvrageInput('refOuvrage'),
      geuAaOuvrageUpdatePage.setPrjAppuisInput('prjAppuis'),
      geuAaOuvrageUpdatePage.setNumCompteurInput('numCompteur'),
      geuAaOuvrageUpdatePage.setNomBenefInput('nomBenef'),
      geuAaOuvrageUpdatePage.setPrenomBenefInput('prenomBenef'),
      geuAaOuvrageUpdatePage.setProfessionBenefInput('professionBenef'),
      geuAaOuvrageUpdatePage.setNbUsagersInput('5'),
      geuAaOuvrageUpdatePage.setCodeUnInput('codeUn'),
      geuAaOuvrageUpdatePage.setDateRemiseDevisInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      geuAaOuvrageUpdatePage.setDateDebutTravauxInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      geuAaOuvrageUpdatePage.setDateFinTravauxInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      geuAaOuvrageUpdatePage.setNumNomRueInput('numNomRue'),
      geuAaOuvrageUpdatePage.setNumNomPorteInput('numNomPorte'),
      geuAaOuvrageUpdatePage.setMenageInput('menage'),
      geuAaOuvrageUpdatePage.setSubvOneaInput('5'),
      geuAaOuvrageUpdatePage.setSubvProjetInput('5'),
      geuAaOuvrageUpdatePage.setAutreSubvInput('5'),
      geuAaOuvrageUpdatePage.setRefBonFournitureInput('refBonFourniture'),
      geuAaOuvrageUpdatePage.setRealisPorteInput('5'),
      geuAaOuvrageUpdatePage.setRealisTolesInput('5'),
      geuAaOuvrageUpdatePage.setAnimateurInput('animateur'),
      geuAaOuvrageUpdatePage.setSuperviseurInput('superviseur'),
      geuAaOuvrageUpdatePage.setControleurInput('controleur'),
      geuAaOuvrageUpdatePage.geoparcelleSelectLastOption(),
      geuAaOuvrageUpdatePage.natureouvrageSelectLastOption(),
      geuAaOuvrageUpdatePage.typehabitationSelectLastOption(),
      geuAaOuvrageUpdatePage.sourceapprovepSelectLastOption(),
      geuAaOuvrageUpdatePage.modeevacuationeauuseeSelectLastOption(),
      geuAaOuvrageUpdatePage.modeevacexcretaSelectLastOption(),
      geuAaOuvrageUpdatePage.maconSelectLastOption(),
      geuAaOuvrageUpdatePage.prefabricantSelectLastOption(),
    ]);

    expect(await geuAaOuvrageUpdatePage.getRefOuvrageInput()).to.eq('refOuvrage', 'Expected RefOuvrage value to be equals to refOuvrage');
    expect(await geuAaOuvrageUpdatePage.getPrjAppuisInput()).to.eq('prjAppuis', 'Expected PrjAppuis value to be equals to prjAppuis');
    expect(await geuAaOuvrageUpdatePage.getNumCompteurInput()).to.eq(
      'numCompteur',
      'Expected NumCompteur value to be equals to numCompteur'
    );
    expect(await geuAaOuvrageUpdatePage.getNomBenefInput()).to.eq('nomBenef', 'Expected NomBenef value to be equals to nomBenef');
    expect(await geuAaOuvrageUpdatePage.getPrenomBenefInput()).to.eq(
      'prenomBenef',
      'Expected PrenomBenef value to be equals to prenomBenef'
    );
    expect(await geuAaOuvrageUpdatePage.getProfessionBenefInput()).to.eq(
      'professionBenef',
      'Expected ProfessionBenef value to be equals to professionBenef'
    );
    expect(await geuAaOuvrageUpdatePage.getNbUsagersInput()).to.eq('5', 'Expected nbUsagers value to be equals to 5');
    expect(await geuAaOuvrageUpdatePage.getCodeUnInput()).to.eq('codeUn', 'Expected CodeUn value to be equals to codeUn');
    expect(await geuAaOuvrageUpdatePage.getDateRemiseDevisInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateRemiseDevis value to be equals to 2000-12-31'
    );
    expect(await geuAaOuvrageUpdatePage.getDateDebutTravauxInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateDebutTravaux value to be equals to 2000-12-31'
    );
    expect(await geuAaOuvrageUpdatePage.getDateFinTravauxInput()).to.contain(
      '2001-01-01T02:30',
      'Expected dateFinTravaux value to be equals to 2000-12-31'
    );
    expect(await geuAaOuvrageUpdatePage.getNumNomRueInput()).to.eq('numNomRue', 'Expected NumNomRue value to be equals to numNomRue');
    expect(await geuAaOuvrageUpdatePage.getNumNomPorteInput()).to.eq(
      'numNomPorte',
      'Expected NumNomPorte value to be equals to numNomPorte'
    );
    expect(await geuAaOuvrageUpdatePage.getMenageInput()).to.eq('menage', 'Expected Menage value to be equals to menage');
    expect(await geuAaOuvrageUpdatePage.getSubvOneaInput()).to.eq('5', 'Expected subvOnea value to be equals to 5');
    expect(await geuAaOuvrageUpdatePage.getSubvProjetInput()).to.eq('5', 'Expected subvProjet value to be equals to 5');
    expect(await geuAaOuvrageUpdatePage.getAutreSubvInput()).to.eq('5', 'Expected autreSubv value to be equals to 5');
    expect(await geuAaOuvrageUpdatePage.getRefBonFournitureInput()).to.eq(
      'refBonFourniture',
      'Expected RefBonFourniture value to be equals to refBonFourniture'
    );
    expect(await geuAaOuvrageUpdatePage.getRealisPorteInput()).to.eq('5', 'Expected realisPorte value to be equals to 5');
    expect(await geuAaOuvrageUpdatePage.getRealisTolesInput()).to.eq('5', 'Expected realisToles value to be equals to 5');
    expect(await geuAaOuvrageUpdatePage.getAnimateurInput()).to.eq('animateur', 'Expected Animateur value to be equals to animateur');
    expect(await geuAaOuvrageUpdatePage.getSuperviseurInput()).to.eq(
      'superviseur',
      'Expected Superviseur value to be equals to superviseur'
    );
    expect(await geuAaOuvrageUpdatePage.getControleurInput()).to.eq('controleur', 'Expected Controleur value to be equals to controleur');

    await geuAaOuvrageUpdatePage.save();
    expect(await geuAaOuvrageUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await geuAaOuvrageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last GeuAaOuvrage', async () => {
    const nbButtonsBeforeDelete = await geuAaOuvrageComponentsPage.countDeleteButtons();
    await geuAaOuvrageComponentsPage.clickOnLastDeleteButton();

    geuAaOuvrageDeleteDialog = new GeuAaOuvrageDeleteDialog();
    expect(await geuAaOuvrageDeleteDialog.getDialogTitle()).to.eq('sidotApp.geuAaOuvrage.delete.question');
    await geuAaOuvrageDeleteDialog.clickOnConfirmButton();

    expect(await geuAaOuvrageComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
