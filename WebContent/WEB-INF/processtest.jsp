<%--
  Created by IntelliJ IDEA.
  User: 84593
  Date: 2023/5/12
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script type="text/javascript">
  var FakeProgress = require("fake-progress");

  // Create the fake progress with a timeConstant of 10 seconds
  // it means that :
  //  after 10 seconds, progress will be 0.6321 ( = 1-Math.exp(-1) )
  //  after 20 seconds, progress will be 0.8646 ( = 1-Math.exp(-2) )
  //  and so on
  var p = new FakeProgress({
    timeConstant : 10000,
    autoStart : true
  });

  var exampleAsyncFunction = function(callback){
    setTimeout(function(){
      callback()
    },30000)
  };

  var onEachSecond = function(){
    console.log("Progress is "+(p.progress*100).toFixed(1)+" %");
  };

  var interval = setInterval(onEachSecond, 1000);

  var onEnd = function(){
    p.end();
    clearInterval(interval);
    console.log("Ended. Progress is "+(p.progress*100).toFixed(1)+" %")
  };

  exampleAsyncFunction(onEnd);
</script>
</body>
</html>
