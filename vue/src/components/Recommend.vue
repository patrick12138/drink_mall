<template>
  <el-row style="width: 80%;margin: 0 auto;background-color: white">
    <el-row style="">
      <div style="float: left;margin: 5px">
        <h2  >新品推荐</h2>
      </div>
    </el-row>
    <el-col :span="6" v-for="o in products" >
      <el-card style="border-radius: 15px;margin: 5px" shadow="hover">
        <el-row>
          <router-link :to="{name:'Product',query:{pid:o.productId}}">
            <img :src="'http://localhost:9090/img/'+o.url" style="  width: 100%;height: 100%;display: block;">
          </router-link>
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
</template>

<script>
import axios from "axios";
import {baseURL} from "../../public/urlConfig";

export default {
  name: "Recommend",
  data(){
    return{
      products:[]
    }
  },
  created() {
    this.load()
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
  methods:{
    load(){
      axios.get(baseURL+"/products/recommend").then(
          res=>{
            this.products=res.data
          }
      )
    }
  }
}
</script>

<style>
</style>
