package org.eclipse.jst.jsf.common.internal.resource;

import java.util.List;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;

/**
 * A resource singleton object manager that manages singletons that are aware
 * of the the resources they are singletons for.
 * 
 * @author cbateman
 * @param <RESTYPE> 
 *
 */
public abstract class ResourceManager<RESTYPE extends IResource> extends
        ResourceSingletonObjectManager<ResourceTracker<RESTYPE>, IResource>
{
    /**
     * @param workspace
     */
    public ResourceManager(IWorkspace workspace)
    {
        super(workspace);
    }

    public void dispose()
    {
        super.dispose();
    }

    /**
     * @param listener
     */
    public void addListener(final IResourceLifecycleListener listener)
    {
        super.addLifecycleEventListener(listener);
    }

    /**
     * @param listener
     */
    public void removeListener(final IResourceLifecycleListener listener)
    {
        super.removeLifecycleEventListener(listener);
    }
    
    /* (non-Javadoc)
     * @see org.eclipse.jst.jsf.common.internal.resource.ResourceSingletonObjectManager#createNewInstance(org.eclipse.core.resources.IResource)
     */
    protected abstract ResourceTracker<RESTYPE> createNewInstance(final IResource resource);

    /**
     * Initialize any state about the current resources we are managing.
     */
    public abstract void initResources();

    /**
     * @return the current list of resources being managed.
     */
    public abstract List<RESTYPE> getResources();

}