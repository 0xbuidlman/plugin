package it.pmx.atlassian.plugins.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.ApplicationProperties;

import it.pmx.atlassian.plugins.api.PeopleManagementExtensionsComponent;

@ExportAsService({ PeopleManagementExtensionsComponent.class })
@Named("testComponent")
public class TestComponent implements PeopleManagementExtensionsComponent {

	@ComponentImport
	private final ApplicationProperties applicationProperties;

	@Inject
	public TestComponent(final ApplicationProperties applicationProperties) {
		this.applicationProperties = applicationProperties;
	}

	@Override
	public String getName() {
		if (null != applicationProperties) {
			return "myComponent:" + applicationProperties.getDisplayName();
		}

		return "myComponent";
	}
}