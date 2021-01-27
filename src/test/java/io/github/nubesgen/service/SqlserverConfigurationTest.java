package io.github.nubesgen.service;

import io.github.nubesgen.configuration.ConfigurationSize;
import io.github.nubesgen.configuration.DatabaseConfiguration;
import io.github.nubesgen.configuration.DatabaseType;
import io.github.nubesgen.configuration.NubesgenConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests NubesGen with the App Service + Azure SQL options.
 */
@SpringBootTest
class SqlserverConfigurationTest {

    private final Logger log = LoggerFactory.getLogger(SqlserverConfigurationTest.class);
    private static final NubesgenConfiguration properties = new NubesgenConfiguration();
    private final CodeGeneratorService codeGeneratorService;
    private final TemplateListService templateListService;

    @Autowired
    public SqlserverConfigurationTest(CodeGeneratorService codeGeneratorService, TemplateListService templateListService) {
        this.codeGeneratorService = codeGeneratorService;
        this.templateListService = templateListService;
        properties.setDatabaseConfiguration(new DatabaseConfiguration(DatabaseType.SQL_SERVER, ConfigurationSize.BASIC));
    }

    @BeforeAll
    public static void init() {
        properties.setApplicationName("nubesgen-testapp-sql-server");
        properties.setRegion("westeurope");
    }

    @Test
    void generateMysqlConfiguration() throws IOException {
        Map<String, String> configuration = this.codeGeneratorService.generateAzureConfiguration(properties);
        int templatesSize = this.templateListService.listMainTemplates().size() +
                this.templateListService.listAppServiceTemplates().size() +
                this.templateListService.listSqlServerTemplates().size();

        assertEquals(templatesSize, configuration.size());
        for (String filename : templateListService.listMainTemplates()) {
            this.generateAndTestOneFile(filename);
        }
        for (String filename : templateListService.listAppServiceTemplates()) {
            this.generateAndTestOneFile(filename);
        }
        for (String filename : templateListService.listSqlServerTemplates()) {
            this.generateAndTestOneFile(filename);
        }
    }

    private void generateAndTestOneFile(String filename) throws IOException {
        log.info("Validating {}", filename);
        String result = this.codeGeneratorService.generateFile(filename, properties);
        File testFile = new ClassPathResource("nubesgen/sql-server/" + filename).getFile();
        String test = new String(
                Files.readAllBytes(testFile.toPath()));

        assertEquals(test, result);
    }
}