import { element, by, ElementFinder } from 'protractor';

export class PrevisionPsaComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-prevision-psa div table .btn-danger'));
  title = element.all(by.css('jhi-prevision-psa div h2#page-heading span')).first();
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

export class PrevisionPsaUpdatePage {
  pageTitle = element(by.id('jhi-prevision-psa-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  elaboreInput = element(by.id('field_elabore'));
  miseEnOeuvreInput = element(by.id('field_miseEnOeuvre'));

  centreSelect = element(by.id('field_centre'));
  refAnneeSelect = element(by.id('field_refAnnee'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setElaboreInput(elabore: string): Promise<void> {
    await this.elaboreInput.sendKeys(elabore);
  }

  async getElaboreInput(): Promise<string> {
    return await this.elaboreInput.getAttribute('value');
  }

  async setMiseEnOeuvreInput(miseEnOeuvre: string): Promise<void> {
    await this.miseEnOeuvreInput.sendKeys(miseEnOeuvre);
  }

  async getMiseEnOeuvreInput(): Promise<string> {
    return await this.miseEnOeuvreInput.getAttribute('value');
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

  async refAnneeSelectLastOption(): Promise<void> {
    await this.refAnneeSelect.all(by.tagName('option')).last().click();
  }

  async refAnneeSelectOption(option: string): Promise<void> {
    await this.refAnneeSelect.sendKeys(option);
  }

  getRefAnneeSelect(): ElementFinder {
    return this.refAnneeSelect;
  }

  async getRefAnneeSelectedOption(): Promise<string> {
    return await this.refAnneeSelect.element(by.css('option:checked')).getText();
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

export class PrevisionPsaDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-previsionPsa-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-previsionPsa'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
