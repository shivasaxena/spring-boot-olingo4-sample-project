package com.metalop.springbootolingo4sampleproject.util;

import org.apache.olingo.commons.api.edm.FullQualifiedName;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    // Service Namespace
    public static final String NAMESPACE = "OData.Demo";

    // EDM Container
    public static final String CONTAINER_NAME = "Container";
    public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

    // Entity Types Names
    public static final String ENTITY_TYPE_STUDENT_NAME = "Student";
    public static final FullQualifiedName ENTITY_TYPE_STUDENT_FQN = new FullQualifiedName(NAMESPACE, ENTITY_TYPE_STUDENT_NAME);

    // Entity Set Names
    public static final String ENTITY_SET_STUDENTS_NAME = "Students";
}
