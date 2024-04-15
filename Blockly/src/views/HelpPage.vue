<template>
  <nav>
    <!--<router-link to="/position" replace>点位设置</router-link> |-->
    <!--<router-link to="/blockly" replace>Blockly</router-link> |-->
    <!--<router-link to="/help" replace>帮助</router-link>-->
    <div id="group">
      <div class="func" @click="position">
        <el-icon class="icon"><Coordinate /></el-icon>
        <p>点位设置</p>
      </div>
      <div class="func" @click="blockly">
        <el-icon class="icon"><Edit /></el-icon>
        <p>脚本编辑</p>
      </div>
      <div class="func" @click="help">
        <el-icon class="icon"><Help /></el-icon>
        <p>帮助</p>
      </div>
      <div class="servo" @click="servo">
        <el-icon class="icon"><SwitchButton /></el-icon>
        <p>伺服</p>
      </div>
      <div class="pause" @click="pause">
        <el-icon class="icon"><VideoPause /></el-icon>
        <p>暂停</p>
      </div>
      <div class="continue" @click="conti">
        <el-icon class="icon"><CaretRight /></el-icon>
        <p>继续</p>
      </div>
      <div class="stop" @click="stop">
        <el-icon class="icon"><Remove /></el-icon>
        <p>停止</p>
      </div>
    </div>
    <!--<button @click="servoOn" class="btn">启动伺服</button>-->
    <!--<button @click="servoOff" class="btn">关闭伺服</button>-->
  </nav>
  <div class="image">
    <img src="../../public/img/robot.png" alt="robot" class="robot" />
  </div>
  <div class="url">
    <h1>ProEasy</h1>
    <ul>
      <li>
        <a
          href="http://aiproe.com/h-col-222.html"
          target="_blank"
          rel="noopener"
          >官网</a
        >
      </li>
      <li>
        <a
          href="https://www.hipdf.cn/download-file?share_id=02khgtlCVmGm2pvVwT7TqA"
          target="_blank"
          rel="noopener"
          >机器人型号介绍</a
        >
      </li>
    </ul>
    <h2>操作指南</h2>
    <ul>
      <li>
        <a
          href="https://www.hipdf.cn/download-file?share_id=__Hvg-tgdvdqTh0OwlTUxg"
          target="_blank"
          rel="noopener"
          >坐标说明</a
        >
      </li>
      <li>
        <a
          href="https://www.hipdf.cn/download-file?share_id=HlZ5pxUwiBhboUwmzl0VPA"
          target="_blank"
          rel="noopener"
          >DRL详解</a
        >
      </li>
    </ul>
  </div>
</template>

<script>
import axios from "axios";
import {
  CaretRight,
  Coordinate,
  Edit,
  Help,
  Remove,
  SwitchButton,
  VideoPause,
} from "@element-plus/icons-vue";
import { ElIcon } from "element-plus";

let flag;
export default {
  components: {
    SwitchButton,
    ElIcon,
    Edit,
    Coordinate,
    Help,
    Remove,
    CaretRight,
    VideoPause,
  },
  methods: {
    servo() {
      if (flag == 0) {
        axios
          // eslint-disable-next-line no-undef
          .post("http://192.168.1.102:8080/robot/command", {
            command: "ServoOn",
          })
          .then(function (response) {
            //console.log(data);
            console.log(typeof response);
          })
          .catch(function (error) {
            console.log(error);
            //console.log(typeof data);
          });
        flag = 1;
        console.log(flag);
      } else {
        axios
          // eslint-disable-next-line no-undef
          .post("http://192.168.1.102:8080/robot/command", {
            command: "ServoOff",
          })
          .then(function (response) {
            //console.log(data);
            console.log(typeof response);
          })
          .catch(function (error) {
            console.log(error);
            //console.log(typeof data);
          });
        flag = 0;
        console.log(flag);
      }
    },
    position() {
      //指定跳转地址
      this.$router.replace("/position");
    },
    blockly() {
      //指定跳转地址
      this.$router.replace("/blockly");
    },
    help() {
      //指定跳转地址
      this.$router.replace("/help");
    },
  },
};
</script>

<style lang="less">
body {
  background-color: #f8f8f8;
}
nav {
  padding: 30px;
  background-color: #354059;
  color: aliceblue;
  a {
    font-weight: bold;
    color: #eeeeee;

    &.active {
      color: #ffc061;
    }
  }
}
.icon {
  font-size: 26px;
}
.func {
  width: 100px;
  height: 50px;
  padding-top: 1px;
}
.func:active {
  color: #ffc061;
}
#group {
  display: flex;
  justify-content: center;
}
.servo {
  width: 100px;
  height: 50px;
  padding-top: 1px;
  padding-left: 70px;
}
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #1f4c80;
  font-size: 17px;
}
.image {
  padding-top: 60px;
}
.robot {
  width: 310px;
  height: 300px;
}
.url {
  padding-top: 20px;
}
.stop {
  width: 100px;
  height: 50px;
  padding-top: 1px;
}
.continue {
  width: 100px;
  height: 50px;
  padding-top: 1px;
  //padding-left: 5px;
}
.pause {
  width: 100px;
  height: 50px;
  padding-top: 1px;
  padding-left: 25px;
  //padding-left: 5px;
}
</style>
