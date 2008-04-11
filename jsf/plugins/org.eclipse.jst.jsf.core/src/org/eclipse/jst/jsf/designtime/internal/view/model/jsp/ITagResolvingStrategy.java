package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.internal.strategy.IIdentifiableStrategy;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;

/**
 * Describes an algorithm that can produce ITagElements from <TLD> objects.
 * 
 * @author cbateman
 * @param <TLDELEMENT> the object type that describes a tag library.
 * @param <IDTYPE> 
 * 
 */
public interface ITagResolvingStrategy<TLDELEMENT, IDTYPE> extends 
    IIdentifiableStrategy<TLDELEMENT, ITagElement, IDTYPE>
{
    /**
     * @param element
     * @return a new tag element or {@link #getNotFoundIndicator()} if not found
     */
    ITagElement resolve(TLDELEMENT element);

    /**
     * @return the ITagElement (may be null) that indicates that
     *         resolve(TLDELEMENT) could not resolve a tag.  The return
     *         must be testable with standard "==" operator and not equals().
     */
    ITagElement getNotFoundIndicator();
}