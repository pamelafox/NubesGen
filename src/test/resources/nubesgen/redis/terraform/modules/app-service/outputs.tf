output "application_hostname" {
  value = "https://${azurerm_app_service.compute.default_site_hostname}"
}
