<template>
  <div class="">
    <!-- blockly工作区 -->
    <div id="blocklyDiv"
         style="height: 480px; width: 600px;"></div>
    <!-- blockly工具栏 -->
    <xml id="toolbox"
         style="display: none">
      <category name="逻辑"
                colour="%{BKY_LOGIC_HUE}">
        <block type="controls_if"></block>
        <block type="logic_compare"></block>
        <block type="logic_operation"></block>
        <block type="logic_negate"></block>
        <block type="logic_boolean"></block>
      </category>
      <sep></sep>
      <category name="数学"
                colour="%{BKY_MATH_HUE}">
        <block type="math_number">
          <field name="NUM">123</field>
        </block>
        <block type="math_arithmetic"></block>
        <block type="math_single"></block>
      </category>
    </xml>
    <!-- blockly代码区 -->
    <textarea name=""
              id="textarea"
              cols="30"
              rows="10"></textarea>
  </div>
</template>

<script setup>
// 组合式API: https://v3.cn.vuejs.org/guide/composition-api-introduction.html#%E4%BB%80%E4%B9%88%E6%98%AF%E7%BB%84%E5%90%88%E5%BC%8F-api
import { ref, onMounted, watch, toRefs, computed } from 'vue'

import Blockly from "blockly"
import "blockly/lua"
import * as zhHans from "blockly/msg/zh-hans"
Blockly.setLocale(zhHans)

let workspace = ref({})

// 代码生成器
const myUpdateFunction = (event) => {
  var code = Blockly.JavaScript.workspaceToCode(workspace)
  document.getElementById('textarea').value = code
}

// 获取blockly工作区中的xml结构
const getXml = () => { return Blockly.Xml.workspaceToDom(workspace) }

const setup = () => {
  workspace = Blockly.inject('blocklyDiv',
    {
      //工具栏
      toolbox: document.getElementById('toolbox'),
      //网格效果
      grid: { spacing: 20, length: 3, colour: '#ccc', snap: true },
      //媒体资源
      // media: './media/',
      //垃圾桶
      trashcan: true
    }
  )
  workspace.addChangeListener(myUpdateFunction)
}

onMounted(() => {
  setup()
})
</script>