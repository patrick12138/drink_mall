<template>
<div style="width: 80%;float: left;background-color: rgb(247,247,247)">
  <div class="radio">
    排序：
    <el-radio-group v-model="reverse">
      <el-radio :label="true">倒序</el-radio>
      <el-radio :label="false">正序</el-radio>
    </el-radio-group>
  </div>
  <el-timeline :reverse="reverse" >
    <el-timeline-item
        v-for="(activity, index) in activities"
        :key="index" style="float: left;width: 50%" :color="'#0bbd87'"
        :timestamp="activity.loginTime">
      {{activity.country}}{{activity.area}}登录，IP：{{activity.ip}}
    </el-timeline-item>
  </el-timeline>
</div>
</template>

<script>
import {baseURL} from "../../public/urlConfig";
import axios from "axios";

export default {
  name: "LoginHistory",
  data(){
    return{
      ip:"",
      reverse: true,
      activities:[
      ],
    }
  },
  created() {
    this.getIp()
  },
  methods:{
    getIp(){
      axios.get(baseURL+"/user-login-history/getById",{
        params:{
          id:localStorage.getItem("userId")
        }
      }).then(
          res=>{
            this.activities=res.data
          }
      )
    }
  }
}
</script>

<style scoped>

</style>