<template>
  <div >
    <Navi/>
    <el-tabs v-model="activeName" tab-position="left" style="width: 80%;margin: 0 auto">
      <el-tab-pane label="个人资料" name="second"><el-card style="width: 80%;float: left">
        <el-form label-width="80px" size="small">
          <el-upload class="avatar-uploader" :action="baseURL+'/product-img/upload/'+username"
                     :show-file-list="false"
          >
            <img :src="'http://localhost:9090/img/'+form.userImg" style="width: 100px">
          </el-upload>
          <el-form-item label="用户名">
            <el-input v-model="form.username" disabled autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="用户ID">
            <el-input v-model="form.userId" disabled autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-input v-model="form.userSex" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="昵称">
            <el-input v-model="form.nickname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="真实姓名">
            <el-input v-model="form.realname" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="form.userEmail" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="form.userMobile" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="注册时间">
            <el-input  v-model="form.userRegtime" disabled autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="save">确 定</el-button>
          </el-form-item>
        </el-form>
      </el-card></el-tab-pane>
      <el-tab-pane label="收货地址" name="third">
        <Address :user-addr="userAddr" @itemclick="childclick"></Address>
        <el-row style="padding: 10px;" v-if="userAddr!=null">
          <el-table :data="userAddr">
            <el-table-column label="收货人" prop="receiverName">
            </el-table-column>
            <el-table-column label="手机号" prop="receiverMobile" width="120px"></el-table-column>
            <el-table-column label="省" prop="province"></el-table-column>
            <el-table-column label="市" prop="city"></el-table-column>
            <el-table-column label="区" prop="area"></el-table-column>
            <el-table-column label="地址" prop="addr"></el-table-column>
            <el-table-column label="邮编" prop="postCode"></el-table-column>
            <el-table-column label="操作" fixed="right">
              <template slot-scope="scope">
                <el-button v-if="scope.row.commonAddr===1" disabled type="text" size="mini">默认地址</el-button>
                <el-button v-else @click="update(scope.row.addrId)" type="text" size="mini">设为默认地址</el-button>
              </template>
            </el-table-column>
            <el-table-column fixed="right">
              <template slot-scope="scope">
                <el-button
                    size="mini" @click="deleteOne(scope.row.addrId)"
                    type="text" icon="el-icon-delete">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-row>
      </el-tab-pane>
<!--      <el-tab-pane label="我的评价" name="fourth"><CommentList></CommentList></el-tab-pane>-->
<!--      <el-tab-pane label="登录历史" name="fivth"><LoginHistory></LoginHistory></el-tab-pane>-->
    </el-tabs>
  </div>

</template>

<script>
import {baseURL} from "../../public/urlConfig";
import request from "@/utils/request";
import Navi from "../components/Navi";
import CommentList from "../components/CommentList";
import Address from "../components/Address";
import axios from "axios";
import LoginHistory from "../components/LoginHistory";

export default {
  name: "Person",
  components: {LoginHistory, Address, CommentList, Navi},
  data() {
    return {
      baseURL: baseURL,
      msg:"",
      form:{},
      img: localStorage.getItem("img") ,
      username: localStorage.getItem("username"),
      activeName: 'second',
      userAddr:[],
      userId:localStorage.getItem("userId"),
    }
  },
  created() {
    // 请求分页查询数据
    this.load()
  },
  methods: {
    load() {
      axios.get(baseURL+"/user/one/"+localStorage.getItem("username")
      ).then(res => {
        this.form=res.data
      })
      axios.get(baseURL+"/user-addr/addr/"+this.userId).then(
          res=>{this.userAddr=res.data})
    },
    childclick(item){
      this.userAddr=item
      this.load()
    },
    save() {
      axios.post(baseURL+"/user", this.form).then(res => {
        if (res.data) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    deleteOne(id){
        axios.delete(baseURL+"/user-addr/deleteOne",{
          params:{
            id:id
          }
        }).then(
            res=>{
              if(res.data===true)this.$message.success("删除成功")
              else this.$message.error("删除失败")
              this.load()
            }
        )
    },
    update(id){
      axios.get(baseURL+"/user-addr/updateCommonAddr",{
        params:{
          id:id
        }
      }).then(
          res=>{
            if(res.data===true)this.$message.success("修改成功")
            else this.$message.error("修改失败")
            this.load()
          }
      )
    }
  }
}
</script>

<style>
.avatar-uploader {
  text-align: center;
  padding-bottom: 10px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 138px;
  height: 138px;
  line-height: 138px;
  text-align: center;
}
.avatar {
  width: 138px;
  height: 138px;
  display: block;
}
</style>
