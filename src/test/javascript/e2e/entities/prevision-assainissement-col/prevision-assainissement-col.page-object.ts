import { element, by, ElementFinder } from 'protractor';

export class PrevisionAssainissementColComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-prevision-assainissement-col div table .btn-danger'));
  title = element.all(by.css('jhi-prevision-assainissement-col div h2#page-heading span')).first();
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

export class PrevisionAssainissementColUpdatePage {
  pageTitle = element(by.id('jhi-prevision-assainissement-col-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nbStepInput = element(by.id('field_nbStep'));
  nbStbvInput = element(by.id('field_nbStbv'));
  reseauxInput = element(by.id('field_reseaux'));
  nbRaccordementInput = element(by.id('field_nbRaccordement'));

  refanneeSelect = element(by.id('field_refannee'));
  centreSelect = element(by.id('field_centre'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNbStepInput(nbStep: string): Promise<void> {
    await this.nbStepInput.sendKeys(nbStep);
  }

  async getNbStepInput(): Promise<string> {
    return await this.nbStepInput.getAttribute('value');
  }

  async setNbStbvInput(nbStbv: string): Promise<void> {
    await this.nbStbvInput.sendKeys(nbStbv);
  }

  async getNbStbvInput(): Promise<string> {
    return await this.nbStbvInput.getAttribute('value');
  }

  async setReseauxInput(reseaux: string): Promise<void> {
    await this.reseauxInput.sendKeys(reseaux);
  }

  async getReseauxInput(): Promise<string> {
    return await this.reseauxInput.getAttribute('value');
  }

  async setNbRaccordementInput(nbRaccordement: string): Promise<void> {
    await this.nbRaccordementInput.sendKeys(nbRaccordement);
  }

  async getNbRaccordementInput(): Promise<string> {
    return await this.nbRaccordementInput.getAttribute('value');
  }

  async refanneeSelectLastOption(): Promise<void> {
    await this.refanneeSelect.all(by.tagName('option')).last().click();
  }

  async refanneeSelectOption(option: string): Promise<void> {
    await this.refanneeSelect.sendKeys(option);
  }

  getRefanneeSelect(): ElementFinder {
    return this.refanneeSelect;
  }

  async getRefanneeSelectedOption(): Promise<string> {
    return await this.refanneeSelect.element(by.css('option:checked')).getText();
  }

  async centreSelectLastOption(): Promise<void> {
    await this.centreSelect.all(by.tagName('option')).last().click();
  }

  async centreSelectOption(option: string): Promise<void> {
    await this.centreSelect.sendKeys(option);
  }

  getCentreSelect(): ElementFinder {
    return this.centreSelect;
  }

  async getCentreSelectedOption(): Promise<string> {
    return await this.centreSelect.element(by.css('option:checked')).getText();
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

export class PrevisionAssainissementColDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-previsionAssainissementCol-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-previsionAssainissementCol'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
