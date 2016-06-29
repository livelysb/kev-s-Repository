<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="ko-kr">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Home</title>

<link href="resources/css/zuplay-green.css" rel="stylesheet">
<link href="resources/css/jquery-ui.min.css" rel="stylesheet">
<link href="resources/css/zuplay.css" rel="stylesheet">

<style type="text/css">
	#searchBar {float: right}
	.itemImg {width:100%;}
</style>
</head>
	<body class='default'>
    <div id='jqxWidget'>
        <div id='jqxTabs'>
            <ul>
                <li style="margin-left: 30px;">구매</li>
                <li>판매목록</li>
            </ul>
            <div>
                Node.js is an event-driven I/O server-side JavaScript environment based on V8. It
                is intended for writing scalable network programs such as web servers. It was created
                by Ryan Dahl in 2009, and its growth is sponsored by Joyent, which employs Dahl.
                Similar environments written in other programming languages include Twisted for
                Python, Perl Object Environment for Perl, libevent for C and EventMachine for Ruby.
                Unlike most JavaScript, it is not executed in a web browser, but is instead a form
                of server-side JavaScript. Node.js implements some CommonJS specifications. Node.js
                includes a REPL environment for interactive testing.
            </div>
            <div>
                JavaServer Pages (JSP) is a Java technology that helps software developers serve
                dynamically generated web pages based on HTML, XML, or other document types. Released
                in 1999 as Sun's answer to ASP and PHP,[citation needed] JSP was designed to address
                the perception that the Java programming environment didn't provide developers with
                enough support for the Web. To deploy and run, a compatible web server with servlet
                container is required. The Java Servlet and the JavaServer Pages (JSP) specifications
                from Sun Microsystems and the JCP (Java Community Process) must both be met by the
                container.
            </div>
        </div>   
        <div id='settings'>
            <div id='animation'>
                Enable Select Animation</div>
             <div id='contentAnimation'>
                Enable Content Animation</div>
        </div>
        
    </div>
</body>

<script src="resources/js/jquery-2.2.4.min.js"></script>
<script src="resources/js/naverLogin_implicit-1.0.2.js"></script>
<script src="resources/js/jquery-ui.min.js"></script>
<script src="resources/js/jquery.cookie.js"></script>
<script src="resources/js/zuplay.js"></script>

<script src="resources/js/demos.js"></script>
<script src="resources/js/jqwidgets/jqxcheckbox.js"></script>
<script src="resources/js/jqwidgets/jqxcore.js"></script>
<script src="resources/js/jqwidgets/jqxtabs.js"></script>
<script type="text/javascript">
$(document).ready(function () {
    // Create jqxTabs.
    $('#jqxTabs').jqxTabs({ width: '90%', height: 200, position: 'top'});
    $('#settings div').css('margin-top', '10px');
    $('#animation').jqxCheckBox({ theme: theme });
    $('#contentAnimation').jqxCheckBox({ theme: theme });
    $('#animation').on('change', function (event) {
        var checked = event.args.checked;
        $('#jqxTabs').jqxTabs({ selectionTracker: checked });
    });
   
    $('#contentAnimation').on('change', function (event) {
        var checked = event.args.checked;
        if (checked) {
            $('#jqxTabs').jqxTabs({ animationType: 'fade' });
        }
        else {
            $('#jqxTabs').jqxTabs({ animationType: 'none' });
        }
    });
});
</script> 
</html> 








