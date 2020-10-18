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
package org.jnosql.demo.micronaut.neo4j.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class City {
	public long id;
	public String name;
	public String country;
	public Double lon, lat;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map asMap() {
		Map result = new HashMap();
		result.put("id", id);
		result.put("name", name);
		result.put("country", country);
		result.put("lat", lat);
		result.put("lon", lon);
		return result;
	}

	public City() {
	}

	@SuppressWarnings("rawtypes")
	public City(Map data) {
		final Long newId = (Long) data.get("id");
		this.id = (newId != null) ? newId : -1;
		this.name = (String) data.get("name");
		this.country = (String) data.get("country");
		this.lat = (Double) data.get("lat");
		this.lon = (Double) data.get("lon");
	}
}

class CityResult {
	public List<City> results;
}
