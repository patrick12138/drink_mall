<template>
<div>
  <el-row>
    <el-descriptions>
      <el-descriptions-item label="产地">{{params.productPlace}}</el-descriptions-item>
<!--      <el-descriptions-item label="有效期">{{params.footPeriod}}</el-descriptions-item>-->
      <el-descriptions-item label="品牌">{{params.brand}}</el-descriptions-item>
<!--      <el-descriptions-item label="生成厂家">{{params.factoryName}}</el-descriptions-item>-->
<!--      <el-descriptions-item label="厂家地址">{{params.factoryAddress}}</el-descriptions-item>-->
      <el-descriptions-item label="包装方式">{{params.packagingMethod}}</el-descriptions-item>
      <el-descriptions-item label="净含量">{{params.weight}}</el-descriptions-item>
      <el-descriptions-item label="储存方式">{{params.storageMethod}}</el-descriptions-item>
      <el-descriptions-item label="食用方式">{{params.eatMethod}}</el-descriptions-item>
    </el-descriptions>
  </el-row>
  <el-divider></el-divider>
  <el-row>
    <div v-for="item in productImgs">
      <el-image :src="'http://localhost:9090/img/'+item.url" style="width: 100%;height: 100%;margin-top: 20px"></el-image>
    </div>
  </el-row>
</div>
</template>

<script>
import axios from "axios";
import {baseURL} from "../../public/urlConfig";

export default {
  name: "ProductParams",
  props:['productId','productImgs'],
  data(){
    return{
      params:"",
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      axios.get(baseURL+"/product-params/params/"+this.productId).then(
          res=>{
            this.params=res.data
          }
      );
    }
  }
}
</script>

<style scoped>

</style>
