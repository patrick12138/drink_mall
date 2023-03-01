<template>
  <div style="width: 80%;margin: 0 auto;background-color: white">
    <el-row style="background-color: white;">
      <div style="float: left;margin: 5px">
        <h2  >分类推荐</h2>
      </div>
    </el-row>
    <el-container v-for="cate in categories">
      <el-header>
        <h3 style="float: left">
          <i><img :src="'http://localhost:9090/img/'+cate.categoryIcon"
                  style="vertical-align:middle; border-radius: 50%; position: relative; "></i>
          <a style="">{{cate.categoryName}}</a>
        </h3>
        <span style="float: right;color: rgb(153,153,153)">{{cate.categorySlogan}}</span>
      </el-header>
      <el-container>
        <el-col :span="4">
          <el-image :src="'http://localhost:9090/img/'+cate.categoryPic" style="margin: 0 auto"></el-image>
        </el-col>
        <el-col v-if="cate.products.length===0">
          <el-empty :image-size="100"></el-empty>
        </el-col >
          <el-col v-else :span="6" v-for="o in cate.products">
            <el-card style="margin: 5px" shadow="hover">
              <router-link :to="{name:'Product',query:{pid:o.productId}}">
                <el-row >
                  <img :src="'http://localhost:9090/img/'+o.url" style="width: 100%;height: 100%;display: block">
                </el-row>
                <el-row>
                  <span style="font-size: 16px;float: left;color:#333333">{{o.productName | ellipsis}}</span>
                </el-row>
              </router-link>
              <el-row>
                <span style="font-size: 10px;float: left;color: rgb(153,153,153)">总销量：{{o.soldNum}}</span>
              </el-row>
               </el-card>
          </el-col>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import axios from "axios";
import {baseURL} from "../../public/urlConfig";

export default {
  name: "CategoryRecommend",
  data(){
    return{
      categories:{}
    }
  },
  created() {
    this.load()
  },
  filters: {
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
      axios.get(baseURL+"/products/CategoryRecommend").then(
          res=>{
            this.categories=res.data
          }
      )
    }
  }
}
</script>

<style scoped>
</style>