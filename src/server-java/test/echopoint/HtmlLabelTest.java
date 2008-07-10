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

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import nextapp.echo.app.Label;

/**
 * Test case for the {@link HtmlLabel} component.  Exercises getting and
 * setting the display text.
 *
 * @author Rakesh 2008-06-25
 * @version $Id$
 */
public class HtmlLabelTest
{
  /** The component that will be tested. */
  private static HtmlLabel component;

  /** The simple text to display in the test component. */
  private static final String SIMPLE_TEXT =
      "<br/><br/><b>HtmlLabel</b> simple test content.<br/><br/>";

  @BeforeClass
  public static void init()
  {
    final MainContent content = Application.getContent();
    component = new HtmlLabel();
    content.getTestArea().removeAll();
    content.getTestArea().add( component );

    assertEquals( "Ensuring test component added to container",
        content.getTestArea().getComponentCount(), 1 );
  }

  @Test
  public void simple()
  {
    component.setText( SIMPLE_TEXT );

    assertEquals( "Ensuring getText returns simple",
        component.getText(), SIMPLE_TEXT );
  }

  @Test
  public void add()
  {
    try
    {
      component.add( new Label( "Test label" ) );
      fail( "Label added to DirectHtml" );
    }
    catch ( Throwable t ) {}
  }

  @Test
  public void append()
  {
    component.setText( null );
    assertNull( "Ensuring getText returns null", component.getText() );

    final String modification = "modified";
    component.append( SIMPLE_TEXT );
    component.append( modification );

    assertEquals( "Ensuring getText returns simple + modification",
        component.getText(), SIMPLE_TEXT + modification );
  }
}