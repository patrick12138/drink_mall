<template>
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
                <el-link :underline="false" href="/person" icon="el-icon-user-solid">{{ username }}</el-link> <i class="el-icon-arrow-down el-icon--right"></i>
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
          <div style="float: right;">
            <el-link :underline="false" href="/">商城首页</el-link>
            <el-divider direction="vertical"></el-divider>
            <el-link :underline="false" href="/login">我的订单</el-link>
            <el-divider direction="vertical"></el-divider>
            <el-link :underline="false" :href="'/shopcart?uid='+userId" icon="el-icon-shopping-cart-2">我的购物车</el-link>
            <el-divider direction="vertical"></el-divider>
            <el-link :underline="false" href="/addProduct">商家后台</el-link>
            <el-divider direction="vertical"></el-divider>
            <el-link :underline="false" href="/history">我的足迹</el-link>
          </div>
        </el-col>
      </el-row>
    </div>
    <el-row style="width: 80%;margin: 0 auto;top: 10px">
      <el-col :span="8">
        <el-link href="/" style="float: left" :underline="false">
          <img src="../assets/logo.png"  width="130px" >
        </el-link>
      </el-col>
      <el-col :span="16">
        <el-steps :active="1" align-center style="">
          <el-step title="步骤1" description="购物车结算"></el-step>
          <el-step title="步骤2" description="核对订单信息"></el-step>
          <el-step title="步骤3" description="成功提交订单"></el-step>
        </el-steps>
      </el-col>
    </el-row>
    <el-row style="width: 80%;margin: 0 auto;padding-top: 10px"><h4 style="float: left">选择收货地址</h4></el-row>
    <el-row style="width: 80%;margin: 0 auto;padding: 10px" v-if="userAddr!==undefined&&userAddr.length>0">
      <el-radio-group style="float: left;margin: 5px;" v-model="addrIndex" v-for="(item,ind) in userAddr">
        <el-radio-button  style="margin: 5px" :label="ind">
          <el-descriptions   border>
            <el-descriptions-item label="收货人">{{item.receiverName}}</el-descriptions-item>
            <el-descriptions-item label="手机号">{{item.receiverMobile}}</el-descriptions-item>
            <el-descriptions-item label="省市">{{item.province}} {{item.city}}</el-descriptions-item>
            <el-descriptions-item label="地址">{{item.area}}{{item.addr}}</el-descriptions-item>
            <el-descriptions-item label="邮编">{{item.postCode}}</el-descriptions-item>
          </el-descriptions>
        </el-radio-button>
      </el-radio-group>

    </el-row>
    <el-row style="width: 80%;margin: 0 auto;padding: 10px">
      <el-button type="text" @click="dialogFormVisible = true" style="float: left">添加新地址</el-button>
    </el-row>
    <el-dialog title="收货地址" :visible.sync="dialogFormVisible">
      <Address :user-addr="userAddr" @itemclick="childclick"></Address>
    </el-dialog>
    <el-row style="width: 80%;margin: 0 auto;padding: 10px"><h4 style="float: left">确认订单信息</h4></el-row>
    <el-row>
      <el-table
          v-loading="loading"
          element-loading-text="拼命加载中"
          element-loading-spinner="el-icon-loading"
          element-loading-background="rgba(0, 0, 0, 0.8)"
          :data="productList"
          style="width: 80%;margin: 0 auto;">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form inline style="font-size:0;margin-left: 5px;margin-bottom: 0;color:rgb(102,120,180)" >
              <el-form-item label="商品ID：">
                <span>{{ props.row.productId }}</span>
              </el-form-item>
              <el-form-item label="商品名称：">
                <span>{{ props.row.productName }}</span>
              </el-form-item>
              <el-form-item label="套餐名：">
                <span>{{ props.row.skuName }}</span>
              </el-form-item>
              <el-form-item label="套餐属性：">
                <span>{{ props.row.skuProps }}</span>
              </el-form-item>
              <el-form-item label="原价：">
                <span>¥{{ props.row.originalPrice}}</span>
              </el-form-item>
              <el-form-item label="创建时间：">
                <span>{{ props.row.cartTime }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column label="图片">
          <template slot-scope="scope">
            <router-link :to="{name:'Product',query:{pid:scope.row.productId}}">
              <el-image :src="'http://localhost:9090/img/'+scope.row.url" fit="fill" style="width: 40px"></el-image>
            </router-link>
          </template>
        </el-table-column>
        <el-table-column
            label="商品名称"
            prop="productName">
        </el-table-column>
        <el-table-column
            label="商品属性"
            prop="skuProps">
        </el-table-column>
        <el-table-column
            label="单价"
            prop="sellPrice">
        </el-table-column>
        <el-table-column
            label="数量"
            prop="cartNum">
        </el-table-column>
        <el-table-column label="总计" width="100">
          <template slot-scope="scope">
            <em style="font-size:16px;font-weight: 700; color: #c81623">¥{{scope.row.sellPrice*scope.row.cartNum}}</em>
          </template>
        </el-table-column>
      </el-table>
    </el-row>
    <el-row style="width: 80%;margin: 0 auto;padding-top: 10px">
      <el-card shadow="hover" body-style="border-radius: 2px" style="width: 450px;height: 130px;float: right" v-if="userAddr!==undefined&&userAddr.length>0">
        <el-row style="float: right;width: 450px">
          <div style="float: right">
            <b>实付款：</b>
            <em style="font-size:35px;font-weight: 700; color: #c81623">¥{{sum}}</em>
          </div>
        </el-row >
        <el-row style="float: right;width: 450px">
          <div style="float: right">
            <b>寄送至：</b>
            <em style="font-weight: 700; ">
              {{
                userAddr[addrIndex].province + "省" + userAddr[addrIndex].city + "市"
                + userAddr[addrIndex].area + " " + userAddr[addrIndex].addr
              }}
            </em>
          </div>
        </el-row>
        <el-row style="float: right;width: 450px">
          <div style="float: right">
            <b>收件人：</b>
            <em style="font-weight: 700; ">
              {{userAddr[addrIndex].receiverName + " "+userAddr[addrIndex].receiverMobile}}
            </em>
          </div>
        </el-row>
      </el-card>
    </el-row>
    <el-row style="width: 80%;margin: 0 auto;padding-top: 10px">
      <el-button style="background-color: rgb(255,0,54);color: white;float: right" @click="addOrder">提交订单</el-button>
    </el-row>

  </div>
</template>

<script>
import axios from "axios";
import {baseURL} from "../../public/urlConfig";
import router from "../router";
import Address from "../components/Address";
let token=localStorage.getItem("token")
export default {
  name: "Order",
  components: {Address},
  data(){
    return{
      username:localStorage.getItem("username"),
      img:localStorage.getItem("img"),
      userId:localStorage.getItem("userId"),
      cids:[],
      userAddr:[],
      productList:[],
      sum:0,
      addrIndex:0,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      form: {
        userId: localStorage.getItem("userId"),
        receiverName: '',
        receiverMobile: '',
        province: '',
        city:"",
        area: "",
        addr: '',
        postCode: '',
        status:1,
        commonAddr:0,
      },
      orderid:"",
    }
  },
  created() {
    this.cids=this.$route.query.cids
    this.load()
  },
  methods:{
    load(){
      axios.get(baseURL+"/user-addr/addr/"+this.userId).then(
          res=>{this.userAddr=res.data
            for(var i=0; i<this.userAddr.length; i++){
              if(this.userAddr[i].commonAddr == 1){
                this.addrIndex = i;
              }
            }
          }
      )
      let s=""
      axios.post(baseURL+"/shopping-cart/listByCartId",s= this.cids.split(","),{
        headers:{token:token}}).then(
          res=>{this.productList=res.data
            let a=this.productList
            for(let i=0;i<a.length;i++){
              this.sum+=a[i].sellPrice*a[i].cartNum
            }
            }
      )
    },
    childclick(item){
      this.userAddr=item
      this.load()
      this.dialogFormVisible=false
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
    addOrder(){
      var order = {
        "actualAmount": this.sum,
        "orderRemark": this.orderRemark,
        "receiverAddress":  this.userAddr[this.addrIndex].province + " " +this.userAddr[this.addrIndex].city+" "
            +this.userAddr[this.addrIndex].area+" " +this.userAddr[this.addrIndex].addr,
        "receiverMobile": this.userAddr[this.addrIndex].receiverMobile,
        "receiverName": this.userAddr[this.addrIndex].receiverName,
        "totalAmount": this.sum,
        "userId": this.userId
      };
      axios.post(baseURL+"/orders/add",order,{
        headers:{
          token:localStorage.getItem("token")
        },
        params:{
          cartId: this.cids
        },}).then((res)=>{
          document.write(res.data)
/*          this.$router.push("/paySuccess?orderId="+res.data.data)*/
      });
    },
  }
}
</script>

<style scoped>
</style>
