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
package org.jnosql.demo.micronaut.mongodb.controller;

import java.util.List;
import javax.inject.Inject;

import org.jnosql.demo.micronaut.mongodb.model.God;
import org.jnosql.demo.micronaut.mongodb.repository.GodRepository;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Delete;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.QueryValue;

@Controller("/api")
public class GodController {

	@Inject
	GodRepository repo;
	
    @SuppressWarnings("rawtypes")
	@Post("/gods/new")
    public HttpResponse create(@QueryValue(value = "name") String name, @QueryValue(value = "power") String power) {
    	//System.out.println(gods + " gods."); TODO change to logging
    	final God god = new God(name);
    	god.setPower(power);
    	repo.save(god);
    	return HttpResponse.created(god);
    }
    
    @Get("/gods")
    public List<God> getAllGods() {
        return repo.findAll();
    }
    
    @Get("/gods/{name}")
    public List<God> godsByName(String name) {
        return repo.findByName(name);
    }
    
    @SuppressWarnings("rawtypes")
	@Delete("/gods/{name}")
    public HttpResponse delete(String name) {    	
    	long d = 0;
    	if (name != null) {
    		d = repo.delete(name);
    	}
        if (d > 0) {
        	return HttpResponse.ok(String.format("%s deleted", name));
        } else {
        	return HttpResponse.noContent();
        }
    }
    
    @Get("/gods/count")
    public long count() {
        return repo.countAll();
    }
    
}