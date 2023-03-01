<template>
  <el-carousel :interval="3000">
    <el-carousel-item v-for="item in imgs">
      <img :src="'http://localhost:9090/img/'+item.imgUrl" style="width: 100%;height: 100%">
    </el-carousel-item>
  </el-carousel>
</template>

<script>
import axios from "axios";
import {baseURL} from "../../public/urlConfig";

export default {
  name: "Banner",
  data(){
    return{
      imgs:{}
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      axios.get(baseURL+'/index-img/img').then(
          res=>{
            this.imgs=res.data.data
          }
      )
    }
  }
}
</script>

<style scoped>
/*.list-container {
  margin: 0 auto;

  .sortList {
    height: 350px;
    padding-left: 210px;

    .center {
      box-sizing: border-box;
      width: 740px;
      height: 100%;
      padding: 5px;
      float: left;
    }
  }
}*/
</style>
