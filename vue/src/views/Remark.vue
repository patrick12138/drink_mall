<template>
<div>
  <Navi/>
  <div>
    <el-row STYLE="width: 80%;margin: 0 auto">
      <el-col :span="8"style="padding: 50px">
        <el-row >
          <router-link :to="{name:'Product',query:{pid:productId}}">
            <img :src="'http://localhost:9090/img/'+productImg" style="width: 200px"></img></router-link>
        </el-row>
        <el-row>
          <el-link :href="'product?pid='+productId" :underline="false">
            <span>{{productName}}</span>
          </el-link>
        </el-row>
        <el-row>
          <span style="font-size: 10px;color: rgb(173,173,173)">{{content}}</span>
        </el-row>
      </el-col>
      <el-col :span="16" style="padding: 50px">
        <el-row>
          <el-rate
              v-model="value" show-text style="float: left"
              :colors="['#99A9BF', '#F7BA2A', '#FF9900']">1
          </el-rate>
        </el-row>
        <el-row>
          <el-input
              type="textarea"
              placeholder="亲，写点评价吧，你的评价对其他买家有很大帮助的。"
              v-model="textarea"
              maxlength="255"
              show-word-limit
          >
          </el-input>
        </el-row>
        <el-row>
          <el-upload
              :action="action+'/product-comments/upload'"
              list-type="picture-card" :limit="limit" :on-success="handleSuccess"
              :on-preview="handlePictureCardPreview" style="float: left">
            <i class="el-icon-plus"></i>
          </el-upload>
          <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
          </el-dialog>
        </el-row>
        <el-row>
          <template>
            <el-radio v-model="isAnonymous" label="0">公开</el-radio>
            <el-radio v-model="isAnonymous" label="1">匿名</el-radio>
            <el-button @click="comment">发表评论</el-button>
          </template>
        </el-row>
      </el-col>
    </el-row>
  </div>
</div>
</template>

<script>
import Navi from "../components/Navi";
import axios from "axios";
import {baseURL} from "../../public/urlConfig";
let a={}
export default {
  name: "Remark",
  components: {Navi},
  data(){
    return{
      action:"",
      productId:"",
      limit:5,
      isAnonymous:"0",
      dialogImageUrl: '',
      dialogVisible: false,
      userId:"",
      value:null,
      textarea:"",
      productName:"",
      content:"",
      productImg:"",
      commentImg:[],
    }
  },
  created() {
    this.action=baseURL
    this.productId=this.$route.query.pid;
    this.userId=localStorage.getItem("userId")
    this.load()
  },
  methods:{
    load(){
      axios.get(baseURL+"/products/product/"+this.productId).then(
          res=>{
            this.productName=res.data.data.product.productName
            this.content=res.data.data.product.content
            this.productImg=res.data.data.productImgs[0].url
          }
      )
    },
    handleSuccess(response,file, fileList) {
      this.commentImg.push(response)
      a={
        img:this.commentImg
      }
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    comment(){
      let comment={
          "commImgs":JSON.stringify(a),
        "commContent":this.textarea,
        "commLevel":this.value,
        "isAnonymous":this.isAnonymous,
        "productId":this.productId,
        "productName":this.productName,
        "userId":this.userId,
      }
      axios.post(baseURL+"/product-comments/makeComment",comment).then(
          res=>{
            if(res.data.code===10000)this.$message.success(res.data.msg)
            else this.$message.error(res.data.msg)
          }
      )
      axios.get(baseURL+"/order-item/updateComment",{
        params:{
          "itemId":localStorage.getItem("item_id"),
          "isComment":1
        },
        headers:{
          "token":localStorage.getItem("token")
        }
      }).then()
      localStorage.removeItem("item_id")
    }
  }
}
</script>

<style scoped>

</style>