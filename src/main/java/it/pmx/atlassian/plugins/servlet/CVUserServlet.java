package it.pmx.atlassian.plugins.servlet;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.atlassian.jira.bc.issue.IssueService;
import com.atlassian.jira.bc.issue.search.SearchService;
import com.atlassian.jira.bc.project.ProjectService;
import com.atlassian.jira.issue.Issue;
import com.atlassian.jira.issue.search.SearchException;
import com.atlassian.jira.jql.builder.JqlClauseBuilder;
import com.atlassian.jira.jql.builder.JqlQueryBuilder;
import com.atlassian.jira.user.ApplicationUser;
import com.atlassian.jira.web.bean.PagerFilter;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.atlassian.sal.api.auth.LoginUriProvider;
import com.atlassian.sal.api.user.UserManager;
import com.atlassian.sal.api.user.UserProfile;
import com.atlassian.templaterenderer.TemplateRenderer;
import com.google.common.collect.Maps;

@Named("userCVServlet")
public class CVUserServlet extends HttpServlet {

	private static final String CV_PROFILE_VM_VIEW = "view/profile/cv-profile.vm";

	/**
	 * 
	 */
	private static final long serialVersionUID = -7389830290380037840L;

	private static final Logger log = LoggerFactory.getLogger(CVUserServlet.class);

	@ComponentImport
	private final UserManager userManager;
	@ComponentImport
	private final LoginUriProvider loginUriProvider;
	@ComponentImport
	private final TemplateRenderer templateRenderer;
	@ComponentImport
	private IssueService issueService;
	@ComponentImport
	private ProjectService projectService;
	@ComponentImport
	private SearchService searchService;
	@ComponentImport
	private TemplateRenderer renderer;

	private com.atlassian.jira.user.util.UserManager jiraUserManager;

	@Inject
	public CVUserServlet(UserManager userManager, LoginUriProvider loginUriProvider, TemplateRenderer templateRenderer, IssueService issueService, ProjectService projectService, SearchService searchService,
			com.atlassian.jira.user.util.UserManager jiraUserManager) {
		this.userManager = userManager;
		this.loginUriProvider = loginUriProvider;
		this.templateRenderer = templateRenderer;
		this.issueService = issueService;
		this.projectService = projectService;
		this.searchService = searchService;
		this.renderer = templateRenderer;
		this.jiraUserManager = jiraUserManager;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserProfile userProfile = userManager.getRemoteUser(req);
		if (userProfile == null || !userManager.isSystemAdmin(userProfile.getUserKey())) {
			redirectToLogin(req, resp);
			return;
		}

		// Render the list of issues
		List<Issue> issues = getIssues(req);

		log.debug("ISSUES: " + issues);

		Map<String, Object> context = Maps.newHashMap();
		context.put("issues", issues);

		templateRenderer.render(CV_PROFILE_VM_VIEW, context, resp.getWriter());
	}

	/**
	 * Returns a list of issues.
	 *
	 * @param req
	 * @return
	 */
	private List<Issue> getIssues(HttpServletRequest req) {
		// User is required to carry out a search
		ApplicationUser user = jiraUserManager.getUser(userManager.getRemoteUsername(req));

		// search issues

		// The search interface requires JQL clause... so let's build one
		JqlClauseBuilder jqlClauseBuilder = JqlQueryBuilder.newClauseBuilder();
		// Our JQL clause is simple project="TUTORIAL"
		com.atlassian.query.Query query = jqlClauseBuilder.project("SKILL").buildQuery();
		// A page filter is used to provide pagination. Let's use an unlimited filter to
		// to bypass pagination.
		PagerFilter pagerFilter = PagerFilter.getUnlimitedFilter();
		com.atlassian.jira.issue.search.SearchResults searchResults = null;
		try {
			// Perform search results
			searchResults = searchService.search(user, query, pagerFilter);
		}
		catch (SearchException e) {
			e.printStackTrace();
		}
		// return the results
		return searchResults.getIssues();
	}

	private void redirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(loginUriProvider.getLoginUri(getUri(request)).toASCIIString());
	}

	private URI getUri(HttpServletRequest request) {
		StringBuffer builder = request.getRequestURL();
		if (request.getQueryString() != null) {
			builder.append("?");
			builder.append(request.getQueryString());
		}
		return URI.create(builder.toString());
	}

}
