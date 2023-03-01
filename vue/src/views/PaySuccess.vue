<template>
  <div>
    <Navi/>
    <div style="width: 80%;margin: 0 auto">
      <el-row style="background-color: rgb(236,255,220)">
        <div style="float: left;margin: 10px">
          <img src="../img/orderSuccess.png"/>
          <span style="font-size: 16px;margin: 5px">您已成功付款</span>
        </div>

      </el-row>
      <el-row style="padding: 20px;background-color: white;width: 100%">
        <li style="float: left">收货地址：{{this.orderInfo.receiverAddress}} {{this.orderInfo.receiverName}} {{this.orderInfo.receiverMobile}}</li>
        <li>实付款：<em style=" text-align: left;color: #c81623;font-size: 20px">¥{{this.orderInfo.actualAmount}}</em></li>
      </el-row>
      <el-row style="padding: 20px;background-color: white;width: 100%">
       <div style="float: left">
         您可以：<router-link :to="{name:'orderList',query:{uid:userId}}" style="color: #ff5000;margin-right: 10px">查看已买到的宝贝</router-link>
         <router-link :to="{name:'orderDetail',query:{orderId:orderId}}" style="color: rgb(0,148,74)">查看订单详情</router-link>
       </div>
      </el-row>
    </div>
  </div>
</template>

<script>
import Navi from "../components/Navi";
import axios from "axios";
import {baseURL} from "../../public/urlConfig";
export default {
  name: "PaySuccess",
  components: {Navi},
  data(){
    return{
      orderId:"",
      orderInfo:"",
      userId:localStorage.getItem("userId")
    }
  },
  created() {
    this.orderId=this.$route.query.orderId;
    this.load()
  },
  methods:{
    load(){
      axios.get(baseURL+"/orders/getOrder",{
        params:{orderId:this.orderId},
        headers:{token:localStorage.getItem("token")}
      }).then(
          res=>{
            this.orderInfo=res.data.data
          }
      )
    }
  }
}
</script>

<style scoped>

</style>