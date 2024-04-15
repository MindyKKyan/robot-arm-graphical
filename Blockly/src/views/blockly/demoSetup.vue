<template>
  <div class="">

  </div>
</template>

<script setup lang="ts">
// https://v3.cn.vuejs.org/api/sfc-spec.html#%E4%BB%8B%E7%BB%8D
// 与普通的 <script> 只在组件被首次引入的时候执行一次不同，<script setup> 中的代码会在每次组件实例被创建的时候执行。
// setup表示组件被创建之前、props被解析之后执行，它是组合式 API 的入口。
// 在使用 setup组合时，不建议使用选项式的生命周期，建议使用 on* 系列 hooks生命周期。
import { ref } from 'vue'

const post = await fetch(`/api/post/1`).then(r => r.json())
const msg = 'Hello!'
function log() {
  console.log(msg)
}
// import { capitalize } from './helpers'
// import * as Form from './form-components'
// import MyComponent from './MyComponent.vue'
// <MyComponent />
// <component :is="someCondition ? MyComponent : Bar" />
const num = ref(100)
const add = () => num.value++

const props1 = defineProps({
  name: {
    type: String,
    required: false,
    default: 'Petter',
  },
  userInfo: Object,
  title: String || Number,
  tags: Array,
})
// or
const props2 = defineProps<{
  userInfo: object,
  title: string | number,
  tags?: string[]
}>()
// or
interface Props {
  userInfo: {id: number},
  title: string | number,
  tags?: string[]
}
const props3 = withDefaults(defineProps<Props>(), {
  userInfo: () => {return {id: 0}},
  title: "string",
  tags: () => ['one', 'two']
})

const emit1 = defineEmits(['change', 'update'])
// or
const emit2 = defineEmits<{
  (e: 'change', id: number): void
  (e: 'update', value: string): void
}>()
emit1('change', 1)
emit2('update', 'Tom')
</script>
