import { element, by, ElementFinder } from 'protractor';

export class GeuRaccordementComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-geu-raccordement div table .btn-danger'));
  title = element.all(by.css('jhi-geu-raccordement div h2#page-heading span')).first();
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

export class GeuRaccordementUpdatePage {
  pageTitle = element(by.id('jhi-geu-raccordement-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  numAbonnementInput = element(by.id('field_numAbonnement'));
  nomInput = element(by.id('field_nom'));
  prenomInput = element(by.id('field_prenom'));
  adresseInput = element(by.id('field_adresse'));
  proprietaireParacelleInput = element(by.id('field_proprietaireParacelle'));
  entrepriseInput = element(by.id('field_entreprise'));
  otherUsageInput = element(by.id('field_otherUsage'));

  geoparcelleSelect = element(by.id('field_geoparcelle'));
  agentSelect = element(by.id('field_agent'));
  tacheronsSelect = element(by.id('field_tacherons'));
  geuusageSelect = element(by.id('field_geuusage'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNumAbonnementInput(numAbonnement: string): Promise<void> {
    await this.numAbonnementInput.sendKeys(numAbonnement);
  }

  async getNumAbonnementInput(): Promise<string> {
    return await this.numAbonnementInput.getAttribute('value');
  }

  async setNomInput(nom: string): Promise<void> {
    await this.nomInput.sendKeys(nom);
  }

  async getNomInput(): Promise<string> {
    return await this.nomInput.getAttribute('value');
  }

  async setPrenomInput(prenom: string): Promise<void> {
    await this.prenomInput.sendKeys(prenom);
  }

  async getPrenomInput(): Promise<string> {
    return await this.prenomInput.getAttribute('value');
  }

  async setAdresseInput(adresse: string): Promise<void> {
    await this.adresseInput.sendKeys(adresse);
  }

  async getAdresseInput(): Promise<string> {
    return await this.adresseInput.getAttribute('value');
  }

  async setProprietaireParacelleInput(proprietaireParacelle: string): Promise<void> {
    await this.proprietaireParacelleInput.sendKeys(proprietaireParacelle);
  }

  async getProprietaireParacelleInput(): Promise<string> {
    return await this.proprietaireParacelleInput.getAttribute('value');
  }

  async setEntrepriseInput(entreprise: string): Promise<void> {
    await this.entrepriseInput.sendKeys(entreprise);
  }

  async getEntrepriseInput(): Promise<string> {
    return await this.entrepriseInput.getAttribute('value');
  }

  async setOtherUsageInput(otherUsage: string): Promise<void> {
    await this.otherUsageInput.sendKeys(otherUsage);
  }

  async getOtherUsageInput(): Promise<string> {
    return await this.otherUsageInput.getAttribute('value');
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

  async agentSelectLastOption(): Promise<void> {
    await this.agentSelect.all(by.tagName('option')).last().click();
  }

  async agentSelectOption(option: string): Promise<void> {
    await this.agentSelect.sendKeys(option);
  }

  getAgentSelect(): ElementFinder {
    return this.agentSelect;
  }

  async getAgentSelectedOption(): Promise<string> {
    return await this.agentSelect.element(by.css('option:checked')).getText();
  }

  async tacheronsSelectLastOption(): Promise<void> {
    await this.tacheronsSelect.all(by.tagName('option')).last().click();
  }

  async tacheronsSelectOption(option: string): Promise<void> {
    await this.tacheronsSelect.sendKeys(option);
  }

  getTacheronsSelect(): ElementFinder {
    return this.tacheronsSelect;
  }

  async getTacheronsSelectedOption(): Promise<string> {
    return await this.tacheronsSelect.element(by.css('option:checked')).getText();
  }

  async geuusageSelectLastOption(): Promise<void> {
    await this.geuusageSelect.all(by.tagName('option')).last().click();
  }

  async geuusageSelectOption(option: string): Promise<void> {
    await this.geuusageSelect.sendKeys(option);
  }

  getGeuusageSelect(): ElementFinder {
    return this.geuusageSelect;
  }

  async getGeuusageSelectedOption(): Promise<string> {
    return await this.geuusageSelect.element(by.css('option:checked')).getText();
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

export class GeuRaccordementDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-geuRaccordement-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-geuRaccordement'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
