
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <!-- import CSS -->
  <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
  <style>
    html,body{
      height: 350px;
       weight: 350px;
      padding: 0px;
      margin: 0px;
    }
    .loadingModal {

      width: 100%;
      background: rgba(255, 255, 255, 0.9);
      text-align: center;
      position: absolute;
      top: 0;
      left: 0;
      z-index: 999999;
    }

  </style>

</head>
<body>

<div id="root">



    <div>
      <el-progress id = "circleProcess" type="circle" v-bind:percentage="parseInt(fake.progress*100)" ></el-progress>
      <el-button @click="function(){fake.end();}">停止</el-button></div>



  <div class="loadingModal" :style="{'height':fatherHeight+'px'}" v-if="progressLoading">
    <el-progress
            :style="{width:'20%',margin:'0 auto',marginTop:`${fatherHeight*0.3}px`}"
            :type="type"
            :width="70"
            :text-inside="true"
            :stroke-width="type?undefined:strokeWidth"
            :percentage="percentage"
            :color="colors"
            :show-text="showText"
            :status="percentage === 100?undefined:'success'"
    ></el-progress>
  </div>

</div>


</body>
<!-- import Vue before Element -->
<script src="https://unpkg.com/vue@2/dist/vue.js"></script>
<!-- import JavaScript -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script>
  var url = location.search; //获取url中"?"符后的字串
  if (url.indexOf("?") != -1) {    //判断是否有参数
    var str = url.substr(1); //从第一个字符开始 因为第0个是?号 获取所有除问号的所有符串
    strs = str.split("=");   //用等号进行分隔 （因为知道只有一个参数 所以直接用等号进分隔 如果有多个参数 要用&号分隔 再用等号进行分隔）
    alert(strs[1]);          //直接弹出第一个参数 （如果有多个参数 还要进行循环的）
  }

</script>
<script >
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
    data:  function() {
      return {
        fake: new FakeProgress({
          timeConstant: 6000,
          autoStart: true
        }),fullscreenLoading: false,

      }
    },
    methods: {
      // 进度条开始
      start() {
        const that = this;
        that.fatherHeight = this.$el.parentNode.offsetHeight;
        that.$nextTick(() => {
          that.progressLoading = true;
          that.percentage = 0;
          that.timeStart = setInterval(() => {
            if (that.percentage < 95) {
              that.percentage += 5;
            }
          }, 100);
        });
      },
      // 进度条结束
      end() {
        const that = this;
        that.percentage = 100;
        clearInterval(this.timeStart);
        setTimeout(() => {
          that.progressLoading = false;
        }, 300);
      },
    },
    watch: {
      // 监听loading状态控制进度条显示
      loading(value, newValue) {
        const that = this;
        if (value) {
          that.start();
        } else {
          that.end();
        }
      },
    }

  });



</script>
</html>