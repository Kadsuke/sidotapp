import { element, by, ElementFinder } from 'protractor';

export class AnalyseParametreComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-analyse-parametre div table .btn-danger'));
  title = element.all(by.css('jhi-analyse-parametre div h2#page-heading span')).first();
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

export class AnalyseParametreUpdatePage {
  pageTitle = element(by.id('jhi-analyse-parametre-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  libelleInput = element(by.id('field_libelle'));

  analysespecialiteSelect = element(by.id('field_analysespecialite'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setLibelleInput(libelle: string): Promise<void> {
    await this.libelleInput.sendKeys(libelle);
  }

  async getLibelleInput(): Promise<string> {
    return await this.libelleInput.getAttribute('value');
  }

  async analysespecialiteSelectLastOption(): Promise<void> {
    await this.analysespecialiteSelect.all(by.tagName('option')).last().click();
  }

  async analysespecialiteSelectOption(option: string): Promise<void> {
    await this.analysespecialiteSelect.sendKeys(option);
  }

  getAnalysespecialiteSelect(): ElementFinder {
    return this.analysespecialiteSelect;
  }

  async getAnalysespecialiteSelectedOption(): Promise<string> {
    return await this.analysespecialiteSelect.element(by.css('option:checked')).getText();
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

export class AnalyseParametreDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-analyseParametre-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-analyseParametre'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
