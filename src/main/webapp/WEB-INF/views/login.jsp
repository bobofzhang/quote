<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	xmlns:fn="http://java.sun.com/jsp/jstl/functions"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt"
	xmlns:form="http://www.springframework.org/tags/form"
	xmlns:spring="http://www.springframework.org/tags"
	xmlns:sec="http://www.springframework.org/security/tags"
	xmlns:tags="urn:jsptagdir:/WEB-INF/tags" version="2.0">
<jsp:directive.page contentType="text/html" pageEncoding="UTF-8" />
<jsp:output omit-xml-declaration="true" />
<jsp:output doctype-root-element="HTML"
	doctype-system="about:legacy-compat" />
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="" />
<title><spring:message code="login" /></title>
<c:url var="bootstrapUrl" value="/assets/css/bootstrap.css" />
<link href="${bootstrapUrl}" rel="stylesheet" />
<c:url var="cssUrl" value="/assets/css/login.css" />
<link href="${cssUrl}" rel="stylesheet" />
<c:url var="faviconUrl" value="/assets/ico/favicon.ico" />
<link href="${faviconUrl}" rel="shortcut icon" />
</head>
<body>
	<div class="container">
		<div data-bind="visible: showErrorMessage" class="alert alert-danger">
			<spring:message code="loginFailed" />
		</div>
		<c:url var="loginUrl" value="/login" />
		<form class="form-signin" method="post" action="${loginUrl}">
			<h3 class="text-muted form-signin-heading">
				<spring:message code="login" />
			</h3>
			<spring:message code="username" var="username" />
			<input type="text" class="form-control" placeholder="${username}"
				name="username" autofocus="autofocus" />
			<spring:message code="password" var="password" />
			<input type="password" class="form-control" placeholder="${password}"
				name="password" /> <label class="checkbox"> <input
				type="checkbox" name="remember-me" /> <spring:message
					code="rememberMe" />
			</label>
			<button class="btn btn-large btn-primary btn-block" type="submit">
				<spring:message code="login" />
			</button>
			<hr />
			<p class="text-info">
				<small><spring:message code="loginInfo" /></small>
			</p>
		</form>
	</div>
	<!-- /container -->
	<c:url var="knockoutUrl" value="/assets/js/knockout-2.3.0.js" />
	<script type="text/javascript" src="${knockoutUrl}"><!-- knockout --></script>
	<script type="text/javascript">
    <![CDATA[
    ko.applyBindings({
      showErrorMessage : ko.computed(function() {
        var query = window.location.search;
        return query ? (query.indexOf('error') != -1) : false;
      }),
    });
    ]]>
  </script>
</body>
</html>
</jsp:root>