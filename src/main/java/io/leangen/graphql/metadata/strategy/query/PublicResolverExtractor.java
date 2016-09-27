package io.leangen.graphql.metadata.strategy.query;

import io.leangen.graphql.generator.mapping.TypeMapperRepository;
import io.leangen.graphql.metadata.QueryResolver;
import io.leangen.graphql.query.conversion.ConverterRepository;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Predicate;

/**
 * Created by bojan.tomic on 6/10/16.
 */
public class PublicResolverExtractor implements ResolverExtractor {

    private final QueryNameGenerator queryNameGenerator;
    private final QueryResolverArgumentExtractor argumentExtractor;

    public PublicResolverExtractor() {
        this(new MethodQueryNameGenerator(), new AnnotatedArgumentExtractor());
    }

    public PublicResolverExtractor(QueryNameGenerator queryNameGenerator, QueryResolverArgumentExtractor argumentExtractor) {
        this.queryNameGenerator = queryNameGenerator;
        this.argumentExtractor = argumentExtractor;
    }

    @Override
    public Collection<QueryResolver> extractQueryResolvers(Object querySourceBean, AnnotatedType type, TypeMapperRepository typeMappers, ConverterRepository converters) {
        return extractQueryResolvers(querySourceBean, type, typeMappers, converters, acceptAll);
    }

    @Override
    public Collection<QueryResolver> extractMutationResolvers(Object querySourceBean, AnnotatedType type, TypeMapperRepository typeMappers, ConverterRepository converters) {
        return extractMutationResolvers(querySourceBean, type, typeMappers, converters, acceptAll);
    }

    @Override
    public Collection<QueryResolver> extractQueryResolvers(Object querySourceBean, AnnotatedType type, TypeMapperRepository typeMappers,
                                                           ConverterRepository converters, Predicate<Member>... filters) {
//		return extractResolvers(querySourceBean, type, queryNameGenerator::generateQueryName, this::isQuery, filters);
        return null;
    }

    @Override
    public Collection<QueryResolver> extractMutationResolvers(Object querySourceBean, AnnotatedType type, TypeMapperRepository typeMappers,
                                                              ConverterRepository converters, Predicate<Member>... filters) {
//		return extractResolvers(querySourceBean, type, queryNameGenerator::generateMutationName, this::isMutation, filters);
        return null;
    }

    protected Collection<QueryResolver> extractResolvers(Object querySourceBean, Type type, BiFunction<Method, Type, String> queryNamer,
                                                         Predicate<Method> mandatoryFilter, Predicate<Method>... extraFilters) {
//		return Arrays.stream(ClassUtils.getRawType(type).getMethods())
//				.filter(method -> ClassUtils.getRawType(type).getPackage().equals(method.getDeclaringClass().getPackage()))
//				.filter(mandatoryFilter)
//				.filter(Arrays.stream(extraFilters).reduce(Predicate::and).orElse(acceptAll))
//				.collect(toResolvers(querySourceBean, type, queryNamer, Method::getName, argumentExtractor));
        return null;
    }

    protected boolean isQuery(Method method) {
        return !isMutation(method);
    }

    protected boolean isMutation(Method method) {
        return method.getReturnType() == Void.class;
    }
}
