import { element, by, ElementFinder } from 'protractor';

export class GeuPrevisionSTBVComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-geu-prevision-stbv div table .btn-danger'));
  title = element.all(by.css('jhi-geu-prevision-stbv div h2#page-heading span')).first();
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

export class GeuPrevisionSTBVUpdatePage {
  pageTitle = element(by.id('jhi-geu-prevision-stbv-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nbCamionsInput = element(by.id('field_nbCamions'));
  volumeInput = element(by.id('field_volume'));
  energieInput = element(by.id('field_energie'));

  geustbvSelect = element(by.id('field_geustbv'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNbCamionsInput(nbCamions: string): Promise<void> {
    await this.nbCamionsInput.sendKeys(nbCamions);
  }

  async getNbCamionsInput(): Promise<string> {
    return await this.nbCamionsInput.getAttribute('value');
  }

  async setVolumeInput(volume: string): Promise<void> {
    await this.volumeInput.sendKeys(volume);
  }

  async getVolumeInput(): Promise<string> {
    return await this.volumeInput.getAttribute('value');
  }

  async setEnergieInput(energie: string): Promise<void> {
    await this.energieInput.sendKeys(energie);
  }

  async getEnergieInput(): Promise<string> {
    return await this.energieInput.getAttribute('value');
  }

  async geustbvSelectLastOption(): Promise<void> {
    await this.geustbvSelect.all(by.tagName('option')).last().click();
  }

  async geustbvSelectOption(option: string): Promise<void> {
    await this.geustbvSelect.sendKeys(option);
  }

  getGeustbvSelect(): ElementFinder {
    return this.geustbvSelect;
  }

  async getGeustbvSelectedOption(): Promise<string> {
    return await this.geustbvSelect.element(by.css('option:checked')).getText();
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

export class GeuPrevisionSTBVDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-geuPrevisionSTBV-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-geuPrevisionSTBV'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
