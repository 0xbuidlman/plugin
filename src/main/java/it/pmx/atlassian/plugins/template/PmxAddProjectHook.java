package it.pmx.atlassian.plugins.template;

import java.util.Collection;

import com.atlassian.jira.ComponentManager;
import com.atlassian.jira.component.ComponentAccessor;
import com.atlassian.jira.issue.link.IssueLinkType;
import com.atlassian.jira.issue.link.IssueLinkTypeManager;
import com.atlassian.jira.project.template.hook.AddProjectHook;
import com.atlassian.jira.project.template.hook.ConfigureData;
import com.atlassian.jira.project.template.hook.ConfigureResponse;
import com.atlassian.jira.project.template.hook.ValidateData;
import com.atlassian.jira.project.template.hook.ValidateResponse;

public class PmxAddProjectHook implements AddProjectHook {
	
	private String LINK_TYPE_NAME = "Required Skills";

	@Override
	public ValidateResponse validate(final ValidateData validateData) {
		ValidateResponse validateResponse = ValidateResponse.create();
		if (validateData.projectKey().equals("SKILL")) {
			validateResponse.addErrorMessage("Invalid Project Key");
		}

		return validateResponse;
	}

	@Override
	public ConfigureResponse configure(final ConfigureData configureData) {
		
		IssueLinkTypeManager issueLinkTypeManager = (IssueLinkTypeManager)
				ComponentManager.getComponentInstanceOfType(IssueLinkTypeManager.class);
		
		Collection<IssueLinkType> it = issueLinkTypeManager.getIssueLinkTypesByName(LINK_TYPE_NAME);
		if ( (it==null) || it.isEmpty() ) {
			issueLinkTypeManager.createIssueLinkType(LINK_TYPE_NAME, "Requires", "Is required by", "require-skill");
		}
		
		ConfigureResponse configureResponse = ConfigureResponse.create().setRedirect("/issues/");
		return configureResponse;
	}
}