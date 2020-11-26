import { element, by, ElementFinder } from 'protractor';

export class GeuPrevisionSTEPComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-geu-prevision-step div table .btn-danger'));
  title = element.all(by.css('jhi-geu-prevision-step div h2#page-heading span')).first();
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

export class GeuPrevisionSTEPUpdatePage {
  pageTitle = element(by.id('jhi-geu-prevision-step-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  datePrevStepInput = element(by.id('field_datePrevStep'));
  volumePrevStepInput = element(by.id('field_volumePrevStep'));

  geustepSelect = element(by.id('field_geustep'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setDatePrevStepInput(datePrevStep: string): Promise<void> {
    await this.datePrevStepInput.sendKeys(datePrevStep);
  }

  async getDatePrevStepInput(): Promise<string> {
    return await this.datePrevStepInput.getAttribute('value');
  }

  async setVolumePrevStepInput(volumePrevStep: string): Promise<void> {
    await this.volumePrevStepInput.sendKeys(volumePrevStep);
  }

  async getVolumePrevStepInput(): Promise<string> {
    return await this.volumePrevStepInput.getAttribute('value');
  }

  async geustepSelectLastOption(): Promise<void> {
    await this.geustepSelect.all(by.tagName('option')).last().click();
  }

  async geustepSelectOption(option: string): Promise<void> {
    await this.geustepSelect.sendKeys(option);
  }

  getGeustepSelect(): ElementFinder {
    return this.geustepSelect;
  }

  async getGeustepSelectedOption(): Promise<string> {
    return await this.geustepSelect.element(by.css('option:checked')).getText();
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

export class GeuPrevisionSTEPDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-geuPrevisionSTEP-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-geuPrevisionSTEP'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
