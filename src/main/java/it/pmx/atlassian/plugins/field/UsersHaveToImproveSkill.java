package it.pmx.atlassian.plugins.field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.atlassian.jira.issue.customfields.impl.TextCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.customfields.converters.MultiUserConverter;
import com.atlassian.jira.issue.customfields.impl.AbstractMultiCFType;
import com.atlassian.jira.issue.customfields.impl.AbstractSingleFieldType;
import com.atlassian.jira.issue.customfields.impl.FieldValidationException;
import com.atlassian.jira.issue.customfields.impl.MultiUserCFType;
import com.atlassian.jira.bc.user.search.UserSearchService;
import com.atlassian.jira.config.properties.ApplicationProperties;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.fields.CustomField;
import com.atlassian.jira.issue.fields.config.FieldConfig;
import com.atlassian.jira.issue.fields.layout.field.FieldLayoutItem;
import com.atlassian.jira.issue.fields.rest.json.UserBeanFactory;
import com.atlassian.jira.issue.fields.rest.json.beans.JiraBaseUrls;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.FieldVisibilityManager;

import java.util.List;
import java.util.Map;

public class UsersHaveToImproveSkill extends MultiUserCFType {
	
    public UsersHaveToImproveSkill(CustomFieldValuePersister customFieldValuePersister,
			GenericConfigManager genericConfigManager, MultiUserConverter multiUserConverter,
			ApplicationProperties applicationProperties, JiraAuthenticationContext authenticationContext,
			UserSearchService searchService, FieldVisibilityManager fieldVisibilityManager, JiraBaseUrls jiraBaseUrls,
			UserBeanFactory userBeanFactory) {
		super(customFieldValuePersister, genericConfigManager, multiUserConverter, applicationProperties, authenticationContext,
				searchService, fieldVisibilityManager, jiraBaseUrls, userBeanFactory);
		// TODO Auto-generated constructor stub
	}

	private static final Logger log = LoggerFactory.getLogger(UsersHaveToImproveSkill.class);
    
}