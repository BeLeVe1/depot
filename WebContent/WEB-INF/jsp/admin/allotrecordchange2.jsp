<!-- 此例子是结合bootstrap的Datatables，暂且定位为最基本的例子吧 -->
<!-- 引入必须的css和js文件 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="js/Hui-js/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="js/Hui-js/lib/jquery/jquery-easyui-1.4.5/themes/icon.css" />
<link id="easyuiTheme" rel="stylesheet" type="text/css" href="js/Hui-js/lib/jquery/jquery-easyui-1.4.5/themes/ui-pepper-grinder/easyui.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<!--/meta 作为公共模版分离出去-->
<script language="javascript" type="text/javascript" src="js/Hui-js/lib/jquery/jquery-easyui-1.4.5/jquery.min.js"></script>
<script language="javascript" type="text/javascript" src="js/Hui-js/lib/jquery/jquery-easyui-1.4.5/jquery.easyui.min.js"></script>
<script language="javascript" type="text/javascript" src="js/Hui-js/lib/jquery/jquery-easyui-1.4.5/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="js/Hui-js/lib/layer/2.4/layer.js"></script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<title>调拨记录修改</title>
</head>
<body style="width: 80%;padding-left:10%;">
	<form  method="post" class="form form-horizontal" id="forms"  enctype="multipart/form-data">
		<div class="row cl" style="padding-top:2%;display:none;">
			<label class="form-label col-xs-4 col-sm-2">dataprocess：</label>
			<div class="formControls col-xs-7 col-sm-9">
				<input type="text" class="input-text" value="${allot_id}" placeholder="" id="allot_id" name="allot_id">
			</div>
			<div class="formControls col-xs-7 col-sm-9">
				<input type="text" class="input-text" value="${sum_allot_number}" placeholder="" id="sum_allot_number" name="sum_allot_number">
			</div>
			<div class="formControls col-xs-7 col-sm-9">
				<input type="text" class="input-text" value="${old_allot_number}" placeholder="" id="old_allot_number" name="old_allot_number">
			</div>
		</div>
		<div class="row cl" style="padding-top:2%;">
			<label class="form-label col-xs-4 col-sm-3">战区编号：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="text" class="input-text" readonly="readonly" value="${jqcode}" placeholder="" id="jqcode" name="jqcode">
			</div>
		</div><div class="row cl" style="padding-top:2%;">
			<label class="form-label col-xs-4 col-sm-3">部队编号：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="text" class="input-text" readonly="readonly" value="${bdcode}" placeholder="" id="bdcode" name="bdcode">
			</div>
		</div>
<!-- 		<div class="row cl" style="padding-top:2%;"> -->
<!-- 			<label class="form-label col-xs-4 col-sm-3">调出仓库编号：</label> -->
<!-- 			<div class="formControls col-xs-7 col-sm-8"> -->
<%-- 				<input type="text" class="input-text" readonly="readonly" value="${from_store}" placeholder="" id="from_store" name="from_store"> --%>
<!-- 			</div>	 -->
<!-- 		</div> -->
		<div class="row cl" style="padding-top:2%;">
			<label class="form-label col-xs-4 col-sm-3">器材编号：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="text" class="input-text" readonly="readonly" value="${qccode}" placeholder="" id="qccode" name="qccode">
			</div>
		</div>
		<div class="row cl" style="padding-top:2%;">
			<label class="form-label col-xs-4 col-sm-3">器材名称：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="text" class="input-text" readonly="readonly" value="${qcname}" placeholder="" id="qcname" name="qcname">
			</div>
		</div>
		<div class="row cl" style="padding-top:2%;">
			<label class="form-label col-xs-4 col-sm-3">当次调拨数量：</label>
			<div class="formControls col-xs-7 col-sm-8">
				<input type="text" class="input-text" value="${this_allot_number}" placeholder="" id="this_allot_number" name="this_allot_number">
			</div>
		</div>
	<div class="mt-20 text-c">
		<input class="btn btn-primary radius" type="button" id="Btn" onClick="sub();" value="&nbsp;&nbsp;提交&nbsp;&nbsp;  ">
	</div>
	</form>


<script>
window.PROJECT_CONTEXT = "${ctx}/";
</script>

<script type="text/javascript">


function sub(){
	
	var params = $("#forms").serialize();
	
	if(forms.this_allot_number.value==""){
    	layer.msg('请输入调拨数量！');
    	return false;
    }
    $.ajax( {  
        type : 'POST',  
        url : 'adminallotrecordchangeajax',  
        data : params, 
        success : function(data) {
        	if(data.code==100){
        		alert("提交成功");
        		//layer.msg(data.info);  
    			parent.reload();
        	}else{
        		layer.msg(data.info);
        	}
		}  
    }); 
}

</script> 

<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>