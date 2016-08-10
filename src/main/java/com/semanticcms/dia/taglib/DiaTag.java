/*
 * semanticcms-dia-taglib - Java API for embedding Dia-based diagrams in web pages in a JSP environment.
 * Copyright (C) 2013, 2014, 2015, 2016  AO Industries, Inc.
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

import com.aoindustries.io.buffer.BufferResult;
import com.aoindustries.io.buffer.BufferWriter;
import com.aoindustries.io.buffer.SegmentedWriter;
import com.semanticcms.core.model.ElementContext;
import com.semanticcms.core.servlet.CaptureLevel;
import com.semanticcms.core.taglib.ElementTag;
import com.semanticcms.dia.model.Dia;
import com.semanticcms.dia.servlet.impl.DiaImpl;
import java.io.IOException;
import java.io.Writer;
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

	public DiaTag() {
		super(new Dia());
	}

    public void setLabel(String label) {
		element.setLabel(label);
	}

	public void setBook(String book) {
		element.setBook(book);
	}

	public void setPath(String path) {
		element.setPath(path);
    }

	public void setWidth(int width) {
		element.setWidth(width);
	}
	
	public void setHeight(int height) {
		element.setHeight(height);
	}

	private BufferResult writeMe;
	@Override
	protected void doBody(CaptureLevel captureLevel) throws JspException, IOException {
		try {
			super.doBody(captureLevel);
			BufferWriter out = (captureLevel == CaptureLevel.BODY) ? new SegmentedWriter() : null;
			try {
				final PageContext pageContext = (PageContext)getJspContext();
				DiaImpl.writeDiaImpl(
					pageContext.getServletContext(),
					(HttpServletRequest)pageContext.getRequest(),
					(HttpServletResponse)pageContext.getResponse(),
					out,
					element
				);
			} finally {
				if(out != null) out.close();
			}
			writeMe = out==null ? null : out.getResult();
		} catch(ServletException e) {
			throw new JspTagException(e);
		}
	}

	@Override
	public void writeTo(Writer out, ElementContext context) throws IOException {
		if(writeMe != null) writeMe.writeTo(out);
	}
}
