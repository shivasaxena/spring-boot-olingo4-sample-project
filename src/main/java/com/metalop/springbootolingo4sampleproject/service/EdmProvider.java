package com.metalop.springbootolingo4sampleproject.service;

import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.provider.*;
import org.apache.olingo.commons.api.ex.ODataException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.metalop.springbootolingo4sampleproject.util.Constants;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("edmProvider")
public class EdmProvider extends CsdlAbstractEdmProvider {

    public EdmProvider() {
        super();
    }

    @Override
    public CsdlEnumType getEnumType(FullQualifiedName enumTypeName) throws ODataException {
        return super.getEnumType(enumTypeName);
    }

    @Override
    public CsdlTypeDefinition getTypeDefinition(FullQualifiedName typeDefinitionName) throws ODataException {
        return super.getTypeDefinition(typeDefinitionName);
    }

    @Override
    public CsdlEntityType getEntityType(FullQualifiedName entityTypeName) throws ODataException {
        if (entityTypeName.equals(Constants.ENTITY_TYPE_STUDENT_FQN)) {

            // create EntityType properties
            CsdlProperty id = new CsdlProperty().setName("StudentID")
                    .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

            CsdlProperty name = new CsdlProperty().setName("Name")
                    .setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

            CsdlProperty dateOfJoining = new CsdlProperty().setName("DateOfJoining")
                    .setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());

            CsdlProperty marks = new CsdlProperty().setName("Marks")
                    .setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());

            CsdlProperty picture = new CsdlProperty().setName("Picture")
                    .setType(EdmPrimitiveTypeKind.Binary.getFullQualifiedName());

            CsdlProperty longTextDescription = new CsdlProperty().setName("LongTextDescription")
                    .setType(EdmPrimitiveTypeKind.DateTimeOffset.getFullQualifiedName());

            // create CsdlPropertyRef for Key element
            CsdlPropertyRef propertyRef = new CsdlPropertyRef();
            propertyRef.setName("ID");

            // configure EntityType
            CsdlEntityType entityType = new CsdlEntityType();
            entityType.setName(Constants.ENTITY_TYPE_STUDENT_NAME);
            entityType.setProperties(Arrays.asList(id, name, dateOfJoining, marks, picture, longTextDescription));
            entityType.setKey(Collections.singletonList(propertyRef));

            return entityType;
        }

        return null;
    }


    @Override
    public CsdlComplexType getComplexType(FullQualifiedName complexTypeName) throws ODataException {
        return super.getComplexType(complexTypeName);
    }

    @Override
    public List<CsdlAction> getActions(FullQualifiedName actionName) throws ODataException {
        return super.getActions(actionName);
    }

    @Override
    public List<CsdlFunction> getFunctions(FullQualifiedName functionName) throws ODataException {
        return super.getFunctions(functionName);
    }

    @Override
    public CsdlTerm getTerm(FullQualifiedName termName) throws ODataException {
        return super.getTerm(termName);
    }

    @Override
    public CsdlEntitySet getEntitySet(FullQualifiedName entityContainer, String entitySetName) throws ODataException {
        if (entityContainer.equals(Constants.CONTAINER) && entitySetName.equals(Constants.ENTITY_SET_STUDENTS_NAME)) {
            CsdlEntitySet entitySet = new CsdlEntitySet();
            entitySet.setName(Constants.ENTITY_SET_STUDENTS_NAME);
            entitySet.setType(Constants.ENTITY_TYPE_STUDENT_FQN);

            return entitySet;
        }

        return null;
    }

    @Override
    public CsdlSingleton getSingleton(FullQualifiedName entityContainer, String singletonName) throws ODataException {
        return super.getSingleton(entityContainer, singletonName);
    }

    @Override
    public CsdlActionImport getActionImport(FullQualifiedName entityContainer, String actionImportName) throws ODataException {
        return super.getActionImport(entityContainer, actionImportName);
    }

    @Override
    public CsdlFunctionImport getFunctionImport(FullQualifiedName entityContainer, String functionImportName) throws ODataException {
        return super.getFunctionImport(entityContainer, functionImportName);
    }

    @Override
    public CsdlEntityContainerInfo getEntityContainerInfo(FullQualifiedName entityContainerName) throws ODataException {
        if (entityContainerName == null || entityContainerName.equals(Constants.CONTAINER)) {
            CsdlEntityContainerInfo entityContainerInfo = new CsdlEntityContainerInfo();
            entityContainerInfo.setContainerName(Constants.CONTAINER);
            return entityContainerInfo;
        }
        return null;
    }

    @Override
    public List<CsdlAliasInfo> getAliasInfos() throws ODataException {
        return super.getAliasInfos();
    }

    @Override
    public List<CsdlSchema> getSchemas() throws ODataException {

        // create Schema
        CsdlSchema schema = new CsdlSchema();
        schema.setNamespace(Constants.NAMESPACE);

        // add EntityTypes
        List<CsdlEntityType> entityTypes = new ArrayList<>();
        entityTypes.add(getEntityType(Constants.ENTITY_TYPE_STUDENT_FQN));
        schema.setEntityTypes(entityTypes);

        // add EntityContainer
        schema.setEntityContainer(getEntityContainer());

        // Add to Schema
        List<CsdlSchema> schemas = new ArrayList<>();
        schemas.add(schema);

        return schemas;
    }

    @Override
    public CsdlEntityContainer getEntityContainer() throws ODataException {
        // create EntitySets
        List<CsdlEntitySet> entitySets = new ArrayList<>();
        entitySets.add(getEntitySet(Constants.CONTAINER, Constants.ENTITY_SET_STUDENTS_NAME));

        // create EntityContainer
        CsdlEntityContainer entityContainer = new CsdlEntityContainer();
        entityContainer.setName(Constants.CONTAINER_NAME);
        entityContainer.setEntitySets(entitySets);

        return entityContainer;
    }

    @Override
    public CsdlAnnotations getAnnotationsGroup(FullQualifiedName targetName, String qualifier) throws ODataException {
        return super.getAnnotationsGroup(targetName, qualifier);
    }
}
