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
<meta http-equiv="Cache-Control"
	content="no-store, no-cache, must-revalidate, max-age=0" />
<title><spring:message code="portfolio" /></title>
<c:url var="bootstrapCss" value="/assets/lib/bootstrap/css/bootstrap.css" />
<link href="${bootstrapCss}" rel="stylesheet" />
<c:url var="portfolioCss" value="/assets/css/portfolio.css" />
<link href="${portfolioCss}" rel="stylesheet" />
<c:url var="faviconUrl" value="/assets/ico/favicon.ico" />
<link href="${faviconUrl}" rel="shortcut icon" />
</head>
<body>
	<div class="container">
		<div id="heading" class="masthead">
			<div class="pull-right">
				<c:url var="logoutUrl" value="/logout" />
				<form:form class="form-inline" action="${logoutUrl}" method="post">
				  <span class="text-info" data-bind="text: username"><em><!--  --></em></span>&amp;nbsp;
                  <button class="btn btn-danger" type="submit"><i class="glyphicon glyphicon-off"><!--  --></i></button>
                </form:form>
			</div>
			<h3 class="text-muted"><spring:message code="portfolio" /></h3>
		</div>
		<div id="main-content">
			<table class="table table-striped">
				<thead>
					<tr>
						<th><spring:message code="company" /></th>
						<th><spring:message code="ticker" /></th>
						<th class="number"><spring:message code="price" /></th>
						<th class="number"><spring:message code="change" /></th>
						<th>%</th>
						<th></th>
					</tr>
				</thead>
				<tbody data-bind="foreach: portfolio().rows">
					<tr>
						<td data-bind="text: company"></td>
						<td data-bind="text: ticker"></td>
						<td data-bind="text: formattedPrice" class="number"></td>
						<td
							data-bind="text: change, style: {color: change() &lt; 0 ? 'green' : 'red'}"
							class="number"></td>
						<td data-bind="html: arrow" class="icon"></td>
						<td class="trade-buttons">
							<button class="btn btn-primary"
								data-bind="click: $root.trend().showTrend"><spring:message code="trend" /></button>
						</td>
					</tr>
				</tbody>
				<tfoot>
				</tfoot>
			</table>
		</div>
	</div>

	<div id="trend-dialog" class="modal fade" tabindex="-1" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&amp;times;</button>
					<h4 class="modal-title"><span data-bind="text: trend().currentRow().company"></span></h4>
				</div>
				<div class="modal-body">
					<div id="chart" style="height: 400px; min-width: 500px"></div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="close" />­</button>
				</div>
			</div>
		</div>
	</div>


	<!-- 3rd party -->
	<c:url var="jqueryUrl" value="/assets/lib/jquery/jquery-2.0.3.min.js" />
	<script src="${jqueryUrl}"><!-- jQuery --></script>
	<c:url var="bootstrapUrl" value="/assets/lib/bootstrap/bootstrap.min.js" />
	<script src="${bootstrapUrl}"><!-- bootstrap --></script>
	<c:url var="knockoutUrl" value="/assets/lib/knockout/knockout-2.3.0.js" />
	<script src="${knockoutUrl}"><!-- knockout --></script>
	<c:url var="sockjsUrl" value="/assets/lib/sockjs/sockjs-0.3.4.min.js" />
	<script src="${sockjsUrl}"><!-- sockjs --></script>
	<c:url var="stompUrl" value="/assets/lib/stomp/stomp.min.js" />
	<script src="${stompUrl}"><!-- stomp --></script>
	<c:url var="highstockUrl" value="/assets/lib/highchart/highstock.js" />
	<script src="${highstockUrl}"><!-- highstock --></script>

	<!-- application -->
	<c:url var="portfolioUrl" value="/assets/js/portfolio.js" />
	<script src="${portfolioUrl}"><!-- portfolio.js --></script>
	<c:url var="websocketUrl" value="/portfolio" />
	<script type="text/javascript">
		(function() {
			var socket = new SockJS('${websocketUrl}');
			var stompClient = Stomp.over(socket);

			var appModel = new ApplicationModel(stompClient);
			ko.applyBindings(appModel);

			appModel.connect();
		})();
	</script>
</body>
</html>
</jsp:root>
