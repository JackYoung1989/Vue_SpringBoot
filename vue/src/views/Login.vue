<template>
<div style="width: 100%; height: 100vh; background-color: darkslateblue; overflow: hidden">
  <div style="width: 400px; margin: 100px auto">
    <div style="color: #cccccc; font-size: 30px; text-align: center; padding: 30px">欢迎登录</div>
    <el-form ref="form" :model="form" size="default">
      <el-form-item>
        <el-input prefix-icon="Search" v-model="form.username"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input prefix-icon="setting" v-model="form.password" show-password></el-input>
      </el-form-item>
      <el-form-item>
        <el-button style="width: 100%" type="primary" @click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</div>
</template>

<script>
import request from "../utils/request";

export default {
  name: "Login",
  data() {
    return {
      form: {}
    }
  },
  methods: {
    login() {
      request.post("/user/login", this.form).then(res => {
        if (res.code === "0") {
          this.$message({
            type: "success",
            message: "登录成功"
          })
          console.log("登录成功，存储的数据是：" + res.data)
          //缓存用户信息
          sessionStorage.setItem("user",JSON.stringify(res.data))
          this.$router.push("/") //登录成功之后进行页面跳转，到首页
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>