package com.takamulstg.seeu.jupiter;

import com.takamulstg.seeu.api.RestClient;
import com.takamulstg.seeu.api.dtos.ContactGroupJson;
import com.takamulstg.seeu.jupiter.UUIDParameter.ContactGroupUUID;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

import java.lang.reflect.Parameter;
import java.util.Optional;
import java.util.UUID;

public class AddContactGroupExtension implements BeforeEachCallback, ParameterResolver, AfterEachCallback {

    private final RestClient restClient = new RestClient();
    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(AddContactGroupExtension.class);

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Optional<AddContactGroup> annotation = AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), AddContactGroup.class);

        UUID contactGroupId = null;

        if (annotation.isPresent()) {
            contactGroupId = restClient.createNewContactGroup();
            context.getStore(NAMESPACE).put(context.getUniqueId(), contactGroupId);
        }
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        Parameter[] parameters = extensionContext.getRequiredTestMethod().getParameters();
        int count = 0;

        for (Parameter parameter : parameters) {
            if (parameter.getType().isAssignableFrom(UUID.class)) {
                count++;
            }
        }
        if (count > 1) {
            return
                    parameterContext.getParameter().getType().isAssignableFrom(UUID.class) &&
                            AnnotationSupport.isAnnotated(parameterContext.getParameter(), ContactGroupUUID.class);
        } else {
            return parameterContext.getParameter().getType().isAssignableFrom(UUID.class);
        }
    }

    @Override
    public UUID resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), UUID.class);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        Optional<AddContactGroup> annotation = AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), AddContactGroup.class);
        UUID contactGroupId = context.getStore(NAMESPACE).get(context.getUniqueId(), UUID.class);
        if (annotation.isPresent() && annotation.get().cleanUp()) {
            ContactGroupJson contactGroup = restClient.getContactGroup(contactGroupId);
            if (!contactGroup.getHasRfi()) {
                restClient.deleteContactGroup(contactGroupId);
            }
        }
    }
}
