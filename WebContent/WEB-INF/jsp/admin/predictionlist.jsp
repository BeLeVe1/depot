<!-- 此例子是结合bootstrap的Datatables，暂且定位为最基本的例子吧 -->
<!-- 引入必须的css和js文件 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!-- <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" /> 
<link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.css" />  -->
<script src="js/Hui-js/lib/jquery.js"></script>
<script src="js/Hui-js/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="js/Hui-js/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css"
	href="static/h-ui.admin/css/style.css" />
<script type="text/javascript" src="js/Hui-js/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="js/Hui-js/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
<!--/_footer /作为公共模版分离出去-->
<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="js/Hui-js/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="js/Hui-js/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="js/Hui-js/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript"></script>
<!-- HTML代码片段中请勿添加<body>标签 //-->
</head>
<body>
	<div id="container">
		<div class="text-c" style="margin-top:1%;">
			<input type="text" name="jqcode" id="jqcode" placeholder="战区编号" style="width:100px" class="input-text">
			<input type="text" name="bdcode" id="bdcode" placeholder="部队编号" style="width:100px" class="input-text">
			<input type="text" name="zbcode" id="zbcode" placeholder="装备代码" style="width:100px" class="input-text">
			<input type="text" name="qccode" id="qccode" placeholder="器材代码" style="width:100px" class="input-text">
			<input type="text" name="qcname" id="qcname" placeholder="器材名称" style="width:100px" class="input-text">
			<button onclick="selectbutton();" class="btn btn-success" style="background-color: #337AB7;" type="submit"></i><i class="Hui-iconfont">&#xe665;</i> 查询</button>
		</div>
		<div class="text-c" style="margin-top:1%;">
			<button onclick="Excelbutton();" class="btn btn-success" style="background-color: #337AB7;" type="submit"></i><i class="Hui-iconfont">&#xe644;</i> 备份</button>
			<button onclick="returnbutton();" class="btn btn-success" style="background-color: #337AB7;" type="submit"></i><i class="Hui-iconfont">&#xe68f;</i> 刷新</button>
		</div>
		<!-- 定义一个表格元素 -->
		<div style="height: 10px"></div>
		<div style="width: 98%; margin-left: 10px">
			<table id="example" class="table table-striped table-bordered">
				<thead>
					<tr>
						<th style="text-align: center;width: 1%">序号</th>
						<th style="text-align: center;width: 1%"><input type="checkbox" onclick="ckAll();" id="allChecks" name="" value="" style="zoom:150%;"></th>
						<th style="text-align: center;width: 1%">战区编号</th>
						<th style="text-align: center;width: 1%">部队编号</th>
						<th style="text-align: center;width: 1%">装备代码</th>
						<th style="text-align: center;width: 1%">器材代码</th>
						<th style="text-align: center;width: 1%">器材名称</th>
						<th style="text-align: center;width: 1%">预测方法</th>
						<th style="text-align: center;width: 1%">预测值（去年消耗）<input type="checkbox" onclick="checkbtn1();" id="ycz9" name="ycz9" value=""></th>
						<th style="text-align: center;width: 1%">标准差（去年消耗）<input type="checkbox" onclick="checkbtn2();" id="bzc9" name="bzc9" value=""></th>
						<th style="text-align: center;width: 1%">真实值（去年消耗）<input type="checkbox" onclick="checkbtn3();" id="zsz9" name="zsz9" value=""></th>
						<th style="text-align: center;width: 1%">预测值（今年消耗）<input type="checkbox" onclick="checkbtn4();" id="ycz10" name="ycz10" value=""></th>
						<th style="text-align: center;width: 1%">标准差（今年消耗）<input type="checkbox" onclick="checkbtn5();" id="bzc10" name="bzc10" value=""></th>
						<th style="text-align: center;width: 1%">真实值（今年消耗）<input type="checkbox" onclick="checkbtn6();" id="zsz10" name="zsz10" value=""></th>
						<th style="text-align: center;width: 1%">预测值（明年消耗）<input type="checkbox" onclick="checkbtn7();" id="ycz11" name="ycz11" value=""></th>
						<th style="text-align: center;width: 1%">预测值（后年消耗）<input type="checkbox" onclick="checkbtn8();" id="ycz12" name="ycz12" value=""></th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
<script type="text/javascript">	
/*Javascript代码片段*/
$(document).ready(selectbutton());

function  open_layer(title,url,w,h){  
	
		layer_show(title,url,w,h);
		
		 }
function  reload(){  
	 var index = layer.getFrameIndex(window.name);
	 layer.close(index);
	 location.reload();
	 }
//全选
function ckAll(){
    var flag=document.getElementById("allChecks").checked;
    var cks=document.getElementsByName("check");
    for(var i=0;i<cks.length;i++){
        cks[i].checked=flag;
    }
}
function selectbutton(){
	var sort="";
	if(document.getElementById("ycz9").checked==true){
		sort="ycz9";
	}
	if(document.getElementById("bzc9").checked==true){
		sort="bzc9";
	}
	if(document.getElementById("zsz9").checked==true){
		sort="zsz9";
	}
	if(document.getElementById("ycz10").checked==true){
		sort="ycz10";
	}
	if(document.getElementById("bzc10").checked==true){
		sort="bzc10";
	}
	if(document.getElementById("zsz10").checked==true){
		sort="zsz10";
	}
	if(document.getElementById("ycz11").checked==true){
		sort="ycz11";
	}
	if(document.getElementById("ycz12").checked==true){
		sort="ycz12";
	}
	var jqcode=$("#jqcode").val();
	var bdcode=$("#bdcode").val();
	var zbcode=$("#zbcode").val();
	var qccode=$("#qccode").val();
	var qcname=$("#qcname").val();
	$(document).ready(function() {
		$('#example').dataTable().fnDestroy();
	    $('#example').DataTable( {
	       "ajax":{
	       	  "url": "adminpredictionlistajax",
	             "type": "post",
	             "data": {jqcode:jqcode,bdcode:bdcode,zbcode:zbcode,qccode:qccode,qcname:qcname,sort:sort}
	           },
	           "lengthChange": true,//是否允许用户自定义显示数量
	           "bPaginate": true, //翻页功能
	           "bFilter": false, //列筛序功能
	           "searching": false,//本地搜索
	           "ordering": false, //排序功能
	           "Info": true,//页脚信息
	           "autoWidth": true,//自动宽度
		        "serverSide":true, 
		        /* "bLengthChange":false, */
		        "fnDrawCallback" : function(){
		        	     　　this.api().column(0).nodes().each(function(cell, i) {
		        	        　　　　cell.innerHTML =  i + 1;
		        	      　　});
		                }, 
		        "columns": [        
				 	{ "data": null,"targets": 0}, 
				 	{ "data": function(obj){return "<span><center><input type='checkbox' value='"+obj.dataprocess_id+"' name='check' style='zoom:150%;'></center></span>"}},
				 	{ "data": "jqcode" },
			    	{ "data": "bdcode" },
				 	{ "data": "zbcode" },
		            { "data": "qccode" },
		            { "data": "qcname" },
		            { "data": "ycffname" },
		            { "data": "ycz9" },
		            { "data": "bzc9" },
		            { "data": "zsz9" },
		            { "data": "ycz10" },
		            { "data": "bzc10" },
		            { "data": "zsz10" },
		            { "data": "ycz11" },
		            { "data": "ycz12" }
		            
		            ],
		        "columnDefs": [
		                       {
		                          
		                       }
		                   ],
		        "oLanguage": {    // 语言设置  
		        	"sProcessing": "处理中...",
		            "sLengthMenu": "显示 _MENU_ 项结果",
		            "sZeroRecords": "没有匹配结果",
		            "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
		            "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
		            "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
		            "sInfoPostFix": "",
		            "sUrl": "",
		            "sEmptyTable": "表中数据为空",
		            "sLoadingRecords": "载入中...",
		            "sInfoThousands": ",",
		            "oPaginate": {
		                "sFirst": "首页",
		                "sPrevious": "上页",
		                "sNext": "下页",
		                "sLast": "末页"
		          }
		        }
	    });
	});
	
}
function checkbtn1(){
	if(document.getElementById("ycz9").checked==true){
		document.getElementById("bzc9").checked=false;
		document.getElementById("zsz9").checked=false;
		document.getElementById("ycz10").checked=false;
		document.getElementById("bzc10").checked=false;
		document.getElementById("zsz10").checked=false;
		document.getElementById("ycz11").checked=false;
		document.getElementById("ycz12").checked=false;
		selectbutton();
	}else{
		selectbutton();
	}
}
function checkbtn2(){
	if(document.getElementById("bzc9").checked==true){
		document.getElementById("ycz9").checked=false;
		document.getElementById("zsz9").checked=false;
		document.getElementById("ycz10").checked=false;
		document.getElementById("bzc10").checked=false;
		document.getElementById("zsz10").checked=false;
		document.getElementById("ycz11").checked=false;
		document.getElementById("ycz12").checked=false;
		selectbutton();
	}else{
		selectbutton();
	}
}
function checkbtn3(){
	if(document.getElementById("zsz9").checked==true){
		document.getElementById("ycz9").checked=false;
		document.getElementById("bzc9").checked=false;
		document.getElementById("ycz10").checked=false;
		document.getElementById("bzc10").checked=false;
		document.getElementById("zsz10").checked=false;
		document.getElementById("ycz11").checked=false;
		document.getElementById("ycz12").checked=false;
		selectbutton();
	}else{
		selectbutton();
	}
}
function checkbtn4(){
	if(document.getElementById("ycz10").checked==true){
		document.getElementById("ycz9").checked=false;
		document.getElementById("bzc9").checked=false;
		document.getElementById("zsz9").checked=false;
		document.getElementById("bzc10").checked=false;
		document.getElementById("zsz10").checked=false;
		document.getElementById("ycz11").checked=false;
		document.getElementById("ycz12").checked=false;
		selectbutton();
	}else{
		selectbutton();
	}
}
function checkbtn5(){
	if(document.getElementById("bzc10").checked==true){
		document.getElementById("ycz9").checked=false;
		document.getElementById("bzc9").checked=false;
		document.getElementById("zsz9").checked=false;
		document.getElementById("ycz10").checked=false;
		document.getElementById("zsz10").checked=false;
		document.getElementById("ycz11").checked=false;
		document.getElementById("ycz12").checked=false;
		selectbutton();
	}else{
		selectbutton();
	}
}
function checkbtn6(){
	if(document.getElementById("zsz10").checked==true){
		document.getElementById("ycz9").checked=false;
		document.getElementById("bzc9").checked=false;
		document.getElementById("zsz9").checked=false;
		document.getElementById("ycz10").checked=false;
		document.getElementById("bzc10").checked=false;
		document.getElementById("ycz11").checked=false;
		document.getElementById("ycz12").checked=false;
		selectbutton();
	}else{
		selectbutton();
	}
}
function checkbtn7(){
	if(document.getElementById("ycz11").checked==true){
		document.getElementById("ycz9").checked=false;
		document.getElementById("bzc9").checked=false;
		document.getElementById("zsz9").checked=false;
		document.getElementById("ycz10").checked=false;
		document.getElementById("bzc10").checked=false;
		document.getElementById("zsz10").checked=false;
		document.getElementById("ycz12").checked=false;
		selectbutton();
	}else{
		selectbutton();
	}
}
function checkbtn8(){
	if(document.getElementById("ycz12").checked==true){
		document.getElementById("ycz9").checked=false;
		document.getElementById("bzc9").checked=false;
		document.getElementById("zsz9").checked=false;
		document.getElementById("ycz10").checked=false;
		document.getElementById("bzc10").checked=false;
		document.getElementById("zsz10").checked=false;
		document.getElementById("ycz11").checked=false;
		selectbutton();
	}else{
		selectbutton();
	}
}
function returnbutton(){
	location.reload();
}
function Excelbutton(){
	$.ajax({
	    type: "POST",
	    url:'adminpredictionExcel',
	    success: function(data) {
		    if(data.code==100){
		    	/* window.location.href="http://test.jikewangluo.cn/upload/预测查询.xls"; */
		    	window.location.href="http://localhost:8081/depot/upload/预测查询.xls";
		    }else{	
				layer.msg('系统错误，请联系后台管理员');	
		    }
	    }
	});
}

</script>
</body>
</html>
