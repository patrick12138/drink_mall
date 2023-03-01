<template>
<div >
  <Navi/>
  <div style="margin: 0 auto;width: 80%" v-for="item in orderInfo">
    <el-row style="background-color:rgb(234,248,255);width: 80%;margin: 0 auto;padding: 10px">
      <b style="float: left;margin-left: 5px">{{item.createTime.substring(0,19)}}</b>
      <span style="float: left;margin-left: 5px">订单号：<em>{{item.orderId}}</em></span>
    </el-row>
    <el-row style="width: 80%;margin: 0 auto;background-color: white">
      <el-col :span="16" style="float: left;">
        <el-table
            v-loading="loading"
            element-loading-text="拼命加载中"
            element-loading-spinner="el-icon-loading"
            element-loading-background="rgba(0, 0, 0, 0.8)"
            :data="item.orderItems"
            style="margin: 0 auto;">
          <el-table-column label="商品信息">
            <template slot-scope="scope">
              <el-row>
                <el-col :span="4">
                  <router-link :to="{name:'Product',query:{pid:scope.row.productId}}">
                    <el-image :src="'http://localhost:9090/img/'+scope.row.productImg" fit="fill" style="width:100%"></el-image>
                  </router-link>
                </el-col>
                <el-col :span="10" style="margin: 5px">
                  <el-row>
                    <span>{{scope.row.productName}}</span></el-row>
                    <el-row><span style="font-size: 12px;color: rgb(176,176,176)">{{scope.row.skuName}}</span>
                  </el-row>
                </el-col>
                <el-col :span="2">
                  <span style="margin: 0 auto">¥{{scope.row.productPrice}}</span>
                </el-col>
                <el-col :span="2">
                  <span>{{scope.row.buyCounts}}</span>
                </el-col>
                <el-col :span="4">
                  <el-link v-if="scope.row.isComment===1" :href="'/product?pid='+scope.row.productId" :underline="false">再次购买</el-link>
<!--                  <el-link v-else :underline="false" @click="sava(scope.row.itemId,scope.row.productId)">立即评价</el-link>-->
                </el-col>
              </el-row>

            </template>
          </el-table-column>
        </el-table>
      </el-col>
      <el-col :span="4" style="">
        <b style="padding: 10px;font-size: 16px;margin: 0 auto">总计：¥{{item.actualAmount}}</b>
      </el-col>
      <el-col :span="4">
        <el-row>
          <el-link :underline="false" :href="'/orderDetail?orderId='+item.orderId">订单详情</el-link>
        </el-row>
        <el-row>
          <span v-if="item.status==1" style="color: rgb(255,66,0)">已创建</span>
          <span v-if="item.status==2" style="color: rgb(255,66,0)">待支付</span>
          <span v-if="item.status==4" style="color: rgb(0,186,151)">已付款</span>
        </el-row>

      </el-col>
    </el-row>
  </div>
</div>
</template>

<script>
import axios from "axios";
import {baseURL} from "../../public/urlConfig";
import Navi from "../components/Navi";
import router from "../router";

export default {
  name: "orderList",
  components: {Navi},
  data(){
    return{
      userId:"",
      orderInfo:[],
    }
  },
  created() {
    this.userId=this.$route.query.uid;
    this.load();
  },
  methods:{
    load(){
      axios.get(baseURL+"/order-item/getOrderListByUserId/"+this.userId,{
        headers:{token:localStorage.getItem("token")}
      }).then(
          res=>{
            if(res.data.code===10001)
              this.$message.error(res.data.msg)
            else {
              this.orderInfo=res.data.data
            }
          }
      )
    },
    sava(id,pid){
      localStorage.setItem("item_id",id)
      router.push({
        path: '/remark',
        query:{
          pid: pid  // 防止从外部进来登录
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
