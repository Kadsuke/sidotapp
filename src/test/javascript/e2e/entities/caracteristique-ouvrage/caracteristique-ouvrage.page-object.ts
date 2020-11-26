import { element, by, ElementFinder } from 'protractor';

export class CaracteristiqueOuvrageComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-caracteristique-ouvrage div table .btn-danger'));
  title = element.all(by.css('jhi-caracteristique-ouvrage div h2#page-heading span')).first();
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

export class CaracteristiqueOuvrageUpdatePage {
  pageTitle = element(by.id('jhi-caracteristique-ouvrage-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  libelleInput = element(by.id('field_libelle'));
  uniteInput = element(by.id('field_unite'));

  typeouvrageSelect = element(by.id('field_typeouvrage'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setLibelleInput(libelle: string): Promise<void> {
    await this.libelleInput.sendKeys(libelle);
  }

  async getLibelleInput(): Promise<string> {
    return await this.libelleInput.getAttribute('value');
  }

  async setUniteInput(unite: string): Promise<void> {
    await this.uniteInput.sendKeys(unite);
  }

  async getUniteInput(): Promise<string> {
    return await this.uniteInput.getAttribute('value');
  }

  async typeouvrageSelectLastOption(): Promise<void> {
    await this.typeouvrageSelect.all(by.tagName('option')).last().click();
  }

  async typeouvrageSelectOption(option: string): Promise<void> {
    await this.typeouvrageSelect.sendKeys(option);
  }

  getTypeouvrageSelect(): ElementFinder {
    return this.typeouvrageSelect;
  }

  async getTypeouvrageSelectedOption(): Promise<string> {
    return await this.typeouvrageSelect.element(by.css('option:checked')).getText();
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

export class CaracteristiqueOuvrageDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-caracteristiqueOuvrage-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-caracteristiqueOuvrage'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
