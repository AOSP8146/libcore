/*
 * Copyright (c) 2001-2004 World Wide Web Consortium, (Massachusetts Institute of
 * Technology, Institut National de Recherche en Informatique et en
 * Automatique, Keio University). All Rights Reserved. This program is
 * distributed under the W3C's Software Intellectual Property License. This
 * program is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See W3C License
 * http://www.w3.org/Consortium/Legal/ for more details.
 */

package org.w3c.domts.level2.events;

import java.lang.reflect.Constructor;

import javax.xml.parsers.DocumentBuilderFactory;

import junit.framework.TestSuite;

import org.w3c.domts.DOMTestDocumentBuilderFactory;
import org.w3c.domts.DOMTestSuite;
import org.w3c.domts.JAXPDOMTestDocumentBuilderFactory;
import org.w3c.domts.JUnitTestSuiteAdapter;

/**
 * Test suite that runs all DOM L2 Events tests using the
 * Oracle Parser for Java in an alternative configuration.
 *
 * @author Curt Arnold
 *
 */
public class TestOracleAltConfig extends TestSuite {
        /**
         * Constructor
         * @return test suite
         * @throws Exception
         */
        public static TestSuite suite() throws Exception {
                Class testClass =
                        ClassLoader.getSystemClassLoader().loadClass(
                                "org.w3c.domts.level2.events.alltests");
                Constructor testConstructor =
                        testClass.getConstructor(
                                new Class[] { DOMTestDocumentBuilderFactory.class });

                DocumentBuilderFactory oracleFactory =
                        (DocumentBuilderFactory) ClassLoader
                                .getSystemClassLoader()
                                .loadClass("oracle.xml.jaxp.JXDocumentBuilderFactory")
                                .newInstance();

                DOMTestDocumentBuilderFactory factory =
                        new JAXPDOMTestDocumentBuilderFactory(
                                oracleFactory,
                                JAXPDOMTestDocumentBuilderFactory.getConfiguration2());

                Object test = testConstructor.newInstance(new Object[] { factory });

                return new JUnitTestSuiteAdapter((DOMTestSuite) test);
        }

}
