package it.pmx.atlassian.plugins.field;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.issue.customfields.impl.AbstractSingleFieldType;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.persistence.PersistenceFieldType;

/**
 * 
 * @author
 *
 */
public class RatingCustomField extends AbstractSingleFieldType<BigDecimal> {

	private static final Logger log = LoggerFactory.getLogger(RatingCustomField.class);

	public RatingCustomField(CustomFieldValuePersister customFieldValuePersister, GenericConfigManager genericConfigManager) {
		super(customFieldValuePersister, genericConfigManager);
	}

	@Override
	protected PersistenceFieldType getDatabaseType() {
		return PersistenceFieldType.TYPE_LIMITED_TEXT;
	}

	@Override
	protected Object getDbValueFromObject(final BigDecimal customFieldObject) {
		return getStringFromSingularObject(customFieldObject);
	}

	@Override
	protected BigDecimal getObjectFromDbValue(final Object databaseValue) throws FieldValidationException {
		return getSingularObjectFromString((String) databaseValue);
	}

	@Override
	public String getStringFromSingularObject(final BigDecimal singularObject) {
		if (singularObject == null)
			return "";
		// format
		return singularObject.toString();
	}

	@Override
	public BigDecimal getSingularObjectFromString(final String string) throws FieldValidationException {
		if (string == null)
			return null;
		try {
			final BigDecimal decimal = new BigDecimal(string);
			// Check that we don't have too many decimal places
			if (decimal.scale() > 2) {
				throw new FieldValidationException("Maximum of 2 decimal places are allowed.");
			}
			return decimal.setScale(2);
		}
		catch (NumberFormatException ex) {
			throw new FieldValidationException("Not a valid number.");
		}
	}
}