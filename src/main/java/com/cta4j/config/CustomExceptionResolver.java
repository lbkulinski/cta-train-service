package com.cta4j.config;

import org.springframework.stereotype.Component;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import graphql.GraphQLError;
import org.springframework.lang.NonNull;
import graphql.schema.DataFetchingEnvironment;
import com.cta4j.exception.DataFetcherException;
import graphql.ErrorClassification;
import graphql.execution.ResultPath;
import graphql.language.SourceLocation;
import graphql.GraphqlErrorBuilder;

@Component
public final class CustomExceptionResolver extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(@NonNull Throwable throwable,
        @NonNull DataFetchingEnvironment environment) {
        if (!(throwable instanceof DataFetcherException exception)) {
            return null;
        }

        ErrorClassification errorType = exception.getErrorType();

        String message = throwable.getMessage();

        ResultPath path = environment.getExecutionStepInfo()
                                     .getPath();

        SourceLocation location = environment.getField()
                                             .getSourceLocation();

        return GraphqlErrorBuilder.newError()
                                  .errorType(errorType)
                                  .message(message)
                                  .path(path)
                                  .location(location)
                                  .build();
    }
}
