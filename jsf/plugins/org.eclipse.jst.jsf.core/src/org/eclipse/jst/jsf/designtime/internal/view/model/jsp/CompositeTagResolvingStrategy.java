package org.eclipse.jst.jsf.designtime.internal.view.model.jsp;

import org.eclipse.jst.jsf.common.internal.policy.IIdentifiable;
import org.eclipse.jst.jsf.common.internal.policy.IIteratorPolicy;
import org.eclipse.jst.jsf.common.internal.strategy.IteratorPolicyBasedStrategyComposite;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;

/**
 * @author cbateman
 * 
 * @param <TLDELEMENT>
 */
public class CompositeTagResolvingStrategy<TLDELEMENT>
        extends
        IteratorPolicyBasedStrategyComposite<TLDELEMENT, ITagElement, String, ITagResolvingStrategy<TLDELEMENT, String>>
        implements IIdentifiable<String>,
        ITagResolvingStrategy<TLDELEMENT, String>
{
    private static final String ID = "org.eclipse.jst.jsf.designtime.CompositeTagResolvingStrategy";

    /**
     * @param policy
     * 
     */
    public CompositeTagResolvingStrategy(final IIteratorPolicy<String> policy)
    {
        super(policy);
    }

    public final String getId()
    {
        return ID;
    }

    public final String getDisplayName()
    {
        return "Composite Tag Resolving Strategies";
    }

    @Override
    public ITagElement getNoResult()
    {
        return null;
    }

    public final ITagElement getNotFoundIndicator()
    {
        return getNoResult();
    }

    public final ITagElement resolve(TLDELEMENT element)
    {
        return perform(element);
    }
}