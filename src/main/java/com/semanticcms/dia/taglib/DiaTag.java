/*
 * semanticcms-dia-taglib - Java API for embedding Dia-based diagrams in web pages in a JSP environment.
 * Copyright (C) 2013, 2014, 2015, 2016, 2017, 2020, 2021, 2022  AO Industries, Inc.
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

package com.semanticcms.dia.taglib;

import static com.aoapps.taglib.AttributeUtils.resolveValue;

import com.aoapps.encoding.taglib.EncodingBufferedTag;
import com.aoapps.html.servlet.DocumentEE;
import com.aoapps.io.buffer.BufferResult;
import com.aoapps.io.buffer.BufferWriter;
import com.aoapps.lang.Strings;
import com.aoapps.lang.validation.ValidationException;
import com.aoapps.net.DomainName;
import com.aoapps.net.Path;
import com.semanticcms.core.model.ElementContext;
import com.semanticcms.core.pages.CaptureLevel;
import com.semanticcms.core.taglib.ElementTag;
import com.semanticcms.dia.model.Dia;
import com.semanticcms.dia.renderer.html.DiaHtmlRenderer;
import java.io.IOException;
import java.io.Writer;
import javax.el.ELContext;
import javax.el.ValueExpression;
import javax.servlet.ServletContext;
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
              Strings.nullIfEmpty(
                  resolveValue(domain, String.class, elContext)
              )
          )
      );
      dia.setBook(
          Path.valueOf(
              Strings.nullIfEmpty(
                  resolveValue(book, String.class, elContext)
              )
          )
      );
      dia.setPath(resolveValue(path, String.class, elContext));
      Integer myWidth = resolveValue(width, Integer.class, elContext);
      dia.setWidth(myWidth == null ? 0 : myWidth);
      Integer myHeight = resolveValue(height, Integer.class, elContext);
      dia.setHeight(myHeight == null ? 0 : myHeight);
    } catch (ValidationException e) {
      throw new JspTagException(e);
    }
  }

  private BufferResult writeMe;

  @Override
  protected void doBody(Dia dia, CaptureLevel captureLevel) throws JspException, IOException {
    try {
      super.doBody(dia, captureLevel);
      final PageContext pageContext = (PageContext) getJspContext();
      final HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
      BufferWriter capturedOut;
      if (captureLevel == CaptureLevel.BODY) {
        capturedOut = EncodingBufferedTag.newBufferWriter(request);
      } else {
        capturedOut = null;
      }
      try {
        ServletContext servletContext = pageContext.getServletContext();
        HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
        DiaHtmlRenderer.writeDiaImpl(
            servletContext,
            request,
            response,
            (capturedOut == null) ? null : new DocumentEE(
                servletContext,
                request,
                response,
                capturedOut,
                false, // Do not add extra newlines to JSP
                false  // Do not add extra indentation to JSP
            ),
            dia
        );
      } finally {
        if (capturedOut != null) {
          capturedOut.close();
        }
      }
      writeMe = capturedOut == null ? null : capturedOut.getResult();
    } catch (ServletException e) {
      throw new JspTagException(e);
    }
  }

  @Override
  public void writeTo(Writer out, ElementContext context) throws IOException {
    if (writeMe != null) {
      writeMe.writeTo(out);
    }
  }
}
