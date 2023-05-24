<!-- 此例子是结合bootstrap的Datatables，暂且定位为最基本的例子吧 -->
<!-- 引入必须的css和js文件 -->
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!-- <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.min.css" />
    <link rel="stylesheet" type="text/css" href="css/dataTables.bootstrap.css" />  -->


    <script src="js/Hui-js/lib/jquery.js"></script>
    <script src="js/Hui-js/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css"
          href="static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css"
          href="js/Hui-js/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css"
          href="static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css"
          href="static/h-ui.admin/css/style.css"/>
    <script type="text/javascript"
            src="js/Hui-js/lib/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="js/Hui-js/lib/layer/2.4/layer.js"></script>
    <script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
    <script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>


    <!--/_footer /作为公共模版分离出去-->
    <!--请在下方写此页面业务相关的脚本-->
    <script type="text/javascript"
            src="js/Hui-js/lib/My97DatePicker/4.8/WdatePicker.js"></script>
    <script type="text/javascript"
            src="js/Hui-js/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
    <script type="text/javascript"
            src="js/Hui-js/lib/laypage/1.2/laypage.js"></script>
    <script type="text/javascript"></script>
    <style>
        .popContainer {
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            z-index: 999999;
            background: rgba(0, 0, 0, 0.6);
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .popContainer > div {
            /* 如果需要内部div也垂直居中 */
            display: flex;
            align-items: center;
            justify-content: center;
        }
    </style>
    <!-- HTML代码片段中请勿添加<body>标签 //-->
</head>
<body>
<div id="root">


    <div v-if="isShowProgress" class="popContainer">

        <div style="text-align: center;">
            <el-progress :percentage="parseInt(fake.progress*100)" :format="format" v-if="isShowProgress" type="circle"
                         text-color="white">
            </el-progress>

        </div>


    </div>
    <div id="container">
        <div id="app" class="text-c" style="margin-top: 1%;">
            <input type="text" name="part_name" id="part_name" placeholder="备件名称"
                   style="width: 200px" class="input-text">
            <button onclick="selectbutton();" class="btn btn-success"
                    style="background-color: #337AB7;" type="submit">
                </i><i class="Hui-iconfont">&#xe665;</i> 查询
            </button>
            <button @click="function(){isShowProgress=true;prebutton();}" class="btn btn-success"
                    style="background-color: #337AB7;" type="submit">
                </i><i class="Hui-iconfont">&#xe606;</i> 预测
            </button>
            <button onclick="returnbutton();" class="btn btn-success"
                    style="background-color: #337AB7;" type="submit">
                </i><i class="Hui-iconfont">&#xe68f;</i> 刷新
            </button>


        </div>


        <!-- 定义一个表格元素 -->
        <div style="height: 10px"></div>
        <div style="width: 98%; margin-left: 10px">
            <table id="example" class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th style="text-align: center; width: 1%">序号</th>
                    <th style="text-align: center; width: 1%">备件名称</th>
                    <th style="text-align: center; width: 1%">出库日期</th>
                    <th style="text-align: center; width: 1%">预测值</th>
                    <th style="text-align: center; width: 1%">运行时间</th>

                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
    </div>
</div>
<script type="text/javascript">

    function modalalertdemo() {
        $("body").Huimodalalert({
            content: '我是消息框，2秒后我自动滚蛋！',
            speed: 2000
        })
    }

    $(document).ready(selectbutton());

    function selectbutton() {
        var sort = "";

        var part_name = $("#part_name").val();

        $(document).ready(function () {
            $('#example').dataTable().fnDestroy();
            $('#example').dataTable({
                "ajax": {
                    "url": "adminfd_preajax",
                    "type": "post",
                    "data": {part_name: part_name}
                },
                "lengthChange": true,//是否允许用户自定义显示数量
                "bPaginate": true, //翻页功能
                "bFilter": false, //列筛序功能
                "searching": false,//本地搜索
                "ordering": false, //排序功能
                "Info": true,//页脚信息
                "autoWidth": true,//自动宽度
                "serverSide": true,
                /* "bLengthChange":false, */
                "fnDrawCallback": function () {
                    this.api().column(0).nodes().each(function (cell, i) {
                        cell.innerHTML = i + 1;
                    });
                },
                "columns": [
                    {"data": null, "targets": 0},
                    {"data": "part_name"},
                    {"data": "prediction_date"},
                    {"data": "prediction_value"},
                    {"data": "running_date"}
                ],

                "columnDefs": [
                    {}
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

    function modaldemo() {
        $("#modal-demo").modal("show")
    }

    function modalalertdemo() {
        $("body").Huimodalalert({
            content: '我是消息框，2秒后我自动滚蛋！',
            speed: 2000
        })
    }


    function open_layer(title, url, w, h) {

        layer_show(title, url, w, h);

    }


</script>
<!-- import Vue before Element -->
<script src="https://unpkg.com/vue@2/dist/vue.js"></script>
<!-- import JavaScript -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>

<script>
    /**
     * Represents a fakeProgress
     * @constructor
     * @param {object} options - options of the contructor
     * @param {object} [options.timeConstant=1000] - the timeConstant in milliseconds (see https://en.wikipedia.org/wiki/Time_constant)
     * @param {object} [options.autoStart=false] - if true then the progress auto start
     */

    const FakeProgress = function (opts) {
        if (!opts) {
            opts = {};
        }
        // 时间快慢
        this.timeConstant = opts.timeConstant || 1000;
        // 自动开始
        this.autoStart = opts.autoStart || false;
        this.parent = opts.parent;
        this.parentStart = opts.parentStart;
        this.parentEnd = opts.parentEnd;
        this.progress = 0;
        this._intervalFrequency = 100;
        this._running = false;
        if (this.autoStart) {
            this.start();
        }
    };

    /**
     * Start fakeProgress instance
     * @method
     */

    FakeProgress.prototype.start = function () {
        this._time = 0;
        this._intervalId = setInterval(
            this._onInterval.bind(this),
            this._intervalFrequency
        );
    };

    FakeProgress.prototype._onInterval = function () {
        this._time += this._intervalFrequency;
        this.setProgress(1 - Math.exp((-1 * this._time) / this.timeConstant));
    };

    /**
     * Stop fakeProgress instance and set progress to 1
     * @method
     */

    FakeProgress.prototype.end = function () {
        this.stop();
        this.setProgress(1);

    };
    FakeProgress.prototype.endto0 = function () {
        this.stop();
        this.setProgress(0);

    };

    /**
     * Stop fakeProgress instance
     * @method
     */

    FakeProgress.prototype.stop = function () {
        clearInterval(this._intervalId);
        this._intervalId = null;
    };

    /**
     * Create a sub progress bar under the first progres
     * @method
     * @param {object} options - options of the FakeProgress contructor
     * @param {object} [options.end=1] - the progress in the parent that correspond of 100% of the child
     * @param {object} [options.start=fakeprogress.progress] - the progress in the parent that correspond of 0% of the child
     */

    FakeProgress.prototype.createSubProgress = function (opts) {
        const parentStart = opts.start || this.progress;
        const parentEnd = opts.end || 1;
        const options = Object.assign({}, opts, {
            parent: this,
            parentStart: parentStart,
            parentEnd: parentEnd,
            start: null,
            end: null,
        });

        const subProgress = new FakeProgress(options);
        return subProgress;
    };

    /**
     * SetProgress of the fakeProgress instance and updtae the parent
     * @method
     * @param {number} progress - the progress
     */

    FakeProgress.prototype.setProgress = function (progress) {
        this.progress = progress;
        if (this.parent) {
            this.parent.setProgress(
                (this.parentEnd - this.parentStart) * this.progress + this.parentStart
            );
        }
    };


    const vm = new Vue({
        el: '#root', //el用于指定当前Vue实例为哪个容器服务，值通常为css选择器字符串
        data: function () {
            return {
                fake: new FakeProgress({
                    timeConstant: 6000,

                }), isShowProgress: false, progressStatus: "",globalVar: 0,
            };
        },
        methods: {
            format(percentage) {
                if (this.fake.progress == 0) {
                    return '未输入备件名！';
                }
                if (this.fake.progress >= 1) {
                    // 运行完成
                    return '算法运行完成';
                } else if (this.isShowProgress) {
                    // 运行中
                    return '算法运行\n' + parseInt(percentage) + '%';
                } else {
                    // 运行失败
                    return '算法运行失败';
                }

            },
            prebutton() {
                const self = this; // 保存 Vue 实例的 this

                this.fake.start();
                var datacode = 0;

                var part_name = $("#part_name").val();
                this.isShowProgress = true

                myajax=$.ajax({
                    type: "POST",
                    url: 'adminfd_preAlgorithmtest',
                    data: {partName: part_name},
                    success: function (data) {
                        if (data.code == "100") {
                            self.globalVar = 100; // 使用保存的 Vue 实例的 this
                        }else if(data.code=="500"){
                            self.globalVar = 500;
                        }
                    }
                });

                // myajax 请求完毕时执行
                $.when(myajax).done(() => {
                   // alert(self.globalVar); // 使用保存的 Vue 实例的 this
                    if (self.globalVar == 100) {
                        self.fake.end(); // 使用保存的 Vue 实例的 this
                        self.progressStatus = 'success'; // 使用保存的 Vue 实例的 this
                        setTimeout(() => {
                            self.isShowProgress = false; // 使用保存的 Vue 实例的 this
                        }, 2000);

                    } else if(self.globalVar == 500){
                        self.fake.endto0(); // 使用保存的 Vue 实例的 this
                        self.progressStatus = 'fail'; // 使用保存的 Vue 实例的 this
                        setTimeout(() => {
                            self.isShowProgress = false; // 使用保存的 Vue 实例的 this
                        }, 2000);

                    }else {
                        self.progressStatus = 'fail'; // 使用保存的 Vue 实例的 this
                        self.fake.end(); // 使用保存的 Vue 实例的 this
                        self.format(0); // 使用保存的 Vue 实例的 this
                    }
                });
            }

        }

    });


</script>
</body>
</html>
