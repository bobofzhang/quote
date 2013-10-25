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
	<jsp:output doctype-root-element="HTML" doctype-system="about:legacy-compat" />
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate, max-age=0" />
<c:url var="faviconUrl" value="/assets/ico/favicon.ico" />
<link href="${faviconUrl}" rel="shortcut icon" />	
<title><spring:message code="quote" /></title>
<c:url var="bootstrapCss" value="/assets/lib/bootstrap/css/bootstrap.css" />
<link href="${bootstrapCss}" rel="stylesheet" />
<c:url var="portfolioCss" value="/assets/css/quote.css" />
<link href="${portfolioCss}" rel="stylesheet" />
<c:url var="html5shivUrl" value="/assets/lib/html5shiv/html5shiv.js" />
<c:url var="respondUrl" value="/assets/lib/respond/respond.min.js" />
<!--[if lt IE 9]>
    <script src="${html5shivUrl}"></script>
    <script src="${respondUrl}"></script>
<![endif]-->
</head>
<body>
    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="icon-bar"><!--  --></span>
            <span class="icon-bar"><!--  --></span>
            <span class="icon-bar"><!--  --></span>
          </button>
          <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="#contact">Contact</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"><!--  --></b></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
          </ul>
          <form class="navbar-form navbar-right">
            <div class="form-group">
              <input type="text" placeholder="Email" class="form-control" />
            </div>
            <div class="form-group">
              <input type="password" placeholder="Password" class="form-control" />
            </div>
            <button type="submit" class="btn btn-success">Sign in</button>
          </form>
        </div><!--/.navbar-collapse -->
      </div>
    </div>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1>Hello, world!</h1>
        <p>This is a template for a simple marketing or informational website. It includes a large callout called the hero unit and three supporting pieces of content. Use it as a starting point to create something more unique.</p>
        <p><a class="btn btn-primary btn-lg">Learn more</a></p>
      </div>
    </div>

    <div class="container">
      <!-- Example row of columns -->
      <div class="row">
        <div class="col-lg-4">
          <h2>Heading</h2>
          <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
          <p><a class="btn btn-default" href="#">View details</a></p>
        </div>
        <div class="col-lg-4">
          <h2>Heading</h2>
          <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
          <p><a class="btn btn-default" href="#">View details</a></p>
       </div>
        <div class="col-lg-4">
          <h2>Heading</h2>
          <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
          <p><a class="btn btn-default" href="#">View details</a></p>
        </div>
      </div>

      <hr />

      <footer>
        <p>Company 2013</p>
      </footer>
    </div> <!-- /container -->




	<!-- 3rd party -->
	<c:url var="jqueryUrl" value="/assets/lib/jquery/jquery-2.0.3.min.js" />
	<script src="${jqueryUrl}"><!-- jquery --></script>
	<c:url var="bootstrapUrl" value="/assets/lib/bootstrap/bootstrap.min.js" />
	<script src="${bootstrapUrl}"><!-- bootstrap --></script>
	<c:url var="typeaheadUrl" value="/assets/lib/typeahead/typeahead.min.js" />
	<script src="${typeaheadUrl}"><!-- typeahead --></script>
    
</body>
	</html>
</jsp:root>
