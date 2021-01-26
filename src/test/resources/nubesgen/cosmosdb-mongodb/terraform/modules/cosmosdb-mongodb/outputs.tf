output "azure_cosmosdb_mongodb_database" {
  value = azurerm_cosmosdb_mongo_database.cosmosdb.name
}

output "azure_cosmosdb_mongodb_uri" {
  value     = azurerm_cosmosdb_account.cosmosdb.connection_strings[0]
  sensitive = true
}
