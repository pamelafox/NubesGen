package io.github.nubesgen.configuration;

public class NubesgenConfiguration {

    private String location;

    private String applicationName;

    private Database database;

    public NubesgenConfiguration() {
        this.location = "eastus";
        this.applicationName = "sampleNubesApplication";
        this.database = new Database();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public Database getDatabaseConfiguration() {
        return database;
    }

    public void setDatabaseConfiguration(Database database) {
        this.database = database;
    }

    public boolean isDatabaseTypeNone() {
        return DatabaseType.NONE.equals(this.database.getType());
    }

    public boolean isDatabaseTypeMysql() {
        return DatabaseType.MYSQL.equals(this.database.getType());
    }

    public boolean isDatabaseTypePostgresql() {
        return DatabaseType.POSTGRESQL.equals(this.database.getType());
    }

    @Override
    public String toString() {
        return "NubesgenConfiguration{" +
                "applicationName='" + applicationName + '\'' +
                ", location='" + location + '\'' +
                ", database=" + database +
                '}';
    }
}