/*
 * Copyright (c) 2020 Werner Keil and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Apache License v2.0 which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Apache License v2.0 is available at http://www.opensource.org/licenses/apache2.0.php.
 *
 * You may elect to redistribute this code under either of these licenses.
 *
 * Contributors:
 *
 * Werner Keil
 */
package org.jnosql.demo.micronaut.mongodb.repository;

import java.util.List;
import org.jnosql.demo.micronaut.mongodb.model.God;

public interface GodRepository {
	boolean save(God g);
	List<God> findAll();
	List<God> findByName(String name);
	long delete(String name);
	long countAll();
}
