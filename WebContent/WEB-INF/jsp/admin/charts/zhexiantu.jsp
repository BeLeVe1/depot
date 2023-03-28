<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<!DOCTYPE HTML>
<html>
<head>
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="js/Hui-js/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
<title>折线图</title>
</head>
<body>
<nav class="breadcrumb"><a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
	<br>
	<!-- <div class="f-14 c-error">特别声明：Highcharts 是一个用纯 JavaScript编写的一个图表库，仅免费提供给个人学习、个人网站，如果在商业项目中使用，请去Highcharts官网网站购买商业授权。或者您也可以选择其他免费的第三方图表插件，例如百度echarts。H-ui.admin不承担任何版权问题。</div> -->
	<div id="container" style="min-width:700px;height:300px"></div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="js/Hui-js/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="js/Hui-js/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="js/Hui-js/lib/hcharts/Highcharts/5.0.6/js/highcharts.js"></script>
<script type="text/javascript" src="js/Hui-js/lib/hcharts/Highcharts/5.0.6/js/modules/exporting.js"></script>
<script type="text/javascript">

var data1=[];
var data2=[];
var data3=[];
var data4=[];
var data5=[];
$.ajax({
    cache: true,
    type: "GET",
    url:'safetystockzhexiantuajax',
    data:{dataprocess_id:${dataprocess_id}},
    async: false,
	error: function(request) {
		alert("失败!");
	    },
	success: function(data) {
		//移动平均法
        jQuery.each(data.ydpjlist,function(i, item) {
        	data1.push(item);
        });
		//指数平滑法
		jQuery.each(data.zsphlist,function(i, item) {
			data2.push(item);
        });
		//线性回归法
		jQuery.each(data.xxhglist,function(i, item) {
			data3.push(item);
        });
		//误差平方和倒数组合预测
		jQuery.each(data.wcpflist,function(i, item) {
			data5.push(item);
        });
		//真实值
		jQuery.each(data.zszlist,function(i, item) {
			data4.push(item);
        });
    }
})

$(function () {
	Highcharts.chart('container', {
        title: {
            text: '信息统计折线图',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        }, 
        xAxis: {
            categories: ["年份1","年份2","年份3","年份4","年份5","年份6","年份7","年份8","年份9","年份10","明年预测","后年预测"],
        },
        yAxis: {
            title: {
                text: ''
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: ''
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '移动平均法',
            data: data1,
        }, {
            name: '指数平滑法',
            data: data2,
        }, {
            name: '线性回归法',
            data: data3,
        }, {
            name: '组合预测',
            data: data5,
        },{
            name: '真实值',
            data: data4,
        }]
    });
});

/* $(function () {
	Highcharts.chart('container', {
        title: {
            text: '信息统计折线图',
            x: -20 //center
        },
        subtitle: {
            text: '',
            x: -20
        }, 
        xAxis: {
            categories: datetime,
        },
        yAxis: {
            title: {
                text: ''
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: ''
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: '数据1',
            data: [1,2,3,4,5,6,7,8,9,10],
        }, {
            name: '数据2',
            data: [2,3,4,5,6,7,8,9,10,11],
        }, {
            name: '数据3',
            data: [3,4,5,6,7,8,9,10,11,12],
        }, {
            name: '数据4',
            data: [4,5,6,7,8,9,10,11,12,13],
        }, {
            name: '数据5',
            data: [13,12,13,14,15,13,17,3,19,3],
        }]
    });
}); */
</script>
</body>
</html>