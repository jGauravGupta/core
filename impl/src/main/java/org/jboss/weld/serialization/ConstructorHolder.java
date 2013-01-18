/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.serialization;

import static org.jboss.weld.logging.messages.ReflectionMessage.UNABLE_TO_GET_CONSTRUCTOR_ON_DESERIALIZATION;

import java.lang.reflect.Constructor;

import org.jboss.weld.exceptions.WeldException;
import org.jboss.weld.util.reflection.SecureReflections;

/**
 * Serializable holder for {@link Constructor}.
 *
 * @author Jozef Hartinger
 *
 */
public class ConstructorHolder<X> extends AbstractSerializableHolder<Constructor<X>> {

    private static final long serialVersionUID = -6439218442811003152L;

    public static <T> ConstructorHolder<T> of(Constructor<T> constructor) {
        return new ConstructorHolder<T>(constructor);
    }

    private final Class<X> declaringClass;
    private final Class<?>[] parameterTypes;

    public ConstructorHolder(Constructor<X> constructor) {
        super(constructor);
        this.declaringClass = constructor.getDeclaringClass();
        this.parameterTypes = constructor.getParameterTypes();
    }

    @Override
    protected Constructor<X> initialize() {
        try {
            return SecureReflections.getDeclaredConstructor(declaringClass, parameterTypes);
        } catch (Exception e) {
            throw new WeldException(UNABLE_TO_GET_CONSTRUCTOR_ON_DESERIALIZATION, e, declaringClass, parameterTypes);
        }
    }
}
