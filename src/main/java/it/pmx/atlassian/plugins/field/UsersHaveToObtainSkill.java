package it.pmx.atlassian.plugins.field;

import com.atlassian.jira.bc.user.search.UserSearchService;
import com.atlassian.jira.config.properties.ApplicationProperties;
import com.atlassian.jira.issue.customfields.converters.MultiUserConverter;
import com.atlassian.jira.issue.customfields.impl.MultiUserCFType;
import com.atlassian.jira.issue.customfields.manager.GenericConfigManager;
import com.atlassian.jira.issue.customfields.persistence.CustomFieldValuePersister;
import com.atlassian.jira.issue.fields.rest.json.UserBeanFactory;
import com.atlassian.jira.issue.fields.rest.json.beans.JiraBaseUrls;
import com.atlassian.jira.security.JiraAuthenticationContext;
import com.atlassian.jira.web.FieldVisibilityManager;

public class UsersHaveToObtainSkill extends MultiUserCFType {

	public UsersHaveToObtainSkill(CustomFieldValuePersister customFieldValuePersister,
			GenericConfigManager genericConfigManager, MultiUserConverter multiUserConverter,
			ApplicationProperties applicationProperties, JiraAuthenticationContext authenticationContext,
			UserSearchService searchService, FieldVisibilityManager fieldVisibilityManager, JiraBaseUrls jiraBaseUrls,
			UserBeanFactory userBeanFactory) {
		super(customFieldValuePersister, genericConfigManager, multiUserConverter, applicationProperties, authenticationContext,
				searchService, fieldVisibilityManager, jiraBaseUrls, userBeanFactory);
		// TODO Auto-generated constructor stub
	}
	
}