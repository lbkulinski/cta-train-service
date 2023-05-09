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
import org.springframework.validation.BindException;
import org.springframework.graphql.execution.ErrorType;

@Component
public final class CustomExceptionResolver extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(@NonNull Throwable throwable,
        @NonNull DataFetchingEnvironment environment) {
        if (throwable instanceof DataFetcherException exception) {
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
        } else if (throwable instanceof BindException) {
            String message = "The specified station ID must be positive integer";

            ResultPath path = environment.getExecutionStepInfo()
                                         .getPath();

            SourceLocation location = environment.getField()
                                                 .getSourceLocation();

            return GraphqlErrorBuilder.newError()
                                      .errorType(ErrorType.BAD_REQUEST)
                                      .message(message)
                                      .path(path)
                                      .location(location)
                                      .build();
        }

        return null;
    }
}
