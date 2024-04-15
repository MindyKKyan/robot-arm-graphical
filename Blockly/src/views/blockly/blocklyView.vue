<!--/src/views/clockly/modules/blocklyView.vue-->
<template>
  <!--上方导航区域-->

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

  <div class="blockly">
    <!--中间代码块拖拽区域+右边栏区域-->

    <div class="main" style="text-align: initial">
      <!-- blockly工作区 -->
      <a-row>
        <!--中间区域-->
        <a-col :span="20">
          <div id="blocklyDiv" style="height: 480px; width: 100%"></div>
        </a-col>
        <a-col :span="4">
          <!-- 右边栏 -->
          <a-card
            size="small"
            :title="currentBlock.type ? currentBlock.type : '说明'"
            style="height: 100%"
          >
            <template #extra>
              <span>
                <QuestionCircleOutlined />
              </span>
            </template>
            <!--<div v-if="!currentBlock.type">代码块设置</div>-->
            <!--<div v-else>-->
            <div>
              {{ currentBlock }}
            </div>
          </a-card>
        </a-col>
      </a-row>
    </div>

    <!-- 左侧blockly工具栏 -->

    <xml id="toolbox" style="display: none">
      <category name="逻辑关系">
        <block type="controls_if"></block>
        <block type="controls_whileUntil"></block>
      </category>
      <sep></sep>
      <category name="速度设定">
        <!--        <block type="lua_demo_1"></block>-->
        <!--        <block type="lua_demo_2"></block>-->
        <!--        <block type="variables_get_panda"></block>-->
        <!--        <block type="variables_set_panda"></block>-->
        <block type="MaxSpdL"></block>
        <block type="MaxAccL"></block>
        <block type="MaxSpdJ"></block>
        <block type="MaxAccJ"></block>
        <block type="SpdL"></block>
        <block type="SpdJ"></block>
        <block type="DecL"></block>
        <block type="DecJ"></block>
        <block type="AccL"></block>
        <block type="AccJ"></block>
        <block type="JerkL"></block>
        <block type="JerkJ"></block>
        <block type="SetPassDistance"></block>
        <block type="SetPassTime"></block>
        <block type="SetPassMode"></block>
        <block type="SetWaitCmdMode"></block>
        <block type="SetMArchPPS"></block>
        <block type="MovL_MODE"></block>
      </category>
      <sep></sep>
      <category name="料盘定义">
        <!--        <block type="lua_demo_1"></block>-->
        <!--        <block type="lua_demo_2"></block>-->
        <block type="LiaoPanNo"></block>
        <block type="test6"></block>
        <block type="test7"></block>
      </category>
      <sep></sep>
      <category name="IO信号">
        <!--        <block type="lua_demo_1"></block>-->
        <!--        <block type="lua_demo_2"></block>-->
        <block type="IOSignal"></block>
      </category>
      <sep></sep>
      <category name="运动控制">
        <!--<block type="lua_demo_1"></block>-->
        <!--<block type="lua_demo_2"></block>-->
        <block type="MovP"></block>
        <block type="MovL"></block>
        <!--<block type="MovL_EX"></block>-->
        <!--<block type="MovPR"></block>-->
        <!--<block type="MovLR"></block>-->

        <!--<block type="MArchP"></block>-->

        <block type="MArchL"></block>
        <!--<block type="MArchPT"></block>-->
        <!--<block type="MArchLT"></block>-->
        <!--<block type="MovJ"></block>-->
        <!--<block type="MovCIRC"></block>-->
        <!--<block type="MovCIRC_DIR"></block>-->
        <!--<block type="MovCIRC_EX"></block>-->
        <!--<block type="StopAxis"></block>-->
        <!--<block type="StopGroup"></block>-->
        <block type="DELAY"></block>
      </category>
      <sep></sep>
      <category name="DI/O操作">
        <block type="DI"></block>
      </category>
      <sep></sep>
      <category name="创建变量" custom="CUSTOM_CREATE_VARIABLE"></category>
      <!--      <sep></sep>-->
      <!--      <category name="Variables" custom="VARIABLE"></category>-->
      <!--      <sep></sep>-->
      <!--      <category name="Functions" custom="PROCEDURE"></category>-->
    </xml>

    <!-- 下方blockly代码区 -->

    <div style="color: #001b33">
      <pre
        id="content_lua"
        class="content prettyprint lang-lua"
        style="text-align: initial; font-size: 16px"
      ></pre>
    </div>
    <div>
      <!--右下button-->
      <!--如果实现软件给控制器传输lua文件进行运行的话，要先点击save进行向后端传输文件-->
      <button
        style="
          background-color: #00336e;
          font-size: 21px;
          color: aliceblue;
          text-align: center;
          border-radius: 8px;
          width: 80px;
          line-height: 30px;
          position: absolute;
          right: 3%;
          bottom: 6%;
        "
        @click="save"
      >
        Save
      </button>
      <br />
      <!--向后端传输run指令，运行lua代码-->
      <button
        style="
          background-color: #00336e;
          font-size: 20px;
          color: aliceblue;
          text-align: center;
          border-radius: 8px;
          width: 80px;
          line-height: 30px;
          position: absolute;
          right: 3%;
          bottom: 1%;
        "
        @click="run"
      >
        Run
      </button>
    </div>

    <!-- 创建变量 -->
    <createVariablePopup
      ref="createVariablePopup"
      :workspace="workspace"
      @updateData="updateData"
    />
  </div>
</template>

<script>
import { message } from "ant-design-vue";
import { QuestionCircleOutlined } from "@ant-design/icons-vue";

import Blockly from "blockly";
import "blockly/lua";
import * as zhHans from "blockly/msg/zh-hans";
Blockly.setLocale(zhHans);

import createVariablePopup from "./modules/createVariablePopup.vue";
import axios from "axios";
import { ElIcon } from "element-plus";
import { Edit } from "@element-plus/icons-vue";
import { Coordinate } from "@element-plus/icons-vue";
import { Help } from "@element-plus/icons-vue";
import { SwitchButton } from "@element-plus/icons-vue";
import { Remove } from "@element-plus/icons-vue";
import { CaretRight } from "@element-plus/icons-vue";
import { VideoPause } from "@element-plus/icons-vue";

let TotalData; //用于最后存入文件的变量
let testtotaldata;
let flag;
export default {
  components: {
    // component icon注册
    SwitchButton,
    createVariablePopup,
    QuestionCircleOutlined,
    ElIcon,
    Edit,
    Coordinate,
    Help,
    Remove,
    CaretRight,
    VideoPause,
  },
  data() {
    return {
      workspace: null,
      // currentBlock: {},
      currentBlock: "Welcome To DRAS",
    };
  },
  mounted() {
    // this.initCustomTheme();//css在之前有过注册，所以这个地方就不能再注册了，不要调用这个函数
    this.workspace = Blockly.inject("blocklyDiv", {
      //工具栏
      toolbox: document.getElementById("toolbox"),
      //网格效果
      grid: { spacing: 20, length: 3, colour: "#a4a3a3", snap: true },
      //媒体资源
      media: "./media/",
      //垃圾桶
      trashcan: true,
      // theme: Blockly.Themes.CUSTOM_THEME,
      zoom: {
        controls: true,
        wheel: true,
      },
    });
    //调整工作区域大小
    // eslint-disable-next-line @typescript-eslint/no-this-alias
    let that = this;
    let onResize = function (e) {
      Blockly.svgResize(that.workspace);
    };
    //注册resize处理函数
    window.addEventListener("resize", onResize);
    Blockly.svgResize(this.workspace);
    //工作区监听代码生成器
    this.workspace.addChangeListener(this.renderContent);

    this.initCustomBlocks();
    this.registerToolboxCategory();
  },
  methods: {
    //伺服启动/关闭
    servo() {
      //这里如果用到两种icon就不需要if了
      if (flag == 1) {
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
        flag = 0;
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
        flag = 1;
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
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    },
    //css设置
    initCustomTheme() {
      Blockly.Themes.CUSTOM_THEME = Blockly.Theme.defineTheme("CUSTOM_THEME", {
        base: Blockly.Themes.Classic,
        categoryStyles: {
          custom_category: {
            colour: "#5ba5a5", // 工具箱颜色标识
          },
        },
        blockStyles: {
          // 块样式目前由四个字段组成：colourPrimary、colourSecondary、colorTertiary 和 hat。
          lua_demo: {
            colourPrimary: "#5ba5a5", //块的背景色，可以用色调或十六进制值定义
            colourSecondary: "#5ba5a5", // 如果块是阴影块，则使用此颜色
            colourTertiary: "#C5EAFF", // 块的边框颜色
          },
        },
        componentStyles: {
          workspaceBackgroundColour: "#f2f6fa", // 工作区背景色
          toolboxBackgroundColour: "#b0b0b0", // 工具箱背景色
          toolboxForegroundColour: "#f5f5f5", // 工具箱类别文字颜色
          flyoutBackgroundColour: "#d2d2d2", // 弹出背景颜色
          flyoutForegroundColour: "#333", // 弹出标签文本颜色
          flyoutOpacity: 1, // 弹出不透明度
          scrollbarColour: "#dcdcdc", // 滚动条颜色
          scrollbarOpacity: 0.4, // 滚动条不透明度
          insertionMarkerColour: "#f5f5f5", // 插入标记颜色（不接受颜色名称）
          insertionMarkerOpacity: 0.3, // 插入标记不透明度
          cursorColour: "#f5f5f5", // 键盘导航模式下显示的光标颜色
        },
      });
    },
    //module设置
    initCustomBlocks() {
      // JSON way
      // var luaDemo2Json = {
      //   message0: "change %1 by %2",
      //   args0: [
      //     {
      //       type: "field_variable",
      //       name: "VARIABLE_NAME",
      //       variable: "item",
      //       variableTypes: [""],
      //     },
      //     {
      //       type: "input_value",
      //       name: "DELTA",
      //       check: "Number",
      //     },
      //   ],
      //   previousStatement: null,
      //   nextStatement: null,
      //   colour: 230,
      //   data: "lua_demo_2!!!",
      // };
      //
      // Blockly.Blocks["lua_demo_2"] = {
      //   init: function () {
      //     this.jsonInit(luaDemo2Json);
      //     // Assign 'this' to a variable for use in the tooltip closure below.
      //     // eslint-disable-next-line @typescript-eslint/no-this-alias
      //     var thisBlock = this;
      //     this.setTooltip(function () {
      //       return 'Add a number to variable "%1".'.replace(
      //         "%1",
      //         thisBlock.getField("VARIABLE_NAME").getText()
      //       );
      //     });
      //   },
      // };

      // // Block for variable getter.
      // Blockly.Blocks["variables_get_panda"] = {
      //   init: function () {
      //     this.appendDummyInput().appendField(
      //       new Blockly.FieldVariable("VAR_NAME", ["Panda"], "Panda"),
      //       "FIELD_NAME"
      //     );
      //     this.setOutput(true, "Panda");
      //   },
      // };
      // // Block for variable setter.
      // Blockly.Blocks["variables_set_panda"] = {
      //   init: function () {
      //     this.appendValueInput("NAME")
      //       .setCheck("Panda")
      //       .appendField("set")
      //       .appendField(
      //         new Blockly.FieldVariable("VAR_NAME", null, ["Panda"], "Panda"),
      //         "FIELD_NAME"
      //       )
      //       .appendField("to");
      //     this.setPreviousStatement(true, null);
      //     this.setNextStatement(true, null);
      //   },
      // };
      //Test block of Lua code generation
      //JS way

      //速度设定
      Blockly.Blocks["MaxSpdL"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("Line运动最大速度限制(mm/sec)")
            .appendField(new Blockly.FieldNumber(1000, 0, 10000, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(105);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["MaxAccL"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("Line运动最大加速度(mm/sec^2)")
            .appendField(new Blockly.FieldNumber(100, 0, 10000, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(210);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["MaxSpdJ"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("Line运动最大速度限制(PUU/msec)")
            .appendField(new Blockly.FieldNumber(8500, 0, 10000, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(66);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["MaxAccJ"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("Line运动最大加速度(PUU/msec^2)")
            .appendField(new Blockly.FieldNumber(100, 0, 10000, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(191);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["SpdL"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("Line运动最大速度(mm/sec)")
            .appendField(new Blockly.FieldNumber(1000, 0, 10000, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(290);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["SpdJ"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("Joint运动最大速度(%)")
            .appendField(new Blockly.FieldNumber(50, 0, 100, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(290);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["DecL"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("Line运动减速度设定(mm/sec^2)")
            .appendField(new Blockly.FieldNumber(200, 0, 10000, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(50);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["DecJ"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("Joint运动减速度设定(%)")
            .appendField(new Blockly.FieldNumber(10, 0, 100, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(50);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["AccL"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("Line运动加速度设定(mm/sec^2)")
            .appendField(new Blockly.FieldNumber(100, 0, 10000, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(0);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["AccJ"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("Joint运动加速度设定(%)")
            .appendField(new Blockly.FieldNumber(50, 0, 100, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(140);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["JerkL"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("Line运动加加速度设定(mm/sec^3)")
            .appendField(new Blockly.FieldNumber(100, 0, 10000000, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(120);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["JerkJ"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("Joint运动加加速度设定(%)")
            .appendField(new Blockly.FieldNumber(50, 0, 10000, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(0);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["SetPassTime"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("插断时间设定(%)")
            .appendField(new Blockly.FieldNumber(100, 0, 10000, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(234);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["SetPassDistance"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("插断距离设定(mm)")
            .appendField(new Blockly.FieldNumber(100, 0, 10000, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(234);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["SetPassMode"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("插断模式设定:")
            .appendField(
              new Blockly.FieldDropdown([
                ["TM_DIS_PASS", "ITEM1"],
                ["TM_TIME_PASS", "ITEM2"],
              ]),
              "passMode"
            );
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(34);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["SetWaitCmdMode"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("等待模式设定:")
            .appendField(
              new Blockly.FieldDropdown([
                ["MOTION_WAITBUFFER", "waitbuffer"],
                ["MOTION_INPOSITION", "motion_inposition"],
                ["MOTION_DONE", "motion_done"],
              ]),
              "waitMode"
            );
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(78);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["SetMArchPPS"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("设定上升过程是否忽略姿态:")
            .appendField(
              new Blockly.FieldDropdown([
                ["false", "F"],
                ["true", "T"],
              ]),
              "marchPps"
            );
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(103);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["MovL_MODE"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("输入模式: ")
            .appendField(
              new Blockly.FieldDropdown([
                ["REAL_SPEED", "real_speed"],
                ["PERCENT_SPEED", "percent_speed"],
              ]),
              "movlMode"
            );
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(66);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };

      //料盘定义
      Blockly.Blocks["LiaoPanNo"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("料盘编号：")
            .appendField(new Blockly.FieldNumber(1, 0, 100, 1), "liaopan");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(120);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["test6"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("料盘原点（1号点）位置：")
            .appendField(new Blockly.FieldNumber(1025, 0, 10000, 1), "addr1");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(140);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["test7"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("料盘X方向点（2号点）位置：")
            .appendField(new Blockly.FieldNumber(1026, 0, 10000, 1), "addr2");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(160);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };

      //IO Signal
      Blockly.Blocks["IOSignal"] = {
        init: function () {
          this.appendDummyInput().appendField("IO信号");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(170);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };

      //运动控制
      Blockly.Blocks["MovP"] = {
        // 触发条件：前面需要JerkJ,AccJ,DecJ,SpdJ
        // init: function () {
        //   this.appendDummyInput()
        //     .appendField("绝对座标上进行多轴PTP运动 ")
        //     // .appendField("目标位置点位(No.)")
        //     .appendField(
        //       new Blockly.FieldDropdown([
        //         ["P1", "p1"],
        //         ["P2", "p2"],
        //         ["P3", "p3"],
        //       ]),
        //       "point"
        //     )
        //
        //     .appendField(" BMode:")
        //     .appendField(
        //       new Blockly.FieldDropdown([
        //         ["PASS", "pass"],
        //         ["ABORT", "abort"],
        //         ["BLENDSTART", "blendstart"],
        //       ]),
        //       "bMode"
        //     )
        //     .appendField(" Spd(%):")
        //     .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "spd")
        //     .appendField(" Acc(%):")
        //     .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "acc")
        //     .appendField(" Dec(%):")
        //     .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "dec")
        //     .appendField(" Jerk(%):")
        //     .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "jerk");
        //   this.setPreviousStatement(true);
        //   this.setNextStatement(true);
        //   this.setColour(23);
        //   this.setTooltip("");
        //   this.setHelpUrl("");
        // },
        init: function () {
          this.appendDummyInput()
            .appendField("多轴PTP运动：MovP")
            .appendField(new Blockly.FieldNumber(1, 1, 100, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(220);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["MovL"] = {
        // 需要预先设置MovL_MODE!!!
        // REAL_SPEED / PERCENT_SPEED
        // 触发条件：前面需要JerkJ,AccJ,DecJ,SpdJ
        // init: function () {
        //   this.appendField("绝对座标上进行多轴Line运动 ");
        //   this.appendDummyInput()
        //     .appendField("目标位置点位(No.)")
        //     .appendField(new Blockly.FieldTextInput("null"), "num1");
        //   this.appendDummyInput()
        //     .appendField(" BMode设定：")
        //     .appendField(new Blockly.FieldNumber(1, 0, 10000, 1), "num2");
        //   this.appendDummyInput()
        //     .appendField(" 最大运动速度设定(mm/sec / %)")
        //     .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num3");
        //   this.appendDummyInput()
        //     .appendField(" 运动加速度设定(mm/sec^2 / %)")
        //     .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num4");
        //   this.appendDummyInput()
        //     .appendField(" 运动减速度设定(mm/sec^2 / %)")
        //     .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num5");
        //   this.appendDummyInput()
        //     .appendField(" 运动加加速度设定(mm/sec^3 / %)")
        //     .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num6");
        //   this.setPreviousStatement(true);
        //   this.setNextStatement(true);
        //   this.setColour(46);
        //   this.setTooltip("");
        //   this.setHelpUrl("");
        init: function () {
          this.appendDummyInput()
            .appendField("多轴Line运动：MovL")
            .appendField(new Blockly.FieldNumber(1, 1, 100, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(240);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["MovL_EX"] = {
        // 需要预先设置MovL_MODE!!!
        // REAL_SPEED / PERCENT_SPEED
        // 触发条件：前面需要JerkJ,AccJ,DecJ,SpdJ
        init: function () {
          this.appendDummyInput()
            .appendField("绝对座标上进行多轴Line运动")
            .appendField(new Blockly.FieldNumber(4, 0, 10000, 1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(49);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      //
      //
      //
      Blockly.Blocks["MovPR"] = {
        // 触发条件：前面需要JerkJ,AccJ,DecJ,SpdJ
        init: function () {
          this.appendField("相对座标上进行多轴PTP运动 ");
          this.appendDummyInput()
            .appendField("目标位置点位(No.)")
            .appendField(new Blockly.FieldTextInput("null"), "num1");
          this.appendDummyInput()
            .appendField(" BMode设定：")
            .appendField(new Blockly.FieldNumber(1, 0, 10000, 1), "num2");
          this.appendDummyInput()
            .appendField(" 最大运动速度设定(%)")
            .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num3");
          this.appendDummyInput()
            .appendField(" 运动加速度设定(%)")
            .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num4");
          this.appendDummyInput()
            .appendField(" 运动减速度设定(%)")
            .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num5");
          this.appendDummyInput()
            .appendField(" 运动加加速度设定(%)")
            .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num6");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(99);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["MovLR"] = {
        // 需要预先设置MovL_MODE!!!
        // REAL_SPEED / PERCENT_SPEED
        // 触发条件：前面需要JerkJ,AccJ,DecJ,SpdJ
        init: function () {
          this.appendField("相对座标上进行多轴Line运动 ");
          this.appendDummyInput()
            .appendField("目标位置点位(No.)")
            .appendField(new Blockly.FieldTextInput("null"), "num1");
          this.appendDummyInput()
            .appendField(" BMode设定：")
            .appendField(new Blockly.FieldNumber(1, 0, 10000, 1), "num2");
          this.appendDummyInput()
            .appendField(" 最大运动速度设定(mm/sec / %)")
            .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num3");
          this.appendDummyInput()
            .appendField(" 运动加速度设定(mm/sec^2 / %)")
            .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num4");
          this.appendDummyInput()
            .appendField(" 运动减速度设定(mm/sec^2 / %)")
            .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num5");
          this.appendDummyInput()
            .appendField(" 运动加加速度设定(mm/sec^3 / %)")
            .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num6");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(246);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      // Blockly.Blocks["MArchP"] = {
      //   // 触发条件：前面需要JerkJ,AccJ,DecJ,SpdJ
      //   init: function () {
      //     this.appendField("相对座标上进行多轴Line运动 ");
      //     this.appendDummyInput()
      //       .appendField("目标位置点位(No.)")
      //       .appendField(new Blockly.FieldTextInput("null"), "num1");
      //     this.appendDummyInput()
      //       .appendField(" Z轴最高上升高度(mm)")
      //       .appendField(new Blockly.FieldNumber(0, 0, 10000, 1), "h1");
      //     this.appendDummyInput()
      //       .appendField(" Z轴最高安全高度(mm)")
      //       .appendField(new Blockly.FieldNumber(0, 0, 10000, 1), "h2");
      //     this.appendDummyInput()
      //       .appendField(" Z轴最低安全高度(mm)")
      //       .appendField(new Blockly.FieldNumber(0, 0, 10000, 1), "h3");
      //     this.appendDummyInput()
      //       .appendField(" 最大运动速度设定(%)")
      //       .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num2");
      //     this.appendDummyInput()
      //       .appendField(" 运动加速度设定(%)")
      //       .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num3");
      //     this.appendDummyInput()
      //       .appendField(" 运动减速度设定(%)")
      //       .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num4");
      //     this.appendDummyInput()
      //       .appendField(" 运动加加速度设定(%)")
      //       .appendField(new Blockly.FieldNumber(0, 0, 100, 1), "num5");
      //     this.setPreviousStatement(true);
      //     this.setNextStatement(true);
      //     this.setColour(259);
      //     this.setTooltip("");
      //     this.setHelpUrl("");
      //   },
      // };
      Blockly.Blocks["MArchP"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("目标位置点位(No.)")
            .appendField(new Blockly.FieldTextInput("null"), "num1");
          this.appendDummyInput()
            .appendField(" Z轴最高上升高度(mm)")
            .appendField(new Blockly.FieldNumber(10, 0, 10000, 1), "h1");
          this.appendDummyInput()
            .appendField(" Z轴最高安全高度(mm)")
            .appendField(new Blockly.FieldNumber(10, 0, 10000, 1), "h2");
          this.appendDummyInput()
            .appendField(" Z轴最低安全高度(mm)")
            .appendField(new Blockly.FieldNumber(10, 0, 10000, 1), "h3");
          this.appendStatementInput("DO").appendField("do");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(241);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["MArchL"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("目标位置点位(No.)")
            .appendField(new Blockly.FieldTextInput("null"), "num1");
          this.appendDummyInput()
            .appendField(" Z轴最高上升高度(mm)")
            .appendField(new Blockly.FieldNumber(10, 0, 10000, 1), "h1");
          this.appendDummyInput()
            .appendField(" Z轴最高安全高度(mm)")
            .appendField(new Blockly.FieldNumber(10, 0, 10000, 1), "h2");
          this.appendDummyInput()
            .appendField(" Z轴最低安全高度(mm)")
            .appendField(new Blockly.FieldNumber(10, 0, 10000, 1), "h3");
          this.appendStatementInput("DO").appendField("do");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(241);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["MArchPT"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("???")
            .appendField(new Blockly.FieldNumber(0.5, 0, 10000, 0.1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(251);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["MArchLT"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("???")
            .appendField(new Blockly.FieldNumber(0.5, 0, 10000, 0.1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(261);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["MovJ"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("???")
            .appendField(new Blockly.FieldNumber(0.5, 0, 10000, 0.1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(271);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["MovCIRC"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("???")
            .appendField(new Blockly.FieldNumber(0.5, 0, 10000, 0.1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(281);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["MovCIRC_DIR"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("???")
            .appendField(new Blockly.FieldNumber(0.5, 0, 10000, 0.1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(291);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["MovCIRC_EX"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("???")
            .appendField(new Blockly.FieldNumber(0.5, 0, 10000, 0.1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(281);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["StopAxis"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("???")
            .appendField(new Blockly.FieldNumber(0.5, 0, 10000, 0.1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(271);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      Blockly.Blocks["StopGroup"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("???")
            .appendField(new Blockly.FieldNumber(0.5, 0, 10000, 0.1), "num1")
            .appendField(new Blockly.FieldNumber(0.5, 0, 10000, 0.1), "num2")
            .appendField(new Blockly.FieldNumber(0.5, 0, 10000, 0.1), "num3")
            .appendField(new Blockly.FieldNumber(0.5, 0, 10000, 0.1), "num4");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(261);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      //
      //
      //
      Blockly.Blocks["DELAY"] = {
        init: function () {
          this.appendDummyInput()
            .appendField("等待(sec)")
            .appendField(new Blockly.FieldNumber(0.5, 0, 10000, 0.1), "num");
          this.setPreviousStatement(true);
          this.setNextStatement(true);
          this.setColour(291);
          this.setTooltip("");
          this.setHelpUrl("");
        },
      };
      // Custom code generation

      // Blockly.Lua['text_indexOf'] = function (block) {
      //   // Search the text for a substring.
      //   var operator = block.getFieldValue('END') == 'FIRST' ? 'indexOf' : 'lastIndexOf';
      //   var subString = Blockly.JavaScript.valueToCode(block, 'FIND',
      //     Blockly.JavaScript.ORDER_NONE) || '\'\'';
      //   var text = Blockly.JavaScript.valueToCode(block, 'VALUE',
      //     Blockly.JavaScript.ORDER_MEMBER) || '\'\'';
      //   var code = text + '.' + operator + '(' + subString + ')';
      //   return [code, Blockly.JavaScript.ORDER_MEMBER];
      // };

      // valueToCode方法顾名思义就是将输入的值转换为代码的方法，第一个参数为积木本身，即被执行该方法的对象，第二个参数是要取值的字段名，第三个参数是执行的顺序，在多个积木并行连接的时候有用，一般默认为0就好。

      // Blockly.Lua["lua_demo_2"] = function (block) {
      //   // 对于变量下拉列表，此函数返回变量下拉列表的面向用户的名称。重要的是要注意，此名称不必与生成的代码中使用的变量名称相同。例如，变量名“ for”在Blockly中是合法的，但在大多数语言中会与保留字冲突，因此将重命名为“ for2”。同样，阿拉伯语变量名“ متغير”在Blockly中是合法的，但在大多数语言中都是非法的，因此将其重命名为“ _D9_85_D8_AA_D8_BA_D9_8A_D8_B1”。要获取生成的代码中可能使用的Blockly变量名称，请使用以下调用：
      //   // var VARIABLE = Blockly.Lua.nameDB_.getName(block.getFieldValue('VARIABLE'), Blockly.Variables.NAME_TYPE);
      //   var VARIABLE = block.getField("VARIABLE_NAME").getText();
      //   var DELTA =
      //     Blockly.Lua.valueToCode(block, "DELTA", Blockly.Lua.ORDER_ATOMIC) ||
      //     0;
      //   var code = VARIABLE + "=" + VARIABLE + "+" + DELTA + "\n";
      //   return code;
      // };
      // Blockly.Lua["variables_get_panda"] = function (block) {
      //   var VARIABLE = block.getField("num").getText();
      //   var code = "variables_get_panda(" + VARIABLE + ")\n";
      //   return code;
      // };
      // Blockly.Lua["variables_set_panda"] = function (block) {
      //   var VARIABLE = block.getField("num").getText();
      //   var code = "variables_set_panda(" + VARIABLE + ")\n";
      //   return code;
      // };
      //代码生成
      Blockly.Lua["MaxSpdL"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "MaxSpdL(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["MaxAccL"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "MaxAccL(" + VARIABLE + ")\n";
        // TotalData = TotalData + code;
        // console.log("MaxAccL", TotalData);
        return code;
      };
      Blockly.Lua["MaxSpdJ"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "MaxSpdJ(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["MaxAccJ"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "MaxAccJ(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["DecL"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "DecL(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["DecJ"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "DecJ(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["AccL"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "AccL(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["AccJ"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "AccJ(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["SpdL"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "SpdL(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["SpdJ"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "SpdJ(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["JerkL"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "JerkL(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["JerkJ"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "JerkJ(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["SetPassMode"] = function (block) {
        var VARIABLE = block.getField("passMode").getText();
        var code = "SetPassMode(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["SetWaitCmdMode"] = function (block) {
        var VARIABLE = block.getField("waitMode").getText();
        var code = "SetWaitCmdMode(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["SetMArchPPS"] = function (block) {
        var VARIABLE = block.getField("marchPps").getText();
        var code = "SetMArchPPS(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["SetPassDistance"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "SetPassDistance(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["SetPassTime"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "SetPassTime(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["MovL_MODE"] = function (block) {
        var VARIABLE = block.getField("movlMode").getText();
        var code = "Movl_MODE = " + VARIABLE + "\n";
        return code;
      };
      Blockly.Lua["LiaoPanNo"] = function (block) {
        var VARIABLE = block.getField("liaopan").getText();
        var code = "tray_number = " + VARIABLE + "\n";
        return code;
      };
      Blockly.Lua["test6"] = function (block) {
        var VARIABLE = block.getField("addr1").getText();
        var code = "tray_base_point = " + VARIABLE + "\n";
        return code;
      };
      Blockly.Lua["test7"] = function (block) {
        var VARIABLE = block.getField("addr2").getText();
        var code = "tray_base_point = " + VARIABLE + "\n";
        return code;
      };
      Blockly.Lua["IOSignal"] = function () {
        var code = "IOSignal";
        return code;
      };
      Blockly.Lua["MovP"] = function (block) {
        // var V1 = block.getField("point").getText();
        // var V2 = block.getField("bMode").getText();
        // var V3 = block.getField("spd").getText();
        // var V4 = block.getField("acc").getText();
        // var V5 = block.getField("dec").getText();
        // var V6 = block.getField("jerk").getText();
        // var code =
        //   "MovP(" +
        //   '"' +
        //   V1 +
        //   '"' +
        //   "," +
        //   '"' +
        //   V2 +
        //   '"' +
        //   "," +
        //   V3 +
        //   "," +
        //   V4 +
        //   "," +
        //   V5 +
        //   "," +
        //   V6 +
        //   ")\n";
        // return code;
        var VARIABLE = block.getField("num").getText();
        var code = "MovP(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["MovL"] = function (block) {
        // var VARIABLE = [];
        // var code;
        // for (var k = 0; k < 3; k++) {
        //   VARIABLE[k] = block.getField("num" + (k + 1)).getText();
        // }
        // if (isNaN(VARIABLE[0])) {
        //   code = '"' + VARIABLE[0] + '"';
        // } else {
        //   code = VARIABLE[0];
        // }
        // code = code + ', "' + VARIABLE[1] + '"';
        // for (var i = 1; i < 4; i++) {
        //   if (VARIABLE[i] == 0) {
        //     continue;
        //   }
        //   code = code + ", " + VARIABLE[i];
        // }
        // code = "MovL(" + code + ")\n";
        // return code;
        var VARIABLE = block.getField("num").getText();
        var code = "MovL(" + VARIABLE + ")\n";
        return code;
      };
      Blockly.Lua["MovL_EX"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "MovL_EX(" + VARIABLE + ")\n";
        return code;
      };
      //
      //
      //
      Blockly.Lua["MovPR"] = function (block) {
        var VARIABLE = [];
        var code;
        for (var k = 0; k < 6; k++) {
          VARIABLE[k] = block.getField("num" + (k + 1)).getText();
        }
        if (isNaN(VARIABLE[0])) {
          code = '"' + VARIABLE[0] + '"';
        } else {
          code = VARIABLE[0];
        }
        code = code + ', "' + VARIABLE[1] + '"';
        for (var i = 2; i < 6; i++) {
          if (VARIABLE[i] === 0) {
            continue;
          }
          code = code + ", " + VARIABLE[i];
        }
        code = "MovPR(" + code + ")\n";
        return code;
      };
      Blockly.Lua["MovLR"] = function (block) {
        block.getField("num").getText();
        var code = "???";
        return code;
      };
      // Blockly.Lua["MArchP"] = function (block) {
      //   let VARIABLE = [];
      //   let H = [];
      //   let code;
      //   for (var k = 0; k < 5; k++) {
      //     VARIABLE[k] = block.getField("num" + (k + 1)).getText();
      //   }
      //   for (var x = 0; x < 3; x++) {
      //     H[x] = block.getField("h" + (x + 1)).getText();
      //   }
      //   if (isNaN(VARIABLE[0])) {
      //     code = '"' + VARIABLE[0] + '"';
      //   } else {
      //     code = VARIABLE[0];
      //   }
      //   for (var j = 0; j < 3; j++) {
      //     if (H[j] == 0) {
      //       code = code + ", ?";
      //     }
      //     code = code + ", " + H[j];
      //   }
      //   for (var i = 1; i < 5; i++) {
      //     if (VARIABLE[i] == 0) {
      //       continue;
      //     }
      //     code = code + ", " + VARIABLE[i];
      //   }
      //   code = "MArchP(" + code + ")\n";
      //   // VARIABLE = block.getField("num").getText();
      //   // code = "???";
      //   return code;
      // };
      Blockly.Lua["MArchL"] = function (block) {
        let VARIABLE;
        let H = [];
        let code;
        // let addition;
        // Get first position var
        VARIABLE = block.getField("num1").getText();
        // Get the next 3 para
        for (var x = 0; x < 3; x++) {
          // Get H0=h1, H1=h2, H2=h3
          H[x] = block.getField("h" + (x + 1)).getText();
        }
        // Decide first var is null or not
        if (isNaN(VARIABLE)) {
          code = '"' + VARIABLE + '"';
        } else {
          code = VARIABLE;
        }
        // Convert to code of H0, H1, H2
        for (var j = 0; j < 3; j++) {
          if (H[j] == 0) code = code + ", ?";
          else code = code + ", " + H[j];
        }

        let c =
          Blockly.JavaScript.valueToCode(
            block,
            "spd",
            Blockly.JavaScript.ORDER_ADDITION
          ) || "0";
        code = code + c;
        code = "MArchL(" + code + ")\n";
        return code;
      };
      Blockly.Lua["MArchPT"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "???";
        return code;
      };
      Blockly.Lua["MArchLT"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "???";
        return code;
      };
      Blockly.Lua["MovJ"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "???";
        return code;
      };
      Blockly.Lua["MovCIRC"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "???";
        return code;
      };
      Blockly.Lua["MovCIRC_DIR"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "???";
        return code;
      };
      Blockly.Lua["MovCIRC_EX"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "???";
        return code;
      };
      Blockly.Lua["StopAxis"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "???";
        return code;
      };
      Blockly.Lua["StopGroup"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "???";
        return code;
      };
      //
      //
      //
      Blockly.Lua["DELAY"] = function (block) {
        var VARIABLE = block.getField("num").getText();
        var code = "DELAY(" + VARIABLE + ")\n";
        // TotalData = TotalData + code;
        // console.log("DELAY", TotalData);
        return code;
      };
    },
    // // 代码生成器
    // myUpdateFunction(event) {
    //   var code = Blockly.JavaScript.workspaceToCode(this.workspace);
    //   document.getElementById('content_lua').textContent = code;
    // },
    // // 获取blockly工作区中的code和xml结构
    // getBlockData() {
    //   const code = Blockly.JavaScript.workspaceToCode(this.workspace);
    //   const xml = Blockly.Xml.workspaceToDom(this.workspace)
    //   const xmlText = Blockly.Xml.domToText(xml);
    // },
    // // 回显工作区中的xml结构
    // setBlockData(xmlText) {
    //   this.clearBlockData();
    //   const xml = Blockly.Xml.textToDom(xmlText);
    //   Blockly.Xml.domToWorkspace(xml, this.workspace);
    // },
    // // 清空工作区
    // clearBlockData() {
    //   this.workspace.clear();
    // },
    // 注册工具箱
    registerToolboxCategory() {
      this.workspace.registerToolboxCategoryCallback(
        "CUSTOM_CREATE_VARIABLE",
        this.createFlyout
      );
      this.registerButton();
    },
    // 工具箱内按钮生成
    createFlyout(workspace) {
      let xmlList = [];
      const button = document.createElement("button");
      button.setAttribute("text", "添加变量块");
      button.setAttribute("callbackKey", "ADD_VAR_BLOCK");
      xmlList.push(button);
      const blockList = Blockly.Variables.flyoutCategoryBlocks(workspace);
      // console.log(Blockly.VariablesDynamic.flyoutCategoryBlocks(workspace), Blockly.Variables.flyoutCategoryBlocks(workspace), Blockly.Variables.flyoutCategory(workspace))
      // xmlList = blockList.concat(xmlList);
      xmlList = xmlList.concat(blockList);
      return xmlList;
    },
    // 注册工具箱内按钮
    registerButton() {
      this.workspace.registerButtonCallback(
        "ADD_VAR_BLOCK",
        this.buttonClickCallbackEvent
      );
    },
    // 按钮点击事件
    buttonClickCallbackEvent(e) {
      //this.$refs.createVariablePopup.show({}, "test");
    },
    // 创建变量块
    createVariable(name) {
      let [text, type, msg] = [this.getValidInput(name), "", ""];
      if (text) {
        const existing = Blockly.Variables.nameUsedWithAnyType(
          text,
          this.workspace
        );
        if (existing) {
          if (existing.type === type) {
            msg = Blockly.Msg["VARIABLE_ALREADY_EXISTS"].replace(
              "%1",
              existing.name
            );
          } else {
            msg = Blockly.Msg["VARIABLE_ALREADY_EXISTS_FOR_ANOTHER_TYPE"];
            msg = msg
              .replace("%1", existing.name)
              .replace("%2", this.getDisplayName(existing.type));
          }
          message.warning(msg);
        } else {
          let workspace = Blockly.mainWorkspace; // if not will use a proxy of the workspace
          workspace.createVariable(text, type);
        }
      }
      if (!msg) {
        let workspace = Blockly.mainWorkspace; // if not will use a proxy of the workspace
        workspace.createVariable(text, type);
      }
    },
    // 类型判断
    getDisplayName(type) {
      // for (let i = 0; i < this.types_.length; i++) {
      //   const typeNames = this.types_[i];
      //   if (type === typeNames[1]) {
      //     return typeNames[0];
      //   }
      // }
      // return '';
      return type;
    },
    // 获取合法变量名称
    getValidInput(newVar) {
      if (newVar) {
        newVar = newVar.replace(/[\s\xa0]+/g, " ").trim();
        if (
          newVar === Blockly.Msg["RENAME_VARIABLE"] ||
          newVar === Blockly.Msg["NEW_VARIABLE"]
        ) {
          newVar = null;
        }
      }
      return newVar;
    },

    /**
     * Populate the currently selected pane with content generated from the blocks.
     */
    renderContent(event) {
      // var content = document.getElementById('content_' + Code.selected);
      // // Initialize the pane.
      // if (content.id === 'content_xml') {
      //   var xmlTextarea = document.getElementById('content_xml');
      //   var xmlDom = Blockly.Xml.workspaceToDom(Code.workspace);
      //   var xmlText = Blockly.Xml.domToPrettyText(xmlDom);
      //   xmlTextarea.value = xmlText;
      //   xmlTextarea.focus();
      // } else if (content.id === 'content_json') {
      //   var jsonTextarea = document.getElementById('content_json');
      //   jsonTextarea.value = JSON.stringify(
      //     Blockly.serialization.workspaces.save(Code.workspace), null, 2);
      //   jsonTextarea.focus();
      // } else if (content.id === 'content_javascript') {
      //   Code.attemptCodeGeneration(Blockly.JavaScript);
      // } else if (content.id === 'content_python') {
      //   Code.attemptCodeGeneration(Blockly.Python);
      // } else if (content.id === 'content_php') {
      //   Code.attemptCodeGeneration(Blockly.PHP);
      // } else if (content.id === 'content_dart') {
      //   Code.attemptCodeGeneration(Blockly.Dart);
      // } else if (content.id === 'content_lua') {
      this.attemptCodeGeneration(Blockly.Lua);
      // }
      // if (typeof PR === 'object') {
      //   PR.prettyPrint();
      // }
      // console.log(event)
      if (event.type == Blockly.Events.CLICK) {
        if (event.targetType == "block" && event.blockId) {
          let block = this.workspace.getBlockById(event.blockId);
          console.log("click", block.type, block);
          this.currentBlock = block;
        } else {
          this.resetDisplay();
        }
      }
    },

    resetDisplay() {
      this.currentBlock = "请拖拽到中间区域";
    },

    /**
     * Attempt to generate the code and display it in the UI, pretty printed.
     * @param generator {!Blockly.Generator} The generator to use.
     */
    attemptCodeGeneration(generator) {
      // var content = document.getElementById('content_' + Code.selected);
      var content = document.getElementById("content_lua");
      content.textContent = "";
      if (this.checkAllGeneratorFunctionsDefined(generator)) {
        var code = generator.workspaceToCode(this.workspace);
        TotalData = code;
        content.textContent = code;
        // Remove the 'prettyprinted' class, so that Prettify will recalculate.
        content.className = content.className.replace("prettyprinted", "");
      }
      console.log("attemptCodeGeneration\n", testtotaldata);
    },

    /**
     * Check whether all blocks in use have generator functions.
     * @param generator {!Blockly.Generator} The generator to use.
     */
    checkAllGeneratorFunctionsDefined(generator) {
      var blocks = this.workspace.getAllBlocks(false);
      var missingBlockGenerators = [];
      for (var i = 0; i < blocks.length; i++) {
        var blockType = blocks[i].type;
        if (!generator[blockType]) {
          if (missingBlockGenerators.indexOf(blockType) === -1) {
            missingBlockGenerators.push(blockType);
          }
        }
      }

      var valid = missingBlockGenerators.length === 0;
      if (!valid) {
        var msg =
          "The generator code for the following blocks not specified for " +
          generator.name_ +
          ":\n - " +
          missingBlockGenerators.join("\n - ");
        Blockly.dialog.alert(msg); // Assuming synchronous. No callback.
      }
      return valid;
    },

    // popup event
    updateData(data) {
      console.log("updateData", data.variableName);
      this.createVariable(data.variableName);
    },
    save() {
      console.log(typeof TotalData);
      axios
        // eslint-disable-next-line no-undef
        .post("http://192.168.1.102:8080/robot/luaFile", {
          luaString: TotalData, //TotalData 是实时生成代码放的string
          // "MaxSpdL(2000)\n" +
          // "MaxAccL(20000)\n" +
          // "MaxSpdJ(9000)\n" +
          // "MaxAccJ(300)\n" +
          // "\n" +
          // "SpdL(100)\n" +
          // "AccL(500)\n" +
          // "DecL(500)\n" +
          // "\n" +
          // "MovP(1)\n" +
          // "\t\n" +
          // "DELAY(0.5)\n" +
          // "MovP(2)",
        })
        .then(function (response) {
          console.log("Success!");
          console.log(typeof response);
        })
        .catch(function (error) {
          console.log(error);
          //console.log(typeof data);
        });
    },
    run() {
      axios
        // eslint-disable-next-line no-undef
        .post("http://192.168.1.102:8080/robot/command", {
          command: "Run",
        })
        .then(function (response) {
          //console.log(data);
          console.log(typeof response);
        })
        .catch(function (error) {
          console.log(error);
          //console.log(typeof data);
        });
    },
    //运行终止
    stop() {
      axios
        // eslint-disable-next-line no-undef
        .post("http://192.168.1.102:8080/robot/command", {
          command: "Stop",
        })
        .then(function (response) {
          //console.log(data);
          console.log(typeof response);
        })
        .catch(function (error) {
          console.log(error);
          //console.log(typeof data);
        });
    },
    //运行继续
    conti() {
      axios
        // eslint-disable-next-line no-undef
        .post("http://192.168.1.102:8080/robot/command", {
          command: "Continue",
        })
        .then(function (response) {
          //console.log(data);
          console.log(typeof response);
        })
        .catch(function (error) {
          console.log(error);
          //console.log(typeof data);
        });
    },
    //运行暂停
    pause() {
      axios
        // eslint-disable-next-line no-undef
        .post("http://192.168.1.102:8080/robot/command", {
          command: "Pause",
        })
        .then(function (response) {
          //console.log(data);
          console.log(typeof response);
        })
        .catch(function (error) {
          console.log(error);
          //console.log(typeof data);
        });
    },
  },
};
</script>

<style lang="less">
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
