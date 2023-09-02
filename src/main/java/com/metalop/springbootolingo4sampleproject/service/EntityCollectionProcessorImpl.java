package com.metalop.springbootolingo4sampleproject.service;

import com.metalop.springbootolingo4sampleproject.util.Constants;
import org.apache.olingo.commons.api.data.*;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmEntityType;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.format.ContentType;
import org.apache.olingo.commons.api.http.HttpHeader;
import org.apache.olingo.commons.api.http.HttpStatusCode;
import org.apache.olingo.server.api.*;
import org.apache.olingo.server.api.processor.EntityCollectionProcessor;
import org.apache.olingo.server.api.serializer.EntityCollectionSerializerOptions;
import org.apache.olingo.server.api.serializer.ODataSerializer;
import org.apache.olingo.server.api.serializer.SerializerResult;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

@Component
public class EntityCollectionProcessorImpl implements EntityCollectionProcessor {

    private OData odata;
    private ServiceMetadata serviceMetadata;

    public void init(OData odata, ServiceMetadata serviceMetadata) {
        this.odata = odata;
        this.serviceMetadata = serviceMetadata;
    }

    public void readEntityCollection(ODataRequest request, ODataResponse response, UriInfo uriInfo,
                                     ContentType responseFormat) throws ODataApplicationException, ODataLibraryException {


        List<UriResource> resourcePaths = uriInfo.getUriResourceParts();
        UriResourceEntitySet uriResourceEntitySet = (UriResourceEntitySet) resourcePaths.get(0);
        EdmEntitySet edmEntitySet = uriResourceEntitySet.getEntitySet();


        EntityCollection entitySet = getData(edmEntitySet);


        ODataSerializer serializer = odata.createSerializer(responseFormat);


        EdmEntityType edmEntityType = edmEntitySet.getEntityType();
        ContextURL contextUrl = ContextURL.with().entitySet(edmEntitySet).build();

        final String id = request.getRawBaseUri() + "/" + edmEntitySet.getName();
        EntityCollectionSerializerOptions opts = EntityCollectionSerializerOptions.with().id(id).contextURL(contextUrl)
                .build();
        SerializerResult serializerResult = serializer.entityCollection(serviceMetadata, edmEntityType, entitySet,
                opts);
        InputStream serializedContent = serializerResult.getContent();

        // Finally: configure the response object: set the body, headers and status code
        response.setContent(serializedContent);
        response.setStatusCode(HttpStatusCode.OK.getStatusCode());
        response.setHeader(HttpHeader.CONTENT_TYPE, responseFormat.toContentTypeString());
    }

    private EntityCollection getData(EdmEntitySet edmEntitySet) {

        EntityCollection studentEntityCollection = new EntityCollection();
        // check for which EdmEntitySet the data is requested
        if (Constants.ENTITY_SET_STUDENTS_NAME.equals(edmEntitySet.getName())) {
            List<Entity> studentList = studentEntityCollection.getEntities();



            final Entity entity_1 = new Entity().addProperty(new Property(EdmPrimitiveTypeKind.Int32.getDeclaringClass().getName(),
                             "studentID", ValueType.PRIMITIVE, "1"))
                    .addProperty(new Property(EdmPrimitiveTypeKind.Int32.getDeclaringClass().getName(), "Name", ValueType.PRIMITIVE, "Student 1"))
                    .addProperty(new Property(EdmPrimitiveTypeKind.DateTimeOffset.getDeclaringClass().getName(), "DateOfJoining", ValueType.PRIMITIVE,
                            System.currentTimeMillis()));

            studentList.add(entity_1);

        }

        return studentEntityCollection;
    }

    private byte[] getImageResource() throws IOException {
        Resource resource = new ClassPathResource("images/lock-icon.png");
        return Files.readAllBytes(resource.getFile().toPath());

    }


}