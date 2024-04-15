<template>
  <a-modal
    :title="ModalTitle"
    :visible="showEdit"
    @ok="handleSubmit"
    @cancel="handleEditCancel"
    :bodyStyle="bodyStyle"
    :destroyOnClose="true"
    class="author-info"
  >
    <!-- :forceRender="true" -->
    <a-form
      ref="formRef"
      :model="formState"
      :rules="rules"
      name="basic"
      v-bind="formItemLayout"
      autocomplete="off"
    >
      <!-- @finish="handleSubmit"
            @finishFailed="onFinishFailed" -->
      <a-form-item
        label="variableName"
        name="variableName"
        has-feedback
        class="regItem"
      >
        <!-- :rules="[{ required: true, message: 'Please input the variableName!' }]"
                     v-bind="formItemLayout" -->
        <a-input v-model:value="formState.variableName" />
      </a-form-item>

      <!-- <a-form-item>
        <a-button type="primary"
                  html-type="submit">Submit</a-button>
      </a-form-item> -->
    </a-form>
  </a-modal>
</template>

<script lang="ts">
import {
  ref,
  defineComponent,
  reactive,
  toRaw,
  defineProps,
  defineEmits,
  nextTick,
} from "vue";

import Blockly from "blockly";

import { ValidateErrorEntity } from "ant-design-vue/es/form/interface";
import type { Rule } from "ant-design-vue/es/form";

interface FormState {
  variableName: string;
}

export default defineComponent({
  emits: ["updateData"],
  props: {
    workspace: {
      require: true,
      type: Blockly.Workspace,
      default: () => {
        return new Blockly.Workspace();
      },
    },
  },
  setup(props, context) {
    console.log(props);
    const formRef = ref();
    const showEdit = ref<boolean>(false);
    const ModalTitle = ref("");
    const currentRecord = ref({});
    const bodyStyle = ref({ width: "100%", margin: "0 auto" });
    const formItemLayout = ref({
      labelCol: { span: 8 },
      wrapperCol: { span: 15, offset: 1 },
    });

    const formState = reactive<FormState>({
      variableName: "item",
    });

    let variableName_validate = async (_rule: Rule, value: string) => {
      if (value === "") {
        return Promise.reject("Please specify a name for the variable");
      } else {
        // 获取合法变量名称
        let getValidInput = (newVar: any) => {
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
        };
        const existing = Blockly.Variables.nameUsedWithAnyType(
          getValidInput(value),
          props.workspace
        );
        if (existing)
          return Promise.reject(
            Blockly.Msg["VARIABLE_ALREADY_EXISTS"].replace("%1", existing.name)
          );
        return Promise.resolve();
      }
    };

    const rules: Record<string, Rule[]> = {
      variableName: [
        { required: true, validator: variableName_validate, trigger: "change" },
      ],
    };

    // Page logics

    const show = (record: any, type: any) => {
      showEdit.value = true;
      ModalTitle.value = type;
      currentRecord.value = record;
      // console.log("mode", type, record);
      nextTick(() => {
        formRef.value.validate();
      });
    };

    // const handleSubmit = (values: any) => {
    //   console.log("Success:", values);
    //   let reviewerVo = {
    //     ...values,
    //   };
    //   console.log(reviewerVo);
    //   context.emit("updateData", reviewerVo);
    // };
    const handleSubmit = () => {
      formRef.value
        .validate()
        .then(() => {
          console.log("values", formState, toRaw(formState));
          let reviewerVo = {
            ...toRaw(formState),
          };
          console.log(reviewerVo);
          context.emit("updateData", reviewerVo);
          handleEditCancel();
        })
        .catch((error: ValidateErrorEntity<FormState>) => {
          console.log("error", error);
        });
    };

    const onFinishFailed = (errorInfo: any) => {
      console.log("Failed:", errorInfo);
    };

    const handleEditCancel = () => {
      formRef.value.resetFields();
      showEdit.value = false;
    };

    return {
      formRef,
      ModalTitle,
      formItemLayout,
      showEdit,
      bodyStyle,
      formState,
      rules,
      show,
      handleSubmit,
      onFinishFailed,
      handleEditCancel,
    };
  },
});
</script>

<style lang="less" scoped>
.author-info {
  width: 700px !important;
  // height: 500px;
}

.regItem {
  // display: flex;
  // align-items: center;

  :deep(.ant-input-suffix) {
    .anticon-search {
      display: none;
    }
  }

  :deep(.ant-form-item-label) {
    white-space: unset;
    line-height: unset;
  }

  // /deep/ .ant-form-item-label,
  // /deep/ .ant-form-item-control-wrapper {
  //   // line-height: 24px;
  //   // white-space: normal;

  //   label {
  //     color: rgba(255, 255, 255, 0.85);
  //     // width: 32px;
  //   }
  // }

  // /deep/ .ant-form-item-control-wrapper {
  //   display: inline-block;
  //   vertical-align: top;
  // }
}
</style>
