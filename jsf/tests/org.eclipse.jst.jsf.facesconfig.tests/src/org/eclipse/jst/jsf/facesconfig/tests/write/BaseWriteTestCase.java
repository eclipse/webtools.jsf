package org.eclipse.jst.jsf.facesconfig.tests.write;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.facesconfig.emf.DynamicElement;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.tests.util.WizardUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
import org.eclipse.jst.jsf.test.util.ConfigurableTestCase;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;

public class BaseWriteTestCase extends ConfigurableTestCase 
{
    final static String CONFIG_FILE_KEY = "config-file-key";
    final static String FACES_VERSION_KEY = "faces-version-key";
    
    protected IProject  project;
    protected String    _facesConfigFile;
    protected String    _facesVersion;
    
    public BaseWriteTestCase(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception {
        super.setUp();
        
        initialize(_testConfiguration);

        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.uk.oracle.com", "80");

        WizardUtil.createProject(getName());
        project = WizardUtil.getTestProject(getName());
    }
    
    protected FacesConfigArtifactEdit getArtifactEditForRead()
    {
        FacesConfigArtifactEdit edit = FacesConfigArtifactEdit
                .getFacesConfigArtifactEditForRead(project, _facesConfigFile);
        assertNotNull(edit);
        return edit;
    }
    
    protected FacesConfigArtifactEdit getArtifactEditForWrite()
    {
        FacesConfigArtifactEdit edit = FacesConfigArtifactEdit
            .getFacesConfigArtifactEditForWrite(project, _facesConfigFile);
        assertNotNull(edit);
        return edit;        
    }

    protected void initialize(TestConfiguration testConfiguration)
    {
        if (_testConfiguration != null)
        {
            _facesConfigFile = (String) _testConfiguration.get(CONFIG_FILE_KEY);
            assertNotNull(_facesConfigFile);
            _facesVersion = (String) _testConfiguration.get(FACES_VERSION_KEY);
            assertNotNull(_facesVersion);
        }
    }
    
    protected DynamicElement createDynamicElement(String name)
    {
        DynamicElement element = 
            FacesConfigFactory.eINSTANCE.createDynamicElement();
        element.setName(name);
        return element;
    }
}
