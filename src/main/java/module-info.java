/*
 * semanticcms-dia-taglib - Java API for embedding Dia-based diagrams in web pages in a JSP environment.
 * Copyright (C) 2021, 2022  AO Industries, Inc.
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
 * along with semanticcms-dia-taglib.  If not, see <https://www.gnu.org/licenses/>.
 */
module com.semanticcms.dia.taglib {
  exports com.semanticcms.dia.taglib;
  // Direct
  requires com.aoapps.encoding.taglib; // <groupId>com.aoapps</groupId><artifactId>ao-encoding-taglib</artifactId>
  requires com.aoapps.html.any; // <groupId>com.aoapps</groupId><artifactId>ao-fluent-html-any</artifactId>
  requires com.aoapps.html.servlet; // <groupId>com.aoapps</groupId><artifactId>ao-fluent-html-servlet</artifactId>
  requires com.aoapps.io.buffer; // <groupId>com.aoapps</groupId><artifactId>ao-io-buffer</artifactId>
  requires com.aoapps.lang; // <groupId>com.aoapps</groupId><artifactId>ao-lang</artifactId>
  requires com.aoapps.net.types; // <groupId>com.aoapps</groupId><artifactId>ao-net-types</artifactId>
  requires com.aoapps.taglib; // <groupId>com.aoapps</groupId><artifactId>ao-taglib</artifactId>
  requires javax.el.api; // <groupId>javax.el</groupId><artifactId>javax.el-api</artifactId>
  requires javax.servlet.api; // <groupId>javax.servlet</groupId><artifactId>javax.servlet-api</artifactId>
  requires javax.servlet.jsp.api; // <groupId>javax.servlet.jsp</groupId><artifactId>javax.servlet.jsp-api</artifactId>
  requires com.semanticcms.core.model; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-core-model</artifactId>
  requires com.semanticcms.core.pages; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-core-pages</artifactId>
  requires com.semanticcms.core.taglib; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-core-taglib</artifactId>
  requires com.semanticcms.dia.model; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-dia-model</artifactId>
  requires com.semanticcms.dia.renderer.html; // <groupId>com.semanticcms</groupId><artifactId>semanticcms-dia-renderer-html</artifactId>
}
