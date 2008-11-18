/*
* JBoss, Home of Professional Open Source
* Copyright 2008, Red Hat Middleware LLC, and individual contributors
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

package org.jboss.webbeans.contexts;

import java.lang.annotation.Annotation;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The abstraction of a shared context with common beans but 
 * with a private active state
 *  
 * @author Nicklas Karlsson
 * @see org.jboss.webbeans.ApplicationContext
 */
public class SharedContext extends AbstractContext
{
   private BeanMap beans;
   private ThreadLocal<AtomicBoolean> active;

   public SharedContext(Class<? extends Annotation> scopeType)
   {
      super(scopeType);
      beans = new SimpleBeanMap();
      active = new ThreadLocal<AtomicBoolean>();
      active.set(new AtomicBoolean(true));
   }

   @Override
   protected AtomicBoolean getActive()
   {
      return active.get();
   }

   @Override
   protected BeanMap getBeanMap()
   {
      return beans;
   }

}