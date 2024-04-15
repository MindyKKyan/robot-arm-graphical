<template>
  <div class="container">
    <div class="div1">
      <span class="title">欢迎使用图形化编程软件</span><br />
      <span class="version">V 1.0.1</span>
    </div>
    <div class="div2">
      <img src="../../public/img/proEasy.png" alt="ProEasy" class="proeasy" />
    </div>
    <div class="div3">
      <br /><br />
      <button @click="connection" class="initbtn">连接机器人</button>

      <!--<button @click="setbot" class="initbtn">设定机器人</button>-->
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  methods: {
    connection() {
      //点击跳转至上次浏览页面
      // this.$router.go(-1)
      //指定跳转地址
      this.$router.replace("/blockly");
    },
    setbot() {
      //点击跳转至上次浏览页面
      // this.$router.go(-1)

      //指定跳转地址
      //this.$router.replace("/init");
      //let data = "ServoOn";
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
    },
  },
};
</script>

<style scoped>
.container {
  display: grid;
  place-items: center;
}

.div1 {
  width: 500px;
  padding-top: 130px;
  background-color: #f9f9fc;
}
.title {
  font-size: 29px;
  font-weight: bold;
}
.version {
  font-size: 20px;
}
.div2 {
  padding: 20px;
  width: 500px;
  background-color: #fdfdff;
}
.proeasy {
  width: 220px;
  height: 210px;
}
.div3 {
  padding: 40px;
  width: 500px;
  background-color: #fdfdff;
}
.initbtn {
  width: 230px;
  height: 39px;
  border-radius: 8px;
  background-color: #001b33;
  color: #eeeeee;
  font-size: 17px;
}
</style>
