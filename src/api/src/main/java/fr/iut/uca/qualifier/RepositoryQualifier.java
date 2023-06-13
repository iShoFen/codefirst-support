package fr.iut.uca.qualifier;

import jakarta.inject.Qualifier;

import java.lang.annotation.Retention;

@Qualifier
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
public @interface RepositoryQualifier {
    RepositoryType value();
}
