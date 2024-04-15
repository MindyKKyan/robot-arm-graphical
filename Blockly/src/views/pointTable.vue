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
  <div class="container">
    <div class="div2">
      <table border="3" id="myTable">
        <tr>
          <th>序号</th>
          <th>名称</th>
          <th>X（微米）</th>
          <th>Y（微米）</th>
          <th>Z（微米）</th>
          <th>C（角度）</th>
        </tr>
        <tr>
          <td>1</td>
          <td contenteditable="true" id="cell-1-1"></td>
          <td contenteditable="true" id="cell-1-2"></td>
          <td contenteditable="true" id="cell-1-3"></td>
          <td contenteditable="true" id="cell-2-4"></td>
          <td contenteditable="true" id="cell-2-5"></td>
        </tr>
        <tr>
          <td>2</td>
          <td contenteditable="true" id="cell-2-1"></td>
          <td contenteditable="true" id="cell-2-2"></td>
          <td contenteditable="true" id="cell-2-3"></td>
          <td contenteditable="true" id="cell-2-4"></td>
          <td contenteditable="true" id="cell-2-5"></td>
        </tr>
        <tr>
          <td>3</td>
          <td contenteditable="true" id="cell-3-1"></td>
          <td contenteditable="true" id="cell-3-2"></td>
          <td contenteditable="true" id="cell-3-3"></td>
          <td contenteditable="true" id="cell-2-4"></td>
          <td contenteditable="true" id="cell-2-5"></td>
        </tr>

        <tr>
          <td>4</td>
          <td contenteditable="true" id="cell-4-1"></td>
          <td contenteditable="true" id="cell-4-2"></td>
          <td contenteditable="true" id="cell-4-3"></td>
          <td contenteditable="true" id="cell-4-4"></td>
          <td contenteditable="true" id="cell-4-5"></td>
        </tr>

        <tr>
          <td>5</td>
          <td contenteditable="true" id="cell-5-1"></td>
          <td contenteditable="true" id="cell-5-2"></td>
          <td contenteditable="true" id="cell-5-3"></td>
          <td contenteditable="true" id="cell-5-4"></td>
          <td contenteditable="true" id="cell-5-5"></td>
        </tr>

        <tr>
          <td>6</td>
          <td contenteditable="true" id="cell-6-1"></td>
          <td contenteditable="true" id="cell-6-2"></td>
          <td contenteditable="true" id="cell-6-3"></td>
          <td contenteditable="true" id="cell-6-4"></td>
          <td contenteditable="true" id="cell-6-5"></td>
        </tr>

        <tr>
          <td>7</td>
          <td contenteditable="true" id="cell-7-1"></td>
          <td contenteditable="true" id="cell-7-2"></td>
          <td contenteditable="true" id="cell-7-3"></td>
          <td contenteditable="true" id="cell-7-4"></td>
          <td contenteditable="true" id="cell-7-5"></td>
        </tr>

        <tr>
          <td>8</td>
          <td contenteditable="true" id="cell-8-1"></td>
          <td contenteditable="true" id="cell-8-2"></td>
          <td contenteditable="true" id="cell-8-3"></td>
          <td contenteditable="true" id="cell-8-4"></td>
          <td contenteditable="true" id="cell-8-5"></td>
        </tr>
      </table>
    </div>
    <div class="div3">
      <button @click="saveTable()" class="initbtn">保存</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {
  Coordinate,
  Edit,
  Help,
  SwitchButton,
  Remove,
} from "@element-plus/icons-vue";
import { ElIcon } from "element-plus";
import { CaretRight } from "@element-plus/icons-vue";
import { VideoPause } from "@element-plus/icons-vue";

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

    saveTable() {
      this.$confirm("确认保存");
      // var table = document.getElementById("myTable");
      // var rows = table.rows;
      // var data = [];
      // var mb_array = [];
      // for (var i = 1; i < rows.length; i++) {
      //   var cells = rows[i].cells;
      //   var row_data = [];
      //   for (var j = 0; j < cells.length; j++) {
      //     if (cells[j].innerHTML.trim() != "") {
      //       row_data.push(cells[j].innerHTML.trim());
      //     } else {
      //       row_data.push(null);
      //     }
      //   }
      //   data.push(row_data);
      // }
      // console.log(data);
      // console.log(data[0][2]);
      // var add = 0;
      // var wrong_point;
      // var signal = 0;
      // var check = 0;
      // for (j = 0; j < 8; j++) {
      //   if (check > 0 && check < 4) {
      //     wrong_point = wrong_point + (j - 1) + ",";
      //     console.log(wrong_point);
      //     signal = 1;
      //   }
      //   check = 0;
      //   if ([j][0] == "0") {
      //     add = "A000";
      //     console.log(add);
      //   } else if ([j][0] == "1") {
      //     add = "A018";
      //     console.log(add);
      //   } else if ([j][0] == "2") {
      //     add = "A030";
      //     console.log(add);
      //   } else if ([j][0] == "3") {
      //     add = "A048";
      //     console.log(add);
      //   } else if ([j][0] == "4") {
      //     add = "A060";
      //     console.log(add);
      //   } else if ([j][0] == "5") {
      //     add = "A078";
      //     console.log(add);
      //   } else if ([j][0] == "6") {
      //     add = "A000";
      //     console.log(add);
      //   } else if ([j][0] == "7") {
      //     add = "A090";
      //     console.log(add);
      //   }
      //
      //   if (data[j][2] === null) {
      //     check++;
      //     continue;
      //   }
      //   var xx = new Float32Array(1);
      //   xx[0] = data[j][2];
      //   var xValue = xx[0].toString(16).toUpperCase();
      //   while (xValue.length < 8) {
      //     xValue = "0" + xValue;
      //   }
      //   console.log(xValue);
      //
      //   if (data[j][3] === null) {
      //     check++;
      //     continue;
      //   }
      //   var yy = new Float32Array(1);
      //   yy[0] = data[j][3];
      //   var yValue = yy[0].toString(16).toUpperCase();
      //   while (yValue.length < 8) {
      //     yValue = "0" + yValue;
      //   }
      //   console.log(yValue);
      //
      //   if (data[j][4] === null) {
      //     check++;
      //     continue;
      //   }
      //   var zz = new Float32Array(1);
      //   zz[0] = data[j][4];
      //   var zValue = zz[0].toString(16).toUpperCase();
      //   while (zValue.length < 8) {
      //     zValue = "0" + zValue;
      //   }
      //   console.log(zValue);
      //
      //   if (data[j][5] === null) {
      //     check++;
      //     continue;
      //   }
      //   var cc = new Float32Array(1);
      //   cc[0] = data[j][5];
      //   var cValue = cc[0].toString(16).toUpperCase();
      //   while (cValue.length < 8) {
      //     cValue = "0" + cValue;
      //   }
      //   console.log(cValue);
      //   var modbus =
      //     "0000 0000 0006 0210" +
      //     add +
      //     " 0018 30 0000 0000 0000 0000 0000 0000 0000 0000" +
      //     xValue +
      //     yValue +
      //     zValue +
      //     "0000 0000 0000 0000" +
      //     cValue +
      //     "F000 0000 0000 8002";
      //   console.log(modbus);
      //   mb_array.push(modbus);
      // }
      // console.log("Type: " + typeof mb_array);
      // axios
      //   // eslint-disable-next-line no-undef
      //   .post("http://192.168.1.102:8080/robot/sendPointCommands", {
      //     PointCommand: mb_array,
      //   })
      //   .then(function (response) {
      //     //console.log(data);
      //     console.log(typeof response);
      //   })
      //   .catch(function (error) {
      //     console.log(error);
      //     //console.log(typeof data);
      //   });
    },
  },
};
</script>

<style lang="less">
.container {
  display: grid;
  place-items: center;
}

.title {
  font-size: 29px;
  font-weight: bold;
}
.version {
  font-size: 20px;
}
.div2 {
  padding-top: 100px;
  width: 1200px;
  background-color: #ffffff;
}
.proeasy {
  width: 220px;
  height: 210px;
}
.div3 {
  padding: 70px;
  width: 800px;
  background-color: #ffffff;
}
.initbtn {
  width: 150px;
  height: 39px;
  border-radius: 8px;
  background-color: #001b33;
  color: #eeeeee;
  font-size: 17px;
}
td {
  width: 200px;
  height: 50px;
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
