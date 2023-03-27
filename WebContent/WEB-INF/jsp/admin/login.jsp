<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 
<c:set var="ctx" value="/spring3"/>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>国能思达备品备件库存管理信息系统</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <link rel="stylesheet" href="statics/css/bootstrap.min.css">
  <link rel="stylesheet" href="statics/css/font-awesome.min.css">
  <link rel="stylesheet" href="statics/css/AdminLTE.min.css">
  <!-- AdminLTE Skins. Choose a skin from the css/skins
       folder instead of downloading all of them to reduce the load. -->
  <link rel="stylesheet" href="statics/css/all-skins.min.css">
  <link rel="stylesheet" href="statics/css/main.css">
  <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body class="hold-transition login-page">
<div class="login-box" id="rrapp" v-cloak>
  <div class="login-logo">
    <b>国能思达备品备件库存管理信息系统</b>
  </div>
  <!-- /.login-logo -->
  <div class="login-box-body">
      <p class="login-box-msg">管理员登录</p>
      <div v-if="error" class="alert alert-danger alert-dismissible">
        <h4 style="margin-bottom: 0px;"><i class="fa fa-exclamation-triangle"></i> {{errorMsg}}</h4>
      </div>
      <div class="form-group has-feedback">
        <input type="text" class="form-control" v-model="username" placeholder="账号">
        <span class="glyphicon glyphicon-user form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="password" class="form-control" v-model="password" placeholder="密码">
        <span class="glyphicon glyphicon-lock form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <input type="text" class="form-control" v-model="captcha" @keyup.enter="login" placeholder="验证码">
        <span class="glyphicon glyphicon-warning-sign form-control-feedback"></span>
      </div>
      <div class="form-group has-feedback">
        <img alt="如果看不清楚，请单击图片刷新！" class="pointer" :src="src" @click="refreshCode">
        &nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:;" @click="refreshCode">点击刷新</a>
      </div>
      
      
      <div class="row">
        <div class="col-xs-8">
          <div class="checkbox icheck">
          </div>
        </div>
        <!-- /.col -->
        <div class="col-xs-4">
          <button type="button" class="btn btn-primary btn-block btn-flat" @click="login">登录</button>
        </div>
        <!-- /.col -->
      </div>
    <!-- /.social-auth-links -->

  </div>
  <!-- /.login-box-body -->
</div>
<!-- /.login-box -->
<script src="statics/libs/jquery.min.js"></script>
<script src="statics/libs/vue.min.js"></script>
<script src="statics/libs/bootstrap.min.js"></script>
<script src="statics/libs/jquery.slimscroll.min.js"></script>
<script src="statics/libs/fastclick.min.js"></script>
<script src="statics/libs/app.js"></script>
<script type="text/javascript">
var vm = new Vue({
	el:'#rrapp',
	data:{
		username: '',
		password: '',
		captcha: '',
		error: false,
		errorMsg: '',
		src: 'logincode'
	},
	beforeCreate: function(){
		if(self != top){
			top.location.href = self.location.href;
		}
	},
	methods: {
		refreshCode: function(){
			this.src = "logincode?t=" + $.now();
		},
		login: function (event) {
			var data = "account="+vm.username+"&password="+vm.password+"&code="+vm.captcha;
			$.ajax({
				type: "POST",
			    url: "adminloginajax",
			    data: data,
			    dataType: "json",
			    success: function(result){
					if(result.rcode == 100){//登录成功
						parent.location.href ='adminindex';
					}else{
						vm.error = true;
						vm.errorMsg = result.rinfo;
						
						vm.refreshCode();
					}
				}
			});
		}
	}
});
</script>
</body>
</html>
