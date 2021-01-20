terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = ">= 2.42"
    }
  }
}

provider "azurerm" {
  features {}
}

resource "azurerm_resource_group" "main" {
  name     = var.resource_group
  location = var.location
  tags = {
    "terraform" = "true"
  }
}

module "compute" {
  source            = "./modules/app-service"
  resource_group    = var.resource_group
  location          = var.location
  application_name  = var.application_name

  azure_storage_account_name  = module.storage-blob.azurerm_storage_account_name
  azure_storage_account_key   = module.storage-blob.azurerm_storage_account_key
  azure_storage_blob_endpoint = module.storage-blob.azurerm_storage_blob_endpoint

  depends_on = [
    module.storage-blob,
    azurerm_resource_group.main
  ]
}

module "storage-blob" {
  source           = "./modules/storage-blob"
  resource_group   = var.resource_group
  location         = var.location
  application_name = var.application_name
  depends_on = [
    azurerm_resource_group.main
  ]
}
