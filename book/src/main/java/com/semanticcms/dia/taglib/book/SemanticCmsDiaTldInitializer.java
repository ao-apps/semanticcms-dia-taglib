/*
 * semanticcms-dia-taglib - Java API for embedding Dia-based diagrams in web pages in a JSP environment.
 * Copyright (C) 2016  AO Industries, Inc.
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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author  AO Industries, Inc.
 */
public class SemanticCmsDiaTldInitializer extends TagReferenceInitializer {

	private static final String TLD_BOOK = "/dia/taglib";
	private static final String TLD_PATH = "/semanticcms-dia.tld";
	static final String TLD_SERVLET_PATH = TLD_BOOK + TLD_PATH;

	private static final Map<String,String> additionalApiLinks = new LinkedHashMap<String,String>();
	static {
		additionalApiLinks.put("com.semanticcms.dia.taglib.", "https://semanticcms.com/dia/taglib/apidocs/");
	}

	public SemanticCmsDiaTldInitializer() {
		super(
			"Dia Taglib Reference",
			"Taglib Reference",
			TLD_BOOK,
			TLD_PATH,
			"https://docs.oracle.com/javase/6/docs/api/",
			"https://docs.oracle.com/javaee/6/api/",
			additionalApiLinks
		);
	}
}
