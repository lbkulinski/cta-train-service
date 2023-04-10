package com.cta4j.config;

import com.cta4j.exception.TrainException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public final class CustomExceptionResolver extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(@NonNull Throwable throwable,
        @NonNull DataFetchingEnvironment environment) {
        if (throwable instanceof TrainException exception) {
            ErrorType errorType = exception.getErrorType();

            String message = throwable.getMessage();

            var path = environment.getExecutionStepInfo().getPath();

            var location = environment.getField()
                                      .getSourceLocation();

            return GraphqlErrorBuilder.newError()
                                      .errorType(errorType)
                                      .message(message)
                                      .path(path)
                                      .location(location)
                                      .build();
        }

        return null;
    }
}
