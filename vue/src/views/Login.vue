<template>
  <div class="wrapper">
    <div
        style="margin: 200px auto; background-color: #fff; width: 350px; height: 300px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>
      <el-form :model="user" ref="userForm" :rules="rules">
        <el-form-item prop="username">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password
                    v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="warning" size="small" autocomplete="off" @click="$router.push('/register')">注册</el-button>
          <el-button type="primary" size="small" autocomplete="off" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {baseURL} from "../../public/urlConfig";
import axios from "axios";

export default {
  name: "Login",
  data() {
    return {
      user: {
        username: "admin",
        password: "123456"
      },
      ip: "",
      province: "",
      city: "",
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 1, message: '不能为空', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 1, message: '不能为空', trigger: 'blur'}
        ],
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    login() {
      this.$refs['userForm'].validate((valid) => {
        if (valid) {  // 表单校验合法
          var url = baseURL + "/user/login";
          axios.get(url, {
            params: {
              username: this.user.username,
              password: this.user.password
            }
          }).then((res) => {
            //res.data 才表示接口返回的数据
            var vo = res.data;
            if (vo.code === 10000) {
              localStorage.setItem("username", vo.data.username)  // 存储用户信息到浏览器
              localStorage.setItem("img", vo.data.userImg)  // 存储用户信息到浏览器
              localStorage.setItem("token", vo.msg)
              localStorage.setItem("userId", vo.data.userId)
              // 动态设置当前用户的路由
              this.$message.success("登录成功")
              this.getIp()
              let hostName = this.$route.query.redirect;  // 获取域名
              if (hostName != null) {   // 判断如果域名是你项目域名，说明是从本网站内部跳转过来的，
                this.$router.push(hostName);   // 登录成功后，返回上次进入的页面；
              } else {
                this.$router.push("/");  // 若不是网站内部跳转过来的，登陆成功后进入网站首页
              }
            } else {
              this.$message.error(res.data.msg)
            }
          })
        }
      });
    },
    load() {
      axios.get(baseURL + "/user-login-history/getIp").then(
          res => {
            this.ip = res.data
            axios.get("https://restapi.amap.com/v3/ip", {
              params: {
                ip: res.data,
                key: '1983be331c48ef28867f61e61f878987',
              }
            }).then(
                res => {
                  this.province = res.data.province
                  this.city = res.data.city
                }
            )
          }
      )
    },
    getIp() {
      var position = {
        "area": this.city,
        "ip": this.ip,
        "userId": localStorage.getItem("userId"),
        "country": this.province
      }
      axios.post(baseURL + "/user-login-history/insert", position)
    }
  }
}
</script>

<style scoped>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom, #42b983, #3F5EFB);
  overflow: hidden;
}
</style>
