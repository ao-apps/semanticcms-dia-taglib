/*
 * semanticcms-dia-taglib - Java API for embedding Dia-based diagrams in web pages in a JSP environment.
 * Copyright (C) 2016, 2017, 2019  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of semanticcms-dia-taglib.
 *
 * semanticcms-dia-taglib is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * semanticcms-dia-taglib is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with semanticcms-dia-taglib.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.semanticcms.dia.taglib.book;

import com.semanticcms.tagreference.TagReferenceInitializer;
import java.util.Collections;

public class SemanticCmsDiaTldInitializer extends TagReferenceInitializer {

	public SemanticCmsDiaTldInitializer() {
		super(
			"Dia Taglib Reference",
			"Taglib Reference",
			"/dia/taglib",
			"/semanticcms-dia.tld",
			Maven.properties.getProperty("documented.javac.link.javaApi"),
			Maven.properties.getProperty("javac.link.javaeeApi.6"),
			Collections.singletonMap("com.semanticcms.dia.taglib.", Maven.properties.getProperty("documented.url") + "apidocs/")
		);
	}
}
