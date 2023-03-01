<template>
  <div>
    <div style="background-color: #F5F5F5;">
      <el-row style="width: 80%;margin: 0 auto">
        <el-col :span="12" style="margin-top: 5px;margin-bottom: 5px">
          <div v-if=!username style="float: left">
            <el-link :underline="false" @click="login">登录</el-link>
            <el-divider direction="vertical"></el-divider>
            <el-link :underline="false" href="/register">注册</el-link>
          </div>
          <div v-else style="float: left">
            <el-dropdown>
              <span class="el-dropdown-link">
                <el-link :underline="false" href="/person">{{ username }}</el-link> <i
                  class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item style="width: 150px;height: 60px">
                  <el-row :gutter="20">
                    <el-col :span="8">
                      <el-avatar :src="'http://localhost:9090/img/'+img" style="margin-top: 5px"></el-avatar>
                    </el-col>
                    <el-col :span="16">
                      <el-link :underline="false" href="/person">个人中心</el-link>
                      <el-link :underline="false" @click="logout">退出登录</el-link>
                    </el-col>
                  </el-row>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
            <el-divider direction="vertical"></el-divider>

          </div>
        </el-col>
        <el-col :span="12" style="margin-top: 5px;margin-bottom: 5px">
          <div style="float: right">
            <el-link :underline="false" href="/" icon="el-icon-s-home">商城首页</el-link>
            <el-divider direction="vertical"></el-divider>
<!--            <el-link :underline="false" :href="'/orderList?uid='+userId" icon="el-icon-circle-check">我的订单</el-link>-->
<!--            <el-divider direction="vertical"></el-divider>-->
            <el-link :underline="false" :href="'/shopcart?uid='+userId" icon="el-icon-shopping-cart-full">我的购物车</el-link>
            <el-divider v-if="username == 'admin' ? true : false" direction="vertical"></el-divider>
            <el-link icon="el-icon-menu" :underline="false" href="/addProduct" v-if="username == 'admin' ? true : false">商家后台</el-link>
          </div>
        </el-col>
      </el-row>
    </div>

    <div>
      <el-row style="width: 80%;margin: 0 auto">
        <el-col :span="12" style="margin-top: 5px;margin-bottom: 5px">
          <el-link href="/" style="float: left" :underline="false">
            <img src="../assets/logo.png" width="130px"/>
          </el-link>
        </el-col>
        <el-col :span="12" style="margin-top: 10px;margin-bottom: 5px">
          <div style="margin-top: 15px;">
            <el-input placeholder="请输入内容" v-model="input" @change="search">
              <el-button slot="append" icon="el-icon-search" style="background-color: #FF5A00;color: white"
                         @click="fuzzyQuery">搜索
              </el-button>
            </el-input>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import router from "@/router";

export default {
  name: "Navi",
  data() {
    return {
      username: localStorage.getItem("username"),
      img: localStorage.getItem("img"),
      userId: localStorage.getItem("userId"),
      input: "",
      select: "",
    };
  },
  methods: {
    logout() {
      localStorage.removeItem("username")
      localStorage.removeItem("img")
      localStorage.removeItem("token")
      localStorage.removeItem("userId")
      this.$message.success("退出成功")
      this.username = ""
    },
    person() {
      this.username = localStorage.getItem("username")
      router.push("/person")
    },
    login() {
      router.push({
        path: '/login',
        query: {
          redirect: router.currentRoute.fullPath  // 防止从外部进来登录
        }
      })
    },
    fuzzyQuery() {
      router.push({
        path: '/search',
        query: {
          keyword: this.input  // 防止从外部进来登录
        }
      })
    },
    search(v) {
      router.push({
        path: '/search',
        query: {
          keyword: v  // 防止从外部进来登录
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
