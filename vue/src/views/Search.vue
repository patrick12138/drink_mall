<template>
<div>
  <div>
    <div style="background-color: #F5F5F5;">
      <el-row style="width: 80%;margin: 0 auto">
        <el-col :span="12" style="margin-top: 5px;margin-bottom: 5px">
          <div v-if=!username style="float: left">
            <el-link :underline="false" @click="login">登录</el-link>
            <el-divider direction="vertical"></el-divider>
            <el-link :underline="false" href="/register">注册</el-link>
            <!--            <el-link @click="up">更新状态</el-link>-->
          </div>
          <div v-else style="float: left">
            <el-dropdown>
              <span class="el-dropdown-link">
                <el-link :underline="false" href="/person">{{ username }}</el-link> <i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item style="width: 150px;height: 60px">
                  <el-row :gutter="20">
                    <el-col :span="8"><el-avatar :src="'http://localhost:9090/img/'+img" style="margin-top: 5px"></el-avatar></el-col>
                    <el-col :span="16">
                      <el-link :underline="false" href="/person">个人中心</el-link>
                      <el-link :underline="false" @click="logout" >退出登录</el-link>
                    </el-col>
                  </el-row>
                </el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
            <el-divider direction="vertical"></el-divider>

          </div>
        </el-col>
        <el-col :span="12"style="margin-top: 5px;margin-bottom: 5px">
          <div style="float: right">
            <el-link :underline="false" href="/">商城首页</el-link>
            <el-divider direction="vertical"></el-divider>
            <el-link :underline="false" :href="'/orderList?uid='+userId" icon="el-icon-circle-check">我的订单</el-link>
            <el-divider direction="vertical"></el-divider>
            <el-link :underline="false" :href="'/shopcart?uid='+userId" icon="el-icon-shopping-cart-2">我的购物车</el-link>
            <el-divider v-if="username == 'admin' ? true : false" direction="vertical"></el-divider>
            <el-link v-if="username == 'admin' ? true : false" :underline="false" href="/addProduct">商家后台</el-link>
          </div>
        </el-col>
      </el-row>
    </div>
    <div >
      <el-row style="width: 80%;margin: 0 auto">
        <el-col :span="12" style="margin-top: 5px;margin-bottom: 5px">
          <el-link href="/"style="float: left" :underline="false">
            <img src="../assets/logo.png" width="130px" />
          </el-link>
        </el-col>
        <el-col :span="12" style="margin-top: 10px;margin-bottom: 5px">
          <div style="margin-top: 15px;">
            <el-input placeholder="请输入内容" v-model="input" @change="search">
              <el-button slot="append" icon="el-icon-search" style="background-color: #FF5A00;color: white" @click="fuzzyQuery">搜索</el-button>
            </el-input>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
  <div style="width: 80%;margin: 0 auto;">
    <el-row>
      <el-col :span="6" v-for="o in result" >
        <el-card style="border-radius: 15px;margin: 5px" shadow="hover">
          <el-row>
            <router-link :to="{name:'Product',query:{pid:o.productId}}">
              <img :src="'http://localhost:9090/img/'+o.url" style="  width: 100%;height: 100%;display: block;">
            </router-link>
          </el-row>
          <el-row>
            <el-col :span="16" style=" text-align: left;color: #c81623">
              <em style="font-size:20px;font-weight: 700;">¥{{o.sellPrice}}</em></el-col>
            <el-col :span="8">
              <em style="font-size:10px;color: rgb(173,173,173);float: right">{{o.soldNum}}人付款</em>
            </el-col>
          </el-row>
          <el-row>
            <router-link :to="{name:'Product',query:{pid:o.productId}}">
              <span style="float: left;font-size: 16px;color: #333333">{{ o.productName|cut}}</span>
            </router-link>
          </el-row>
          <el-row>
            <span style="float: left;font-size: 10px;color: rgb(153,153,153)">{{o.content | ellipsis}}</span>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[2, 5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
  </div>

</div>
</template>

<script>
import axios from "axios";
import {baseURL} from "../../public/urlConfig";
import Navi from "../components/Navi";
import router from "../router";

export default {
  name: "Search",
  components: {Navi},
  data(){
    return{
      cid:"",
      pageNum:1,
      pageSize:2,
      result:[],
      total:0,
      keyword:"",
      username:localStorage.getItem("username"),
      img:localStorage.getItem("img"),
      userId:localStorage.getItem("userId"),
      input:"",
      select:"",
    }
  },
  filters: {
    cut(value){
      if (!value) return "商品名为空";
      if (value.length > 10) {
        return value.slice(0, 10) + "...";
      }
      return value;
    },
    ellipsis(value) {
      if (!value) return "暂无说明";
      if (value.length > 13) {
        return value.slice(0, 13) + "...";
      }
      return value;
    }
  },
  created() {
    this.cid=this.$route.query.cid
    this.keyword=this.$route.query.keyword
    this.load()
  },
  methods:{
    load(){
      if(this.cid!=null){
        axios.get(baseURL+"/products/searchCate/"+this.cid,{
          params:{"pageNum":this.pageNum,"pageSize":this.pageSize}
        }).then(
            res=>{
              this.result=res.data.records
              this.total=res.data.total
            }
        )
      }
      if(this.keyword!=null){
        axios.get(baseURL+"/products/searchName",{
          params:{"keyword":this.keyword.toString(),
            "pageNum":this.pageNum,"pageSize":this.pageSize}
        }).then(
            res=>{
              this.result=res.data.records
              this.total=res.data.total
            }
        )
      }
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    logout() {
      localStorage.removeItem("username")
      localStorage.removeItem("img")
      localStorage.removeItem("token")
      localStorage.removeItem("userId")
      this.$message.success("退出成功")
      this.username=""
    },
    person(){
      this.username=localStorage.getItem("username")
      router.push("/person")
    },
    login(){
      router.push({
        path: '/login',
        query:{
          redirect: router.currentRoute.fullPath  // 防止从外部进来登录
        }
      })
    },
    fuzzyQuery(){
      axios.get(baseURL+"/products/searchName",{
        params:{"keyword":this.input,
          "pageNum":this.pageNum,"pageSize":this.pageSize}
      }).then(
          res=>{
            this.result=res.data.records
            this.total=res.data.total
          }
      )
    },
    search(v){
      axios.get(baseURL+"/products/searchName",{
        params:{"keyword":v,
          "pageNum":this.pageNum,"pageSize":this.pageSize}
      }).then(
          res=>{
            this.result=res.data.records
            this.total=res.data.total
          }
      )
    }
  }
}
</script>

<style scoped>

</style>
