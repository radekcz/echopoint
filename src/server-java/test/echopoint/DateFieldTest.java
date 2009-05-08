/*
 * This file is part of the Echo Point Project.  This project is a
 * collection of Components that have extended the Echo Web Application
 * Framework Version 3.
 *
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 */
package echopoint;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import echopoint.jquery.DateField;
import nextapp.echo.app.*;

/**
 * Test case for the {@link echopoint.jquery.DateField} component.
 *
 * @author Hans Holmlund 2009-04-29
 * @version $Id$
 */
public class DateFieldTest extends AbstractTest<DateField> {
    @BeforeClass
    public static void init()
    {
        set( new DateField() );
    }

    @Test
    public void renderId()
    {
        final String renderId = "echopointUnitTestDateField";
        getComponent().setRenderId( renderId );
        assertEquals( "Ensuring renderId set", renderId, getComponent().getRenderId() );
    }

    @Test
    public void alignment()
    {
        final Alignment alignment = Alignment.ALIGN_CENTER;
        getComponent().setAlignment( alignment );
        assertEquals( "Ensuring alignment set", alignment, getComponent().getAlignment() );
    }

    @Test
    public void insets()
    {
        final Insets insets = new Insets( 10 );
        getComponent().setInsets( insets );
        assertEquals( "Ensuring insets set", insets, getComponent().getInsets() );
    }

    @Test
    public void font()
    {
        final Font font = new Font( Font.HELVETICA, Font.PLAIN, new Extent( 11 ) );
        getComponent().setFont( font );
        assertEquals( "Ensuring Font set", font, getComponent().getFont() );
    }

    @Test
    public void foreground()
    {
        final Color color = new Color( 0x2f2f4f );
        getComponent().setForeground( color );
        assertEquals( "Ensuring Foreground set", color, getComponent().getForeground() );
    }

    @Test
    public void width()
    {
        final Extent width = new Extent( 250 );
        getComponent().setWidth( width );
        assertEquals( "Ensuring width set", width, getComponent().getWidth() );
    }

    @Test
    public void dateFormat()
    {
        final String dateFormat = "yyyy/MM/dd HH:mm";
        getComponent().setDateFormat( dateFormat );
        assertEquals( "Ensuring dateformat set", dateFormat, getComponent().getDateFormat() );
    }

    @AfterClass
    public static void finish()
    {
        final Component content = Application.getContent().getTestArea();
        content.removeAll();
        content.add( get() );
    }
    
}