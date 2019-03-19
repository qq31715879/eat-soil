<html>
<head>
    <!-- Required Stylesheets -->
    <link href="./static/resource/css/bootstrap.css" rel="stylesheet">
    <!-- Required Javascript -->
    <script src="./static/resource/js/jquery-3.3.1.min.js"></script>
    <script src="./static/resource/js/bootstrap-treeview.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<div id="tree"></div>
<script>
    function getTree() {
        // Some logic to retrieve, or generate tree structure
        return data;
    }
    $('#tree').treeview({data: getTree()});
</script>
</body>
</html>
