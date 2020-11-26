import { element, by, ElementFinder } from 'protractor';

export class TypeOuvrageComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-type-ouvrage div table .btn-danger'));
  title = element.all(by.css('jhi-type-ouvrage div h2#page-heading span')).first();
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

export class TypeOuvrageUpdatePage {
  pageTitle = element(by.id('jhi-type-ouvrage-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  libelleInput = element(by.id('field_libelle'));

  categorieressourcesSelect = element(by.id('field_categorieressources'));
  caracteristiqueouvrageSelect = element(by.id('field_caracteristiqueouvrage'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setLibelleInput(libelle: string): Promise<void> {
    await this.libelleInput.sendKeys(libelle);
  }

  async getLibelleInput(): Promise<string> {
    return await this.libelleInput.getAttribute('value');
  }

  async categorieressourcesSelectLastOption(): Promise<void> {
    await this.categorieressourcesSelect.all(by.tagName('option')).last().click();
  }

  async categorieressourcesSelectOption(option: string): Promise<void> {
    await this.categorieressourcesSelect.sendKeys(option);
  }

  getCategorieressourcesSelect(): ElementFinder {
    return this.categorieressourcesSelect;
  }

  async getCategorieressourcesSelectedOption(): Promise<string> {
    return await this.categorieressourcesSelect.element(by.css('option:checked')).getText();
  }

  async caracteristiqueouvrageSelectLastOption(): Promise<void> {
    await this.caracteristiqueouvrageSelect.all(by.tagName('option')).last().click();
  }

  async caracteristiqueouvrageSelectOption(option: string): Promise<void> {
    await this.caracteristiqueouvrageSelect.sendKeys(option);
  }

  getCaracteristiqueouvrageSelect(): ElementFinder {
    return this.caracteristiqueouvrageSelect;
  }

  async getCaracteristiqueouvrageSelectedOption(): Promise<string> {
    return await this.caracteristiqueouvrageSelect.element(by.css('option:checked')).getText();
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

export class TypeOuvrageDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-typeOuvrage-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-typeOuvrage'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
