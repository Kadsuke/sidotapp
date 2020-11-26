import { element, by, ElementFinder } from 'protractor';

export class GeoCommuneComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-geo-commune div table .btn-danger'));
  title = element.all(by.css('jhi-geo-commune div h2#page-heading span')).first();
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

export class GeoCommuneUpdatePage {
  pageTitle = element(by.id('jhi-geo-commune-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  libelleInput = element(by.id('field_libelle'));

  geoprovinceSelect = element(by.id('field_geoprovince'));
  geotypecommuneSelect = element(by.id('field_geotypecommune'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setLibelleInput(libelle: string): Promise<void> {
    await this.libelleInput.sendKeys(libelle);
  }

  async getLibelleInput(): Promise<string> {
    return await this.libelleInput.getAttribute('value');
  }

  async geoprovinceSelectLastOption(): Promise<void> {
    await this.geoprovinceSelect.all(by.tagName('option')).last().click();
  }

  async geoprovinceSelectOption(option: string): Promise<void> {
    await this.geoprovinceSelect.sendKeys(option);
  }

  getGeoprovinceSelect(): ElementFinder {
    return this.geoprovinceSelect;
  }

  async getGeoprovinceSelectedOption(): Promise<string> {
    return await this.geoprovinceSelect.element(by.css('option:checked')).getText();
  }

  async geotypecommuneSelectLastOption(): Promise<void> {
    await this.geotypecommuneSelect.all(by.tagName('option')).last().click();
  }

  async geotypecommuneSelectOption(option: string): Promise<void> {
    await this.geotypecommuneSelect.sendKeys(option);
  }

  getGeotypecommuneSelect(): ElementFinder {
    return this.geotypecommuneSelect;
  }

  async getGeotypecommuneSelectedOption(): Promise<string> {
    return await this.geotypecommuneSelect.element(by.css('option:checked')).getText();
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

export class GeoCommuneDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-geoCommune-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-geoCommune'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
