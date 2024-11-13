package com.takamulstg.seeu.jupiter;

import com.takamulstg.seeu.api.RestClient;
import com.takamulstg.seeu.data.CustomFieldType;
import com.takamulstg.seeu.jupiter.UUIDParameter.FieldUUID;
import org.junit.jupiter.api.extension.*;
import org.junit.platform.commons.support.AnnotationSupport;

import java.lang.reflect.Parameter;
import java.util.Optional;
import java.util.UUID;

public class CreateCustomFieldExtension implements BeforeEachCallback, ParameterResolver {

    private final RestClient restClient = new RestClient();
    public static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(CreateCustomFieldExtension.class);

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        Optional<CreateCustomField> annotation = AnnotationSupport.findAnnotation(context.getRequiredTestMethod(), CreateCustomField.class);

        UUID fieldId = null;

        if (annotation.isPresent()) {
            CustomFieldType fieldType = annotation.get().type();

            fieldId = restClient.createCustomField(fieldType);
            context.getStore(NAMESPACE).put(context.getUniqueId(), fieldId);
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
                            AnnotationSupport.isAnnotated(parameterContext.getParameter(), FieldUUID.class);
        } else {
            return parameterContext.getParameter().getType().isAssignableFrom(UUID.class);
        }
    }

    @Override
    public UUID resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return extensionContext.getStore(NAMESPACE).get(extensionContext.getUniqueId(), UUID.class);
    }
}
