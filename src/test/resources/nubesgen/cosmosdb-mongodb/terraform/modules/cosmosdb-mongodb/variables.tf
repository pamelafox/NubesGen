variable "resource_group" {
  description = "The resource group"
  default     = ""
}

variable "application_name" {
  description = "The name of your application"
  default     = ""
}

variable "location" {
  description = "The Azure region where all resources in this example should be created"
  default     = ""
}

variable "throughput" {
  description = "The throughput of the MongoDB database (RU/s)"
  default     = "400"
}

