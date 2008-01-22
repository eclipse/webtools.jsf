package org.eclipse.jst.jsf.designtime.tests.views.model.jsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.common.runtime.tests.model.RuntimeTestUtil;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.JSPTagResolvingStrategy;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.TagIntrospectingStrategy;
import org.eclipse.jst.jsf.designtime.tests.views.model.jsp.VerifyRegistryUtil.ConverterTagVerifier;
import org.eclipse.jst.jsf.designtime.tests.views.model.jsp.VerifyRegistryUtil.ValidatorTagVerifier;
import org.eclipse.jst.jsf.designtime.tests.views.model.jsp.VerifyRegistryUtil.Verifier;

public class TestTagIntrospectingStrategy extends BaseStrategyTestClass
{
    @Override
    protected JSPTagResolvingStrategy createStrategy()
    {
        return new TagIntrospectingStrategy(_webProjectTestEnv.getTestProject());
    }

    public void testGetNotFoundIndicator()
    {
        assertNull(_strategy.getNotFoundIndicator());
    }

    public void testGetId()
    {
        assertEquals("org.eclipse.jst.jsf.designtime.TagIntrospectingStrategy",
                _strategy.getId());
    }

    @Override
    protected List<String> getTestUris()
    {
        return Collections.unmodifiableList(Arrays.asList(new String[]
        { ITLDConstants.URI_JSF_CORE, ITLDConstants.URI_JSF_HTML }));
    }

    @Override
    protected List<Verifier> createVerifiers(
            final Map<String, ITagElement> tagElements, final String uri)
    {
        if (ITLDConstants.URI_JSF_CORE.equals(uri))
        {
            return createCoreVerifiers(_jsfVersion, tagElements);
        }
        return super.createVerifiers(tagElements, uri);
    }

    @Override
    protected ExpectedTagCount getExpectedTagCount(String uri)
    {
        if (ITLDConstants.URI_JSF_CORE.equals(uri))
        {
            switch (_jsfVersion)
            {
                case V1_0:
                case V1_1:
                    return new ExpectedTagCount(14, 15);
                case V1_2:
                    return new ExpectedTagCount(13, 15);
                default:
                    throw new IllegalStateException(_jsfVersion.toString());
            }
        }
        // default
        return super.getExpectedTagCount(uri);
    }

    static List<Verifier> createCoreVerifiers(final JSFVersion jsfVersion,
            final Map<String, ITagElement> tagElements)
    {
        // default for JSF 1.2 where we can resolve converters and validators
        // using tags
        ConverterTypeInfo dateTimeConverterTypeInfo = ConverterTypeInfo.UNKNOWN;
        ConverterTypeInfo numberConverterTypeInfo = ConverterTypeInfo.UNKNOWN;
        ValidatorTypeInfo doubleRangeValidatorTypeInfo = ValidatorTypeInfo.UNKNOWN;
        ValidatorTypeInfo lengthValidatorTypeInfo = ValidatorTypeInfo.UNKNOWN;
        ValidatorTypeInfo longRangeValidatorTypeInfo = ValidatorTypeInfo.UNKNOWN;

        if (jsfVersion == JSFVersion.V1_2)
        {
            assertTrue("Size was " + tagElements.size()
                    + " but expected [13,15]",
                    tagElements.size() >= 13 && tagElements.size() <= 15);
            // the JSF 1.2 impl doesn't resolve converters and validators

            // MyFaces resolves extra.

            if (tagElements.size() == 15)
            {
                doubleRangeValidatorTypeInfo = RuntimeTestUtil.VALIDATORINFO_DOUBLERANGE;
                lengthValidatorTypeInfo = RuntimeTestUtil.VALIDATORINFO_LENGTH;
                longRangeValidatorTypeInfo = RuntimeTestUtil.VALIDATORINFO_LONGRANGE;
            }
        }
        else
        {
            // ri doesn't respect the getComponentType contract so MyFaces
            // will resolve 15, ri only 14
            assertTrue("Size was " + tagElements.size()
                    + " but expected [14,15]", tagElements.size() >= 14
                    && tagElements.size() <= 15);
            dateTimeConverterTypeInfo = RuntimeTestUtil.CONVERTERINFO_DATETIME;
            numberConverterTypeInfo = RuntimeTestUtil.CONVERTERINFO_NUMBER;
            doubleRangeValidatorTypeInfo = RuntimeTestUtil.VALIDATORINFO_DOUBLERANGE;
            lengthValidatorTypeInfo = RuntimeTestUtil.VALIDATORINFO_LENGTH;
            longRangeValidatorTypeInfo = RuntimeTestUtil.VALIDATORINFO_LONGRANGE;
        }
        final List<Verifier> verifiers = new ArrayList<Verifier>();
        if (jsfVersion != JSFVersion.V1_2)
        {
            verifiers.add(VerifyRegistryUtil.ATTRIBUTE_VERIFIER);
        }

        verifiers.add(new ConverterTagVerifier(
                IJSFConstants.TAG_IDENTIFIER_CONVERTDATETIME,
                dateTimeConverterTypeInfo));
        verifiers.add(VerifyRegistryUtil.CONVERTER_VERIFIER);
        verifiers.add(new ConverterTagVerifier(
                IJSFConstants.TAG_IDENTIFIER_CONVERTNUMBER,
                numberConverterTypeInfo));
        verifiers.add(VerifyRegistryUtil.FACET_VERIFIER);
        verifiers.add(VerifyRegistryUtil.PARAM_VERIFIER);
        verifiers.add(VerifyRegistryUtil.SELECTITEM_VERIFIER);
        verifiers.add(VerifyRegistryUtil.SELECTITEMS_VERIFIER);
        verifiers.add(VerifyRegistryUtil.SUBVIEW_VERIFIER);
        verifiers.add(new ValidatorTagVerifier(
                IJSFConstants.TAG_IDENTIFIER_VALIDATEDOUBLERANGE,
                doubleRangeValidatorTypeInfo));
        verifiers.add(new ValidatorTagVerifier(
                IJSFConstants.TAG_IDENTIFIER_VALIDATELENGTH,
                lengthValidatorTypeInfo));
        verifiers.add(new ValidatorTagVerifier(
                IJSFConstants.TAG_IDENTIFIER_VALIDATELONGRANGE,
                longRangeValidatorTypeInfo));
        verifiers.add(VerifyRegistryUtil.VALIDATOR_VERIFIER);
        verifiers.add(VerifyRegistryUtil.VERBATIM_VERIFIER);

        return verifiers;
    }

}
