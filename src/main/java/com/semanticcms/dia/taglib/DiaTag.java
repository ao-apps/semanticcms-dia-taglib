/*
 * semanticcms-dia-taglib - Java API for embedding Dia-based diagrams in web pages in a JSP environment.
 * Copyright (C) 2013, 2014, 2015, 2016, 2017  AO Industries, Inc.
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
package com.semanticcms.dia.taglib;

import static com.aoindustries.encoding.Coercion.zeroIfEmpty;
import com.aoindustries.io.buffer.BufferResult;
import com.aoindustries.io.buffer.BufferWriter;
import com.aoindustries.net.DomainName;
import com.aoindustries.net.Path;
import static com.aoindustries.taglib.AttributeUtils.resolveValue;
import com.aoindustries.taglib.AutoEncodingBufferedTag;
import com.aoindustries.util.StringUtility;
import com.aoindustries.validation.ValidationException;
import com.semanticcms.core.model.ElementContext;
import com.semanticcms.core.pages.CaptureLevel;
import com.semanticcms.core.taglib.ElementTag;
import com.semanticcms.dia.model.Dia;
import com.semanticcms.dia.servlet.impl.DiaImpl;
import java.io.IOException;
import java.io.Writer;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;

/**
 * Writes an img tag for the diagram thumbnail.
 */
public class DiaTag extends ElementTag<Dia> {

	private ValueExpression label;
	public void setLabel(ValueExpression label) {
		this.label = label;
	}

	private ValueExpression domain;
	public void setDomain(ValueExpression domain) {
		this.domain = domain;
	}

	private ValueExpression book;
	public void setBook(ValueExpression book) {
		this.book = book;
	}

	private ValueExpression path;
	public void setPath(ValueExpression path) {
		this.path = path;
	}

	private ValueExpression width;
	public void setWidth(ValueExpression width) {
		this.width = width;
	}

	private ValueExpression height;
	public void setHeight(ValueExpression height) {
		this.height = height;
	}

	@Override
	protected Dia createElement() {
		return new Dia();
	}

	@Override
	protected void evaluateAttributes(Dia dia, ELContext elContext) throws JspTagException, IOException {
		try {
			super.evaluateAttributes(dia, elContext);
			dia.setLabel(resolveValue(label, String.class, elContext));
			dia.setDomain(
				DomainName.valueOf(
					StringUtility.nullIfEmpty(
						resolveValue(domain, String.class, elContext)
					)
				)
			);
			dia.setBook(
				Path.valueOf(
					StringUtility.nullIfEmpty(
						resolveValue(book, String.class, elContext)
					)
				)
			);
			dia.setPath(resolveValue(path, String.class, elContext));
			dia.setWidth(zeroIfEmpty(resolveValue(width, Integer.class, elContext)));
			dia.setHeight(zeroIfEmpty(resolveValue(height, Integer.class, elContext)));
		} catch(ValidationException e) {
			throw new JspTagException(e);
		}
	}

	private BufferResult writeMe;
	@Override
	protected void doBody(Dia dia, CaptureLevel captureLevel) throws JspException, IOException {
		try {
			super.doBody(dia, captureLevel);
			final PageContext pageContext = (PageContext)getJspContext();
			final HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			BufferWriter capturedOut;
			if(captureLevel == CaptureLevel.BODY) {
				capturedOut = AutoEncodingBufferedTag.newBufferWriter(request);
			} else {
				capturedOut = null;
			}
			try {
				DiaImpl.writeDiaImpl(
					pageContext.getServletContext(),
					request,
					(HttpServletResponse)pageContext.getResponse(),
					capturedOut,
					dia
				);
			} finally {
				if(capturedOut != null) capturedOut.close();
			}
			writeMe = capturedOut==null ? null : capturedOut.getResult();
		} catch(ServletException e) {
			throw new JspTagException(e);
		}
	}

	@Override
	public void writeTo(Writer out, ElementContext context) throws IOException {
		if(writeMe != null) writeMe.writeTo(out);
	}
}
