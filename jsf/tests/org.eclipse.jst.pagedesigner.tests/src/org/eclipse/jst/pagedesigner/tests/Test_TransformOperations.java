/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.tests;

import java.util.List;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;
import org.eclipse.jst.jsp.core.internal.domdocument.DOMModelForJSP;
import org.eclipse.jst.pagedesigner.converter.IConverterFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.dtmanager.DTManager;
import org.eclipse.jst.pagedesigner.dtmanager.converter.ITransformOperation;
import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.TransformOperationFactory;
import org.eclipse.wst.sse.core.StructuredModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IModelManager;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * DTManager tests.
 * 
 * @author Ian Trimble - Oracle
 */
public class Test_TransformOperations extends TestCase {

	private static final String OUTPUT_PREFIX = "[Test_TransformOperations]";

	private WebProjectTestEnvironment webProjectTestEnv;

	protected void setUp() throws Exception {
		super.setUp();

		JSFTestUtil.setValidationEnabled(false);

		webProjectTestEnv = new WebProjectTestEnvironment("Test_TransformOperations_" + getName());
		webProjectTestEnv.createProject(false);
		assertNotNull(webProjectTestEnv);
		assertNotNull(webProjectTestEnv.getTestProject());
		assertTrue(webProjectTestEnv.getTestProject().isAccessible());

        JSFFacetedTestEnvironment jsfFacetedTestEnv = new JSFFacetedTestEnvironment(webProjectTestEnv);
        jsfFacetedTestEnv.initialize(IJSFCoreConstants.FACET_VERSION_1_1);

        webProjectTestEnv.loadResourceInWebRoot(
        		PageDesignerTestsPlugin.getDefault().getBundle(),
        		"/testdata/Test_TransformOperations.jsp.data",
        		"/Test_TransformOperations.jsp");

        assertTrue(JSFCoreUtilHelper.addJSFRuntimeJarsToClasspath(JSFVersion.V1_1, jsfFacetedTestEnv));
	}

	/**
	 * Sanity check.
	 */
	public void testSanity() {
		ContextWrapper wrapper = null;
		try {
			wrapper = getDocumentContext("/WebContent/Test_TransformOperations.jsp", 477);
			IStructuredDocumentContext context = wrapper.getContext();
            IDOMContextResolver resolver =
                IStructuredDocumentContextResolverFactory.INSTANCE.
                    getDOMContextResolver(context);
            Node node = resolver.getNode();
            assertTrue(node instanceof Element);
            assertEquals("h", node.getPrefix());
            assertEquals("selectManyListbox", node.getLocalName());
		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		} finally {
			if (wrapper != null) {
				wrapper.dispose();
			}
		}
	}

	/**
	 * Test "AppendChildElementOperation".
	 */
	public void testAppendChildElementOperation() {
		try {
			//get and test result element
			Element resultElement = getResultElement(477, "h", "selectManyListbox");
			assertNotNull(resultElement);
			assertEquals("select", resultElement.getLocalName());

			//check first child node exists and is correct node
			Node firstChildNode = resultElement.getFirstChild();
			assertTrue(firstChildNode instanceof Element);
			Element firstChildElement = (Element)firstChildNode;
			assertEquals("option", firstChildElement.getLocalName());

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}

	/**
	 * Test "AppendChildTextFromXPathOperation".
	 */
	public void testAppendChildTextFromXPathOperation() {
		try {
			//get and test result element
			Element resultElement = getResultElement(477, "h", "selectManyListbox");
			assertNotNull(resultElement);
			assertEquals("select", resultElement.getLocalName());

			//check first child node exists and is correct node
			Node firstChildNode = resultElement.getFirstChild();
			assertTrue(firstChildNode instanceof Element);
			Element firstChildElement = (Element)firstChildNode;
			assertEquals("option", firstChildElement.getLocalName());

			//check grandchild Text exists and has correct value
			Node firstGrandChildNode = firstChildElement.getFirstChild();
			assertTrue(firstGrandChildNode instanceof Text);
			Text firstGrandChildText = (Text)firstGrandChildNode;
			assertEquals("selectItem One", firstGrandChildText.getData());

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}

	/**
	 * Test "AppendChildTextOperation".
	 */
	public void testAppendChildTextOperation() {
		try {
			//get and test result element
			Element resultElement = getResultElement(477, "h", "selectManyListbox");
			assertNotNull(resultElement);
			assertEquals("select", resultElement.getLocalName());

			//check second child node exists and is correct node
			NodeList childNodes = resultElement.getChildNodes();
			assertTrue(childNodes.getLength() > 1);
			Node secondChildNode = childNodes.item(1);
			assertTrue(secondChildNode instanceof Element);
			Element secondChildElement = (Element)secondChildNode;
			assertEquals("option", secondChildElement.getLocalName());

			//check grandchild Text exists and has correct value
			Node firstGrandChildNode = secondChildElement.getFirstChild();
			assertTrue(firstGrandChildNode instanceof Text);
			Text firstGrandChildText = (Text)firstGrandChildNode;
			assertEquals("selectItem", firstGrandChildText.getData());

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}

	/**
	 * Test "CheckModeOperation".
	 */
	public void testCheckModeOperation() {
		try {
			webProjectTestEnv.loadResourceInWebRoot(
				PageDesignerTestsPlugin.getDefault().getBundle()
				, "/testdata/checkmodeop/checkModeOp.tld.data"
				, "/META-INF/checkModeOp.tld");

			webProjectTestEnv.loadResourceInWebRoot(
				PageDesignerTestsPlugin.getDefault().getBundle()
				,"/testdata/checkmodeop/testCheckModeOperation.jsp.data"
				,"/testCheckModeOperation.jsp");

			ContextWrapper wrapper = null;

			// First converter is simple use of CheckModeOperation
			// containing child operations.
			// Exercises both design and preview mode, but there should
			// only be conversion for design view, based on the design
			// time info trait in the meta-data for the tag in this test.
			try {
				wrapper = 
					getDocumentContext("/WebContent/testCheckModeOperation.jsp", 365);

				String prefix = "cmo";
				String localName = "testTagOne";
				ITagConverter tagConverter = 
					getTagConverter(wrapper, prefix, localName, IConverterFactory.MODE_DESIGNER);
				assertNotNull(tagConverter);

				// invoke ITagConverter instance
				tagConverter.convertRefresh(null);

				// get and test result element
				Element resultElement = tagConverter.getResultElement();
				assertNotNull(resultElement);
				assertEquals("div", resultElement.getLocalName());

				// Ensure correct child element was copied to child node list.
				// First child is text node, look at second child.
				List<?> childNodes = tagConverter.getChildModeList();
				assertTrue(childNodes.size() > 1);
				Node secondChildNode = (Node)childNodes.get(1);
				assertTrue(secondChildNode instanceof Element);
				Element secondChildElement = (Element)secondChildNode;
				assertEquals("span", secondChildElement.getLocalName());
				assertEquals("t1ChildSpan", secondChildElement.getAttribute("id"));

				// now try in preview mode
				tagConverter = 
					getTagConverter(wrapper, prefix, localName, IConverterFactory.MODE_PREVIEW);
				assertNotNull(tagConverter);

				// invoke ITagConverter instance
				tagConverter.convertRefresh(null);

				// get and test result element
				resultElement = tagConverter.getResultElement();
				assertNull(resultElement);
			} finally {
				if (wrapper != null) {
					wrapper.dispose();
				}
			}

			// Second tag converter has the CheckModeOperation
			// as a sibling operation for additional transforms.
			// Exercises both design and preview mode.
			try {
				wrapper = 
					getDocumentContext("/WebContent/testCheckModeOperation.jsp", 531);

				String prefix = "cmo";
				String localName = "testTagTwo";

				// test conversion for design mode
				ITagConverter tagConverter = 
					getTagConverter(wrapper, prefix, localName, IConverterFactory.MODE_DESIGNER);
				assertNotNull(tagConverter);

				// invoke ITagConverter instance
				tagConverter.convertRefresh(null);

				// get and test result element
				Element resultElement = tagConverter.getResultElement();
				assertNotNull(resultElement);
				assertEquals("div", resultElement.getLocalName());
				assertEquals("designId", resultElement.getAttribute("id"));

				// now try in preview mode
				tagConverter = 
					getTagConverter(wrapper, prefix, localName, IConverterFactory.MODE_PREVIEW);
				assertNotNull(tagConverter);

				// invoke ITagConverter instance
				tagConverter.convertRefresh(null);

				// get and test result element
				resultElement = tagConverter.getResultElement();
				assertNotNull(resultElement);
				assertEquals("div", resultElement.getLocalName());
				assertEquals("previewId", resultElement.getAttribute("id"));
			} finally {
				if (wrapper != null) {
					wrapper.dispose();
				}
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}


	/**
	 * Test "ConvertAttributeToTextOperation".
	 */
	public void testConvertAttributeToTextOperation() {
		try {
			//get and test result element
			Element resultElement = getResultElement(620, "h", "commandLink");
			assertNotNull(resultElement);
			assertEquals("a", resultElement.getLocalName());

			//check first child Text exists and has correct value
			Node firstChildNode = resultElement.getFirstChild();
			assertTrue(firstChildNode instanceof Text);
			Text firstChildText = (Text)firstChildNode;
			assertEquals("commandLink value", firstChildText.getData());

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}

	/**
	 * Test "CopyAllAttributesOperation".
	 */
	public void testCopyAllAttributesOperation() {
		try {
			//get and test result element
			Element resultElement = getResultElement(620, "h", "commandLink");
			assertNotNull(resultElement);
			assertEquals("a", resultElement.getLocalName());

			//check attributes exist and have correct values
			String attr1Value = resultElement.getAttribute("attr1");
			assertEquals("attr1_value", attr1Value);
			String attr2Value = resultElement.getAttribute("attr2");
			assertEquals("attr2_value", attr2Value);

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}

	/**
	 * Test "CopyAttributeOperation".
	 */
	public void testCopyAttributeOperation() {
		try {
			//get and test result element
			Element resultElement = getResultElement(477, "h", "selectManyListbox");
			assertNotNull(resultElement);
			assertEquals("select", resultElement.getLocalName());

			//check first child node exists and is correct node
			Node firstChildNode = resultElement.getFirstChild();
			assertTrue(firstChildNode instanceof Element);
			Element firstChildElement = (Element)firstChildNode;
			assertEquals("option", firstChildElement.getLocalName());

			//check first child node has correct attribute with correct value
			String valueAttrValue = firstChildElement.getAttribute("value");
			assertEquals("selectItem_1", valueAttrValue);

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}

	/**
	 * Test "CopyChildrenOperation".
	 */
	public void testCopyChildrenOperation() {
		ContextWrapper wrapper = null;
		try {
			wrapper = getDocumentContext("/WebContent/Test_TransformOperations.jsp", 620);
			ITagConverter tagConverter = 
				getTagConverter(wrapper, "h", "commandLink", IConverterFactory.MODE_DESIGNER);
            assertNotNull(tagConverter);

            //invoke ITagConverter instance
            tagConverter.convertRefresh(null);

            //test tag converter to ensure correct child element was copied to child node list
            List<?> childNodes = tagConverter.getChildModeList();
            assertTrue(childNodes.size() > 1);
            Node secondChildNode = (Node)childNodes.get(1);
            assertTrue(secondChildNode instanceof Element);
            Element secondChildElement = (Element)secondChildNode;
            assertEquals("f", secondChildElement.getPrefix());
            assertEquals("actionListener", secondChildElement.getLocalName());

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		} finally {
			if (wrapper != null) {
				wrapper.dispose();
			}
		}
	}

	/**
	 * Test "CreateAttributeFromXPathOperation".
	 */
	public void testCreateAttributeFromXPathOperation() {
		try {
			//get and test result element
			Element resultElement = getResultElement(477, "h", "selectManyListbox");
			assertNotNull(resultElement);
			assertEquals("select", resultElement.getLocalName());

			//check size attribute has been calculated and set
			String sizeAttrValue = resultElement.getAttribute("size");
			assertEquals("2", sizeAttrValue);

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}

	/**
	 * Test "CreateAttributeOperation".
	 */
	public void testCreateAttributeOperation() {
		try {
			//get and test result element
			Element resultElement = getResultElement(477, "h", "selectManyListbox");
			assertNotNull(resultElement);
			assertEquals("select", resultElement.getLocalName());

			//check multiple attribute has been set
			String multipleAttrValue = resultElement.getAttribute("multiple");
			assertEquals("multiple", multipleAttrValue);

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}

	/**
	 * Test "CreateElementOperation".
	 */
	public void testCreateElementOperation() {
		try {
			//get and test result element
			Element resultElement = getResultElement(477, "h", "selectManyListbox");
			assertNotNull(resultElement);
			assertEquals("select", resultElement.getLocalName());

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}

	/**
	 * Test "IfNotOperation".
	 */
	public void testIfNotOperation() {
		try {
			//get and test result element
			Element resultElement = getResultElement(477, "h", "selectManyListbox");
			assertNotNull(resultElement);
			assertEquals("select", resultElement.getLocalName());

			//check size attribute has been calculated and set (only occurs if the IfNotOperation is successful)
			String sizeAttrValue = resultElement.getAttribute("size");
			assertEquals("2", sizeAttrValue);

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}

	/**
	 * Test "IfOperation".
	 */
	public void testIfOperation() {
		try {
			//get and test result element
			Element resultElement = getResultElement(477, "h", "selectManyListbox");
			assertNotNull(resultElement);
			assertEquals("select", resultElement.getLocalName());

			//check first child node exists and is correct node
			Node firstChildNode = resultElement.getFirstChild();
			assertTrue(firstChildNode instanceof Element);
			Element firstChildElement = (Element)firstChildNode;
			assertEquals("option", firstChildElement.getLocalName());

			//check grandchild Text exists and has correct value (only occurs if the IfOperation is successful)
			Node firstGrandChildNode = firstChildElement.getFirstChild();
			assertTrue(firstGrandChildNode instanceof Text);
			Text firstGrandChildText = (Text)firstGrandChildNode;
			assertEquals("selectItem One", firstGrandChildText.getData());

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}

	/**
	 * Test "IterateOverElementsOperation".
	 */
	public void testIterateOverElementsOperation() {
		try {
			//get and test result element
			Element resultElement = getResultElement(477, "h", "selectManyListbox");
			assertNotNull(resultElement);
			assertEquals("select", resultElement.getLocalName());

			//check two child nodes exist and are correct nodes
			NodeList childNodes = resultElement.getChildNodes();
			assertEquals(2, childNodes.getLength());
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node childNode = childNodes.item(i);
				assertTrue(childNode instanceof Element);
				Element childElement = (Element)childNode;
				assertEquals("option", childElement.getLocalName());
			}

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}

	/**
	 * Test "MakeParentElementCurrentOperation".
	 */
	public void testMakeParentElementCurrentOperation() {
		try {
			//get and test result element
			Element resultElement = getResultElement(477, "h", "selectManyListbox");
			assertNotNull(resultElement);
			assertEquals("select", resultElement.getLocalName());

			//check two child nodes exist and are correct nodes (only occurs if the MakeParentElementCurrentOperation is successful)
			NodeList childNodes = resultElement.getChildNodes();
			assertEquals(2, childNodes.getLength());
			for (int i = 0; i < childNodes.getLength(); i++) {
				Node childNode = childNodes.item(i);
				assertTrue(childNode instanceof Element);
				Element childElement = (Element)childNode;
				assertEquals("option", childElement.getLocalName());
			}

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}

	/**
	 * Test "RemoveAttributeOperation".
	 */
	public void testRemoveAttributeOperation() {
		try {
			//get and test result element
			Element resultElement = getResultElement(477, "h", "selectManyListbox");
			assertNotNull(resultElement);
			assertEquals("select", resultElement.getLocalName());

			//check multiple attribute has been set
			String multipleAttrValue = resultElement.getAttribute("multiple");
			assertEquals("multiple", multipleAttrValue);

			//remove multiple attribute and assert it no longer exists
			ITransformOperation removeAttrOp =
				TransformOperationFactory.getInstance().getTransformOperation(
						TransformOperationFactory.OP_RemoveAttributeOperation,
						new String[]{"multiple"});
			removeAttrOp.transform(null, resultElement);
			Node multipleAttrNode = resultElement.getAttributeNode("multiple");
			assertNull(multipleAttrNode);

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}

	/**
	 * Test "RenameAttributeOperation".
	 */
	public void testRenameAttributeOperation() {
		try {
			//get and test result element
			Element resultElement = getResultElement(477, "h", "selectManyListbox");
			assertNotNull(resultElement);
			assertEquals("select", resultElement.getLocalName());

			//check first child node exists and is correct node
			Node firstChildNode = resultElement.getFirstChild();
			assertTrue(firstChildNode instanceof Element);
			Element firstChildElement = (Element)firstChildNode;
			assertEquals("option", firstChildElement.getLocalName());

			//check first child node has correct attribute with correct value
			String valueAttrValue = firstChildElement.getAttribute("value");
			assertEquals("selectItem_1", valueAttrValue);

		} catch(Exception ex) {
			ex.printStackTrace();
			fail(ex.getLocalizedMessage());
		}
	}

	private Element getResultElement(int docOffset, String prefix, String localName) throws Exception {
		Element resultElement = null;
		ContextWrapper wrapper = null;
		try {
			wrapper = getDocumentContext("/WebContent/Test_TransformOperations.jsp", docOffset);
			ITagConverter tagConverter =
				getTagConverter(wrapper, prefix, localName, IConverterFactory.MODE_DESIGNER);

			if (tagConverter != null) {
				//invoke ITagConverter instance
				tagConverter.convertRefresh(null);
	
				//get result element
				resultElement = tagConverter.getResultElement();
			} else {
				System.out.printf("%s getTagConverter(...) returned null for %s:%s at document offset %d\n", OUTPUT_PREFIX, prefix, localName, docOffset);
			}
		} finally {
			if (wrapper != null) {
				wrapper.dispose();
			}
		}		
		return resultElement;
	}

	private ITagConverter getTagConverter(ContextWrapper wrapper, String prefix,
			String localName, int mode) throws Exception {
		ITagConverter tagConverter = null;
		IStructuredDocumentContext context = wrapper.getContext();
		IDOMContextResolver resolver =
			IStructuredDocumentContextResolverFactory.INSTANCE.
				getDOMContextResolver(context);

		//get Element
		Node node = resolver.getNode();
		if (node instanceof Element
				&& node.getPrefix().equals(prefix)
				&& node.getLocalName().equals(localName)) {

			Element element = (Element)node;

			//get IDOMDocument
			IStructuredModel model = wrapper.getModel();
			if (model instanceof IDOMModel) {
				IDOMDocument document = ((IDOMModel)model).getDocument();
				if (document != null) {
	
					//get ITagConverter instance
					tagConverter = DTManager.getInstance().getTagConverter(
							element,
							mode,
							document);
				} else {
					System.out.printf("%s model.getDocument() returned null for %s:%s\n", OUTPUT_PREFIX, prefix, localName);
				}
			} else {
				System.out.printf("%s wrapper.getModel() returned unexpected result for %s:%s\n", OUTPUT_PREFIX, prefix, localName);
			}
		} else {
			if (node instanceof Element) {
				if (node.getPrefix().equals(prefix)) {
					if (!node.getLocalName().equals(localName)) {
						System.out.printf("%s resolver.getNode().getLocalName() is incorrect for %s:%s\n", OUTPUT_PREFIX, prefix, localName);
					}
				} else {
					System.out.printf("%s resolver.getNode().getPrefix() is incorrect for %s:%s\n", OUTPUT_PREFIX, prefix, localName);
				}
			} else {
				if (node != null) {
					if (node instanceof Text) {
						System.out.printf("%s resolver.getNode() returned Text (\"%s\") instead of Element for %s:%s\n", OUTPUT_PREFIX, ((Text)node).getNodeValue(), prefix, localName);
					} else {
						System.out.printf("%s resolver.getNode() returned %s (when Element was expected) for %s:%s\n", OUTPUT_PREFIX, node.getClass().getName(), prefix, localName);
					}
				} else {
					System.out.printf("%s resolver.getNode() returned null for %s:%s\n", OUTPUT_PREFIX, prefix, localName);
				}
			}
		}

		return tagConverter;
	}

    private ContextWrapper getDocumentContext(String path, int offset) throws Exception {
        IProject project = webProjectTestEnv.getTestProject();
        IFile jspFile = project.getFile(new Path(path));
        assertTrue(jspFile.exists());
        final IModelManager modelManager = 
            StructuredModelManager.getModelManager();
        IStructuredModel model = null;
        model = modelManager.getModelForRead(jspFile);
        assertTrue(model instanceof DOMModelForJSP);
        final IStructuredDocumentContext context =
        	IStructuredDocumentContextFactory.INSTANCE.getContext(
        			model.getStructuredDocument(),
        			offset);
        return new ContextWrapper(context, model);
    }

    private static class ContextWrapper {
        private final IStructuredDocumentContext context;
        private final IStructuredModel model;
        ContextWrapper(final IStructuredDocumentContext context, final IStructuredModel model) {
            super();
            this.context = context;
            this.model = model;
        }
        IStructuredDocumentContext getContext() {
            return context;
        }
        IStructuredModel getModel() {
            return model;
        }
        void dispose() {
            model.releaseFromRead();
        }
    }
}
