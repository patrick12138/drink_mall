<template>
<div style="overflow-x: hidden">
  <Navi/>
  <el-row :gutter="20" style="background-color: white;width: 80%;margin: 0 auto">
    <el-col :span="8">
      <el-col>
        <el-image :src="'http://localhost:9090/img/'+productImgs[currentImgIndex].url" fit="fill"
                  :preview-src-list="['http://localhost:9090/img/'+productImgs[currentImgIndex].url]"
        style="height: 210px"></el-image>
      </el-col>
      <el-col>
        <el-carousel :autoplay="false" type="card" height="100px" @change="changeImg">
          <el-carousel-item  v-for="item in productImgs" >
            <el-image :src="'http://localhost:9090/img/'+item.url" style="height: 100%;"></el-image>
          </el-carousel-item>
        </el-carousel>
      </el-col>
    </el-col>
    <el-col :span="16">
        <el-col>
          <h3 style="float: left;margin: 5px">{{productInfo.productName}}</h3>
        </el-col>
        <el-col style="background-color:#fee9eb">
          <el-col style="margin: 5px">
            <el-col :span="3"style="text-align: left;font-size: 16px">原价：</el-col>
            <el-col :span="16" style=" text-align: left">
              <em style="font-size: 16px;text-decoration: line-through">¥{{productSku[currentSkuIndex].originalPrice}}</em></el-col>
          </el-col>
          <el-col style="margin: 5px">
            <el-col :span="3"style="text-align: left;font-size: 16px">促销价：</el-col>
            <el-col :span="16" style=" text-align: left;color: #c81623">
              <em style="font-size:30px;font-weight: 700;">¥{{productSku[currentSkuIndex].sellPrice}}</em></el-col>
          </el-col>
        </el-col>
<!--        <el-col >-->
<!--          <span style="margin: 5px;float: left" >套餐：</span>-->
<!--          <div style="margin: 5px;float: left" v-for="(sku,index) in productSku" >-->
<!--            <el-radio-group v-model="tabPosition" size="medium"  @change="changeSku">-->
<!--              <el-radio-button :label="index" >{{sku.skuName}}</el-radio-button>-->
<!--            </el-radio-group>-->
<!--          </div>-->
<!--        </el-col>-->
        <el-col v-for="(value,key,index) in skuProps" >
          <span style="margin: 5px;float: left" >{{ key }}：</span>
          <div style="margin: 5px;float: left" v-for="(sku) in value">
            <el-radio-group v-model="tab[index].value" :key="key" size="medium" @change="(skus)=>changeKey(skus,key)" >
              <el-radio-button :label="sku" >{{sku}}</el-radio-button>
            </el-radio-group>
          </div>
        </el-col>
        <el-col>
          <el-descriptions size="medium" style="text-align: left;font-size: 16px;margin: 5px">
            <el-descriptions-item label="库存">{{ productSku[currentSkuIndex].stock }}</el-descriptions-item>
            <el-descriptions-item label="累计销量">{{productInfo.soldNum}}件</el-descriptions-item>
          </el-descriptions>
        </el-col>
        <el-col>
          <el-input v-model="num"  :min="1" :max="productSku[currentSkuIndex].stock"
          style="float: left;margin: 5px;width: 100px"></el-input>
          <el-button type="warning" round plain style="float:left;margin: 5px;" @click="addList">添加购物车</el-button>
        </el-col>
    </el-col>
  </el-row>
  <el-tabs type="border-card" style="margin: 0 auto;width: 80%">
    <el-tab-pane label="宝贝详情"><ProductParams :productId="productId" :productImgs="productImgs"></ProductParams></el-tab-pane>

  </el-tabs>
</div>
</template>

<script>
import axios from "axios";
import {baseURL} from "../../public/urlConfig";
import router from "@/router";
import Navi from "../components/Navi";
import ProductParams from "../components/ProductParams";
import Comments from "../components/Comments";
let skuMaps=new Map()
export default {
  name: "Product",
  components: {ProductParams, Navi,Comments},
  data(){
    return{
      productId:"",
      num:1,
      currentSkuIndex:0,
      currentImgIndex:0,
      tab: [],
      tabPosition:"0",
      productInfo:{},
      productSku:[],
      productImgs:[],
      skuProps:[],
    }
  },
  created() {
    //获取到url传递的商品id
    this.productId=this.$route.query.pid;
    this.load();
    this.addHistory();
    var len = 3;
    for (var i = 0; i < len; i++) {
      var item = {value: "0"};
      this.tab.push(item);
    }
  },
  methods:{
    load(){
      axios.get(baseURL+"/products/product/"+this.productId).then(
          res=>{
            this.productInfo=res.data.data.product;
            this.productSku=res.data.data.productSku;
            this.productImgs=res.data.data.productImgs;
            //eval将json格式转化为Map键值对格式
            this.skuProps=eval("("+this.productSku[this.currentSkuIndex].untitled+")");
          }
      )
    },
    recur(data){
      for(let i=0;i<data.length;i++){
        for (let j=0;j<data[i].districts.length;j++){
          for (let k=0;k<data[i].districts[j].districts.length;k++){
            data[i].districts[j].districts[k].districts=undefined
          }

        }
      }
      return data
    },
    changeSku(val){
        this.currentSkuIndex=val
      //eval将json格式转化为Map键值对格式
      this.skuProps=eval("("+this.productSku[this.currentSkuIndex].untitled+")");
    },
    changeImg(val){
      this.currentImgIndex=val
    },
    changeKey(val,e){
      skuMaps.set(e,val)
    },
    addList(){
      var a=""
      for (let keys of skuMaps.keys()){
        a+=keys+":"+skuMaps.get(keys)+";";
      }
      var carts={
        "cartNum":this.num,
        "productId":this.productId,
        "productPrice":this.productSku[this.currentSkuIndex].sellPrice,
        "skuId":this.productSku[this.currentSkuIndex].skuId,
        "skuProps":a,
        "userId":localStorage.getItem("userId"),
      }
      var token=localStorage.getItem("token")
      axios.post(baseURL+"/shopping-cart/add",carts,{headers:{
          token:token}}).then(res=>{
            if (res.data.code==10000){
              this.$message.success(res.data.msg)
            }
            if(res.data.code==10001){
              this.$message.error(res.data.msg)
              router.push({
                path: '/login',
                query:{
                  redirect: router.currentRoute.fullPath  // 防止从外部进来登录
                }
              })
            }
          })
    },
    submit(){
      var a=""
      for (let keys of skuMaps.keys()){
        a+=keys+":"+skuMaps.get(keys)+";";
      }
      var carts={
        "cartNum":this.num,
        "productId":this.productId,
        "productPrice":this.productSku[this.currentSkuIndex].sellPrice,
        "skuId":this.productSku[this.currentSkuIndex].skuId,
        "skuProps":a,
        "userId":localStorage.getItem("userId"),
      }
      var token=localStorage.getItem("token")
      axios.post(baseURL+"/shopping-cart/add",carts,{headers:{
          token:token}}).then(res=>{
            if (res.data.code==10000){
              this.$router.push("/order?cids="+res.data.data)
            }
            if(res.data.code==10001) {
              this.$message.error(res.data.msg)
              router.push({
                path: '/login',
                query: {
                  redirect: router.currentRoute.fullPath  // 防止从外部进来登录
                }
              })
            }
      })
    },
    addHistory(){
      var history={
        "productId":this.productId,
        "userId":localStorage.getItem("userId"),
      }
      axios.post(baseURL+"/history/insert",history)
    },
  }

}
</script>

<style scoped>

</style>
