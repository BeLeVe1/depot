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
			<input type="text" name="sparepartsNum" id="sparepartsNum" placeholder="存货编号" style="width:100px" class="input-text">
			<input type="text" name="sparepartsName" id="sparepartsName" placeholder="存货名称" style="width:100px" class="input-text">
			<input type="text" name="sparepartsSpecification" id="sparepartsSpecification" placeholder="规格型号" style="width:100px" class="input-text">
			<input type="text" name="sparepartsType" id="sparepartsType" placeholder="存货代码" style="width:100px" class="input-text">
			<input type="text" name="warehouseNum" id="warehouseNum" placeholder="仓库编码" style="width:100px" class="input-text">
			<input type="text" name="warehouseName" id="warehouseName" placeholder="仓库" style="width:100px" class="input-text">
			<input type="text" name="warehouseFreight" id="warehouseFreight" placeholder="货位" style="width:100px" class="input-text">

			<input type="text" name="customerName" id="customerName" placeholder="客户名称" style="width:100px" class="input-text">
			<input type="text" name="ContractNum" id="ContractNum" placeholder="合同号" style="width:100px" class="input-text">
			<input type="text" name="ContractName" id="ContractName" placeholder="合同名称" style="width:100px" class="input-text">
			<input type="text" name="outdate" id="outdate" placeholder="出库日期" style="width:100px" class="input-text">
			<input type="text" name="auditor" id="auditor" placeholder="审核人" style="width:100px" class="input-text">
			<input type="text" name="auditTime" id="auditTime" placeholder="审核时间" style="width:100px" class="input-text">
			<input type="text" name="modifiedName" id="modifiedName" placeholder="修改人" style="width:100px" class="input-text">
			<input type="text" name="modifiedDate" id="modifiedDate" placeholder="修改日期" style="width:100px" class="input-text">
			<input type="text" name="modifiedTime" id="modifiedTime" placeholder="修改时间" style="width:100px" class="input-text">
			<input type="text" name="preparationTime" id="preparationTime" placeholder="制单时间" style="width:100px" class="input-text">






				<!-- <input type="text" name="fg" id="fg" placeholder="分工" style="width:100px" class="input-text"> -->
			<button onclick="selectbutton();" class="btn btn-success" style="background-color: #337AB7;" type="submit"></i><i class="Hui-iconfont">&#xe665;</i> 查询</button>
		</div>
		<div class="text-c" style="margin-top:1%;">
			<button onclick="addbutton();" class="btn btn-success" style="background-color: #337AB7;" type="submit"></i><i class="Hui-iconfont">&#xe644;</i> 添加出库记录</button>
			<button onclick="returnbutton();" class="btn btn-success" style="background-color: #337AB7;" type="submit"></i><i class="Hui-iconfont">&#xe68f;</i> 刷新</button>
		</div>
		<!-- 定义一个表格元素 -->
		<div style="height: 10px"></div>
		<div style="width: 98%; margin-left: 10px">
			<table id="example" class="table table-striped table-bordered">
				<thead>
					<tr>

						<th style="text-align: center;width: 1%">序号</th>
						<!--<th style="text-align: center;width: 1%">出库单编号</th>-->
						<th style="text-align: center;width: 1%">存货编号</th>
						<th style="text-align: center;width: 1%">存货名称</th>
						<th style="text-align: center;width: 1%">规格型号</th>
						<th style="text-align: center;width: 1%">存货代码</th>

						<th style="text-align: center;width: 1%">仓库编码</th>
						<th style="text-align: center;width: 1%">仓库</th>
						<th style="text-align: center;width: 1%">货位</th>
						<th style="text-align: center;width: 1%">主计量单位</th>
						<th style="text-align: center;width: 1%">数量</th>
						<th style="text-align: center;width: 1%">单价</th>
						<th style="text-align: center;width: 1%">金额</th>
						<th style="text-align: center;width: 1%">客户名称</th>
						<th style="text-align: center;width: 1%">合同号</th>
						<th style="text-align: center;width: 1%">合同名称</th>
						<th style="text-align: center;width: 1%">出库日期</th>
						<th style="text-align: center;width: 1%">审核人</th>
						<th style="text-align: center;width: 1%">审核时间</th>
						<th style="text-align: center;width: 1%">修改人</th>
						<th style="text-align: center;width: 1%">修改日期</th>
						<th style="text-align: center;width: 1%">修改时间</th>
						<th style="text-align: center;width: 1%">制单时间</th>
						<th style="text-align: center;width: 1%">操作</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</div>
<script type="text/javascript">	
/*Javascript代码片段*/
$(document).ready(selectbutton());

function selectbutton(){
	var sort="";
	var sparepartsNum=$("#sparepartsNum").val();
	var sparepartsName=$("#sparepartsName").val();
	var sparepartsSpecification=$("#sparepartsSpecification").val();
	var sparepartsType=$("#sparepartsType").val();
	var warehouseNum=$("#warehouseNum").val();
	var 	warehouseName=$("#warehouseName").val();
	var 	warehouseFreight=$("#warehouseFreight").val();
	var 	customerName=$("#customerName").val();
	var 	ContractNum=$("#ContractNum").val();
	var 	ContractName=$("#ContractName").val();
	var 	outdate=$("#outdate").val();
	var 	auditor=$("#auditor").val();
	var 	auditTime=$("#auditTime").val();
	var 	modifiedName=$("#modifiedName").val();
	var 	modifiedDate=$("#modifiedDate").val();
	var 	modifiedTime=$("#modifiedTime").val();
	var 	preparationTime=$("#preparationTime").val();

	$(document).ready(function() {
		 $('#example').dataTable().fnDestroy();
	     $('#example').DataTable( {
	        "ajax":{
	        	  "url": "adminallotrecordajax",
	              "type": "post",
	              "data": {sparepartsNum:sparepartsNum,
					  sparepartsName:sparepartsName,
					  sparepartsSpecification:sparepartsSpecification,
					  sparepartsType:sparepartsType,
					  warehouseNum:warehouseNum,
					  warehouseName:warehouseName,
					  warehouseFreight:warehouseFreight,
					  customerName:customerName,
					  ContractNum:ContractNum,
					  ContractName:ContractName,
					  outdate:outdate,
					  auditor:auditor,
					  auditTime:auditTime,
					  modifiedName:modifiedName,
					  modifiedDate:modifiedDate,
					  modifiedTime:modifiedTime,
					  preparationTime:preparationTime,
					  sort:sort}
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
				 	{ "data": "outid"},
				 	{ "data": "sparepartsNum" },
				 	{ "data": "sparepartsName" },
				 	{ "data": "sparepartsSpecification" },
				 	{ "data": "sparepartsType" },

				 	{ "data": "warehouseNum" },
		            { "data": "warehouseName" },
		            { "data": "warehouseFreight" },
		            { "data": "sparepartsUnit" },
		            { "data": "quantity" },
		            { "data": "unitPrice" },
		            { "data": "price" },
		            { "data": "customerName" },
		            
		            { "data": "ContractNum" },
		            { "data": "ContractName" },
		            { "data": "outdate" },
		            { "data": "auditor" },
		            { "data": "auditTime" },
		            { "data": "modifiedName" },
		            { "data": "modifiedDate" },
		            { "data": "modifiedTime" },
					{ "data": "preparationTime" },
		            { "data": function(obj){
		            	
		            	return "<span><center><a onclick=\"open_layer('编辑','adminallotrecordedit?supplyplan_id="+obj.supplyplan_id+"&selfplan_id="+obj.selfplan_id+"&jqcode="+obj.jqcode+"&bdcode="+obj.bdcode+"&qccode="+obj.qccode+"&qcname="+obj.qcname+"&this_allot_number="+obj.this_allot_number+"&plan_supply_number="+obj.plan_supply_number+"&create_people="+obj.create_people+"&createtime="+obj.createtime+"&receive_people="+obj.receive_people+"&total_price="+obj.total_price+"&customer_name="+obj.customer_name+"&checker="+obj.checker+"&contract="+obj.contract+"&reviser="+obj.reviser+"&modification_date="+obj.modification_date+"&preparation_time="+obj.preparation_time+"','700','700')\">编辑</a>&nbsp;<a onclick=\"deleteobj("+obj.outid+")\">删除</a></span>"
	                }
		            }
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


function  open_layer(title,url,w,h){
	layer_show(title,url,w,h);
}  
function deleteobj(id){
	layer.confirm('确定要执行此删除操作吗?', {icon: 3, title:'提示'}, function(index){
		  $.ajax({
			    cache: true,
			    type: "POST",
			    url:'adminfd_outdeleteajax',
			    data:{id:id},
			    async: false,
			    error: function(request) {
			        layer.msg("连接错误，请联系后台管理员");
			    },
			    success: function(data) {
				    
			    	 layer.msg('删除成功..');
			    	 location.reload();
				   
			      
			    }
			});		  
		  layer.close(index);
		});
	  return false; 
}
 
function  reload(){  
	var index = layer.getFrameIndex(window.name);
	layer.close(index);
	location.reload();
}

function returnbutton(){
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

</script>
</body>
</html>
