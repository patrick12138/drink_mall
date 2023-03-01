<template>
  <div >
    <Navi/>
    <div style="width: 80%;margin: 0 auto">
      <el-breadcrumb separator=">">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a :href="'/orderList?uid='+userId">我的订单</a></el-breadcrumb-item>
        <el-breadcrumb-item>订单详情</el-breadcrumb-item>
      </el-breadcrumb>
      <el-row style="margin: 10px">
        <el-steps :active="3" align-center style="">
          <el-step title="步骤1" description="购物车结算"></el-step>
          <el-step title="步骤2" description="核对订单信息"></el-step>
          <el-step title="步骤3" description="成功提交订单"></el-step>
        </el-steps>
      </el-row>
      <el-row style="width: 80%;margin: 0 auto">
        <em style="float: left">订单信息：</em>
      </el-row>
      <el-row style="width: 80%;margin:0 auto;padding: 10px">
        <el-row style="float: left">
          <span style="color: rgb(144,147,153)">订单编号：</span>
          <span>{{orderInfo[0].orderId}}</span>
        </el-row>
        <el-row style="float: left">
          <span style="color: rgb(144,147,153)">加入购物车时间：</span>
          <span>{{orderInfo[0].basketDate.substring(0,19)}}</span>
        </el-row>
          <el-row style="float: left">
          <span style="color: rgb(144,147,153)">成交时间：</span>
          <span>{{orderInfo[0].buyTime.substring(0,19)}}</span>
          </el-row>
      </el-row>
      <el-row style="padding: 10px">
        <el-table
            v-loading="loading"
            element-loading-text="拼命加载中"
            element-loading-spinner="el-icon-loading"
            element-loading-background="rgba(0, 0, 0, 0.8)"
            :data="orderInfo"
            style="width: 80%;margin: 0 auto;">
          <el-table-column label="图片">
            <template slot-scope="scope">
              <router-link :to="{name:'Product',query:{pid:scope.row.productId}}">
                <el-image :src="'http://localhost:9090/img/'+scope.row.productImg" fit="fill" style="width: 40px"></el-image>
              </router-link>
            </template>
          </el-table-column>
          <el-table-column
              label="商品名称"
              prop="productName">
          </el-table-column>
          <el-table-column
              label="商品属性"
              prop="skuName">
          </el-table-column>
          <el-table-column
              label="单价"
              prop="productPrice">
          </el-table-column>
          <el-table-column
              label="数量"
              prop="buyCounts">
          </el-table-column>
          <el-table-column label="总计" width="100"
          prop="totalAmount">
          </el-table-column>
        </el-table>
      </el-row>
        <el-row style="width: 80%;margin: 0 auto">
          <el-col :span="20">
            <span style="float:right">商品总价：</span> </el-col>
          <el-col :span="4">¥{{order.totalAmount}}</el-col>
        </el-row>
      <el-row style="width: 80%;margin: 0 auto">
        <el-col :span="20">
          <span style="float:right">运费：</span> </el-col>
        <el-col :span="4">¥{{order.orderFreight}}</el-col>
      </el-row>
      <el-row style="width: 80%;margin: 0 auto">
        <el-col :span="20"  v-if="order.status==1">
          <span style="float:right">待付款：</span>
        </el-col>
        <el-col :span="20"  v-if="order.status==4">
          <span style="float:right">实付款：</span>
        </el-col>
        <el-col :span="4">
          <span style="font-size: 24px;color: #c81623;font-weight: bold">¥{{order.actualAmount}}</span> </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {baseURL} from "../../public/urlConfig";
import Navi from "../components/Navi";

export default {
  name: "orderDetail",
  components: {Navi},
  data(){
    return{
      orderId:"",
      orderInfo:[],
      order:"",
      userId:localStorage.getItem("userId")
    }
  },
  created() {
    this.orderId=this.$route.query.orderId;
    this.load()
  },
  methods:{
    load(){
      axios.get(baseURL+"/order-item/getOrderDetail",{
        params:{orderId:this.orderId},
        headers:{token:localStorage.getItem("token")}
      }).then(
          res=>{
            this.orderInfo=res.data.data
          }
      );
      axios.get(baseURL+"/orders/getOrder",{
        params:{orderId:this.orderId},
        headers:{token:localStorage.getItem("token")}
      }).then(
          res=>{
            this.order=res.data.data
          }
      )
    }
  }
}
</script>

<style scoped>

</style>