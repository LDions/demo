package com.hzy.mydemo;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.hzy.mydemo");

        noClasses()
            .that()
            .resideInAnyPackage("com.hzy.mydemo.modules.firstversion.service..")
            .or()
            .resideInAnyPackage("com.hzy.mydemo.modules.firstversion.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.hzy.mydemo.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
