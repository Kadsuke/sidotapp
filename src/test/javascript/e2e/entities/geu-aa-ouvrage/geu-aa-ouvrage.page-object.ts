import { element, by, ElementFinder } from 'protractor';

export class GeuAaOuvrageComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-geu-aa-ouvrage div table .btn-danger'));
  title = element.all(by.css('jhi-geu-aa-ouvrage div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class GeuAaOuvrageUpdatePage {
  pageTitle = element(by.id('jhi-geu-aa-ouvrage-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  refOuvrageInput = element(by.id('field_refOuvrage'));
  prjAppuisInput = element(by.id('field_prjAppuis'));
  numCompteurInput = element(by.id('field_numCompteur'));
  nomBenefInput = element(by.id('field_nomBenef'));
  prenomBenefInput = element(by.id('field_prenomBenef'));
  professionBenefInput = element(by.id('field_professionBenef'));
  nbUsagersInput = element(by.id('field_nbUsagers'));
  codeUnInput = element(by.id('field_codeUn'));
  dateRemiseDevisInput = element(by.id('field_dateRemiseDevis'));
  dateDebutTravauxInput = element(by.id('field_dateDebutTravaux'));
  dateFinTravauxInput = element(by.id('field_dateFinTravaux'));
  numNomRueInput = element(by.id('field_numNomRue'));
  numNomPorteInput = element(by.id('field_numNomPorte'));
  menageInput = element(by.id('field_menage'));
  subvOneaInput = element(by.id('field_subvOnea'));
  subvProjetInput = element(by.id('field_subvProjet'));
  autreSubvInput = element(by.id('field_autreSubv'));
  refBonFournitureInput = element(by.id('field_refBonFourniture'));
  realisPorteInput = element(by.id('field_realisPorte'));
  realisTolesInput = element(by.id('field_realisToles'));
  animateurInput = element(by.id('field_animateur'));
  superviseurInput = element(by.id('field_superviseur'));
  controleurInput = element(by.id('field_controleur'));

  geoparcelleSelect = element(by.id('field_geoparcelle'));
  natureouvrageSelect = element(by.id('field_natureouvrage'));
  typehabitationSelect = element(by.id('field_typehabitation'));
  sourceapprovepSelect = element(by.id('field_sourceapprovep'));
  modeevacuationeauuseeSelect = element(by.id('field_modeevacuationeauusee'));
  modeevacexcretaSelect = element(by.id('field_modeevacexcreta'));
  maconSelect = element(by.id('field_macon'));
  prefabricantSelect = element(by.id('field_prefabricant'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setRefOuvrageInput(refOuvrage: string): Promise<void> {
    await this.refOuvrageInput.sendKeys(refOuvrage);
  }

  async getRefOuvrageInput(): Promise<string> {
    return await this.refOuvrageInput.getAttribute('value');
  }

  async setPrjAppuisInput(prjAppuis: string): Promise<void> {
    await this.prjAppuisInput.sendKeys(prjAppuis);
  }

  async getPrjAppuisInput(): Promise<string> {
    return await this.prjAppuisInput.getAttribute('value');
  }

  async setNumCompteurInput(numCompteur: string): Promise<void> {
    await this.numCompteurInput.sendKeys(numCompteur);
  }

  async getNumCompteurInput(): Promise<string> {
    return await this.numCompteurInput.getAttribute('value');
  }

  async setNomBenefInput(nomBenef: string): Promise<void> {
    await this.nomBenefInput.sendKeys(nomBenef);
  }

  async getNomBenefInput(): Promise<string> {
    return await this.nomBenefInput.getAttribute('value');
  }

  async setPrenomBenefInput(prenomBenef: string): Promise<void> {
    await this.prenomBenefInput.sendKeys(prenomBenef);
  }

  async getPrenomBenefInput(): Promise<string> {
    return await this.prenomBenefInput.getAttribute('value');
  }

  async setProfessionBenefInput(professionBenef: string): Promise<void> {
    await this.professionBenefInput.sendKeys(professionBenef);
  }

  async getProfessionBenefInput(): Promise<string> {
    return await this.professionBenefInput.getAttribute('value');
  }

  async setNbUsagersInput(nbUsagers: string): Promise<void> {
    await this.nbUsagersInput.sendKeys(nbUsagers);
  }

  async getNbUsagersInput(): Promise<string> {
    return await this.nbUsagersInput.getAttribute('value');
  }

  async setCodeUnInput(codeUn: string): Promise<void> {
    await this.codeUnInput.sendKeys(codeUn);
  }

  async getCodeUnInput(): Promise<string> {
    return await this.codeUnInput.getAttribute('value');
  }

  async setDateRemiseDevisInput(dateRemiseDevis: string): Promise<void> {
    await this.dateRemiseDevisInput.sendKeys(dateRemiseDevis);
  }

  async getDateRemiseDevisInput(): Promise<string> {
    return await this.dateRemiseDevisInput.getAttribute('value');
  }

  async setDateDebutTravauxInput(dateDebutTravaux: string): Promise<void> {
    await this.dateDebutTravauxInput.sendKeys(dateDebutTravaux);
  }

  async getDateDebutTravauxInput(): Promise<string> {
    return await this.dateDebutTravauxInput.getAttribute('value');
  }

  async setDateFinTravauxInput(dateFinTravaux: string): Promise<void> {
    await this.dateFinTravauxInput.sendKeys(dateFinTravaux);
  }

  async getDateFinTravauxInput(): Promise<string> {
    return await this.dateFinTravauxInput.getAttribute('value');
  }

  async setNumNomRueInput(numNomRue: string): Promise<void> {
    await this.numNomRueInput.sendKeys(numNomRue);
  }

  async getNumNomRueInput(): Promise<string> {
    return await this.numNomRueInput.getAttribute('value');
  }

  async setNumNomPorteInput(numNomPorte: string): Promise<void> {
    await this.numNomPorteInput.sendKeys(numNomPorte);
  }

  async getNumNomPorteInput(): Promise<string> {
    return await this.numNomPorteInput.getAttribute('value');
  }

  async setMenageInput(menage: string): Promise<void> {
    await this.menageInput.sendKeys(menage);
  }

  async getMenageInput(): Promise<string> {
    return await this.menageInput.getAttribute('value');
  }

  async setSubvOneaInput(subvOnea: string): Promise<void> {
    await this.subvOneaInput.sendKeys(subvOnea);
  }

  async getSubvOneaInput(): Promise<string> {
    return await this.subvOneaInput.getAttribute('value');
  }

  async setSubvProjetInput(subvProjet: string): Promise<void> {
    await this.subvProjetInput.sendKeys(subvProjet);
  }

  async getSubvProjetInput(): Promise<string> {
    return await this.subvProjetInput.getAttribute('value');
  }

  async setAutreSubvInput(autreSubv: string): Promise<void> {
    await this.autreSubvInput.sendKeys(autreSubv);
  }

  async getAutreSubvInput(): Promise<string> {
    return await this.autreSubvInput.getAttribute('value');
  }

  async setRefBonFournitureInput(refBonFourniture: string): Promise<void> {
    await this.refBonFournitureInput.sendKeys(refBonFourniture);
  }

  async getRefBonFournitureInput(): Promise<string> {
    return await this.refBonFournitureInput.getAttribute('value');
  }

  async setRealisPorteInput(realisPorte: string): Promise<void> {
    await this.realisPorteInput.sendKeys(realisPorte);
  }

  async getRealisPorteInput(): Promise<string> {
    return await this.realisPorteInput.getAttribute('value');
  }

  async setRealisTolesInput(realisToles: string): Promise<void> {
    await this.realisTolesInput.sendKeys(realisToles);
  }

  async getRealisTolesInput(): Promise<string> {
    return await this.realisTolesInput.getAttribute('value');
  }

  async setAnimateurInput(animateur: string): Promise<void> {
    await this.animateurInput.sendKeys(animateur);
  }

  async getAnimateurInput(): Promise<string> {
    return await this.animateurInput.getAttribute('value');
  }

  async setSuperviseurInput(superviseur: string): Promise<void> {
    await this.superviseurInput.sendKeys(superviseur);
  }

  async getSuperviseurInput(): Promise<string> {
    return await this.superviseurInput.getAttribute('value');
  }

  async setControleurInput(controleur: string): Promise<void> {
    await this.controleurInput.sendKeys(controleur);
  }

  async getControleurInput(): Promise<string> {
    return await this.controleurInput.getAttribute('value');
  }

  async geoparcelleSelectLastOption(): Promise<void> {
    await this.geoparcelleSelect.all(by.tagName('option')).last().click();
  }

  async geoparcelleSelectOption(option: string): Promise<void> {
    await this.geoparcelleSelect.sendKeys(option);
  }

  getGeoparcelleSelect(): ElementFinder {
    return this.geoparcelleSelect;
  }

  async getGeoparcelleSelectedOption(): Promise<string> {
    return await this.geoparcelleSelect.element(by.css('option:checked')).getText();
  }

  async natureouvrageSelectLastOption(): Promise<void> {
    await this.natureouvrageSelect.all(by.tagName('option')).last().click();
  }

  async natureouvrageSelectOption(option: string): Promise<void> {
    await this.natureouvrageSelect.sendKeys(option);
  }

  getNatureouvrageSelect(): ElementFinder {
    return this.natureouvrageSelect;
  }

  async getNatureouvrageSelectedOption(): Promise<string> {
    return await this.natureouvrageSelect.element(by.css('option:checked')).getText();
  }

  async typehabitationSelectLastOption(): Promise<void> {
    await this.typehabitationSelect.all(by.tagName('option')).last().click();
  }

  async typehabitationSelectOption(option: string): Promise<void> {
    await this.typehabitationSelect.sendKeys(option);
  }

  getTypehabitationSelect(): ElementFinder {
    return this.typehabitationSelect;
  }

  async getTypehabitationSelectedOption(): Promise<string> {
    return await this.typehabitationSelect.element(by.css('option:checked')).getText();
  }

  async sourceapprovepSelectLastOption(): Promise<void> {
    await this.sourceapprovepSelect.all(by.tagName('option')).last().click();
  }

  async sourceapprovepSelectOption(option: string): Promise<void> {
    await this.sourceapprovepSelect.sendKeys(option);
  }

  getSourceapprovepSelect(): ElementFinder {
    return this.sourceapprovepSelect;
  }

  async getSourceapprovepSelectedOption(): Promise<string> {
    return await this.sourceapprovepSelect.element(by.css('option:checked')).getText();
  }

  async modeevacuationeauuseeSelectLastOption(): Promise<void> {
    await this.modeevacuationeauuseeSelect.all(by.tagName('option')).last().click();
  }

  async modeevacuationeauuseeSelectOption(option: string): Promise<void> {
    await this.modeevacuationeauuseeSelect.sendKeys(option);
  }

  getModeevacuationeauuseeSelect(): ElementFinder {
    return this.modeevacuationeauuseeSelect;
  }

  async getModeevacuationeauuseeSelectedOption(): Promise<string> {
    return await this.modeevacuationeauuseeSelect.element(by.css('option:checked')).getText();
  }

  async modeevacexcretaSelectLastOption(): Promise<void> {
    await this.modeevacexcretaSelect.all(by.tagName('option')).last().click();
  }

  async modeevacexcretaSelectOption(option: string): Promise<void> {
    await this.modeevacexcretaSelect.sendKeys(option);
  }

  getModeevacexcretaSelect(): ElementFinder {
    return this.modeevacexcretaSelect;
  }

  async getModeevacexcretaSelectedOption(): Promise<string> {
    return await this.modeevacexcretaSelect.element(by.css('option:checked')).getText();
  }

  async maconSelectLastOption(): Promise<void> {
    await this.maconSelect.all(by.tagName('option')).last().click();
  }

  async maconSelectOption(option: string): Promise<void> {
    await this.maconSelect.sendKeys(option);
  }

  getMaconSelect(): ElementFinder {
    return this.maconSelect;
  }

  async getMaconSelectedOption(): Promise<string> {
    return await this.maconSelect.element(by.css('option:checked')).getText();
  }

  async prefabricantSelectLastOption(): Promise<void> {
    await this.prefabricantSelect.all(by.tagName('option')).last().click();
  }

  async prefabricantSelectOption(option: string): Promise<void> {
    await this.prefabricantSelect.sendKeys(option);
  }

  getPrefabricantSelect(): ElementFinder {
    return this.prefabricantSelect;
  }

  async getPrefabricantSelectedOption(): Promise<string> {
    return await this.prefabricantSelect.element(by.css('option:checked')).getText();
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class GeuAaOuvrageDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-geuAaOuvrage-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-geuAaOuvrage'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
