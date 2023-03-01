<template>
<div>
  <Navi/>
  <div style="width: 80%;margin: 0 auto;background-color: rgb(247,247,247)">
    <el-timeline style="margin: 10px">
      <el-timeline-item placement="top" v-for="o in result" :timestamp=o.time style="width: 40%;margin: 10px" :color="'#0bbd87'">
        <el-card style="border-radius: 15px;margin: 5px" shadow="hover">
          <el-row>
            <router-link :to="{name:'Product',query:{pid:o.productId}}">
              <img :src="'http://localhost:9090/img/'+o.url" style="  width: 100%;height: 100%;display: block;">
            </router-link>
          </el-row>
          <el-row>
<!--            <el-col :span="16" style=" text-align: left;color: #c81623">
              <em style="font-size:20px;font-weight: 700;">¥{{o.sellPrice}}</em></el-col>-->
            <el-col :span="8">
              <em style="font-size:10px;color: rgb(173,173,173);float: right">{{o.soldNum}}人付款</em>
            </el-col>
          </el-row>
          <el-row>
            <router-link :to="{name:'Product',query:{pid:o.productId}}">
              <span style="float: left;font-size: 16px;color: #333333">{{ o.productName|cut}}</span>
            </router-link>
          </el-row>
          <el-row>
            <span style="float: left;font-size: 10px;color: rgb(153,153,153)">{{o.content | ellipsis}}</span>
          </el-row>
        </el-card>
      </el-timeline-item>
    </el-timeline>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[2, 5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
  </div>
</div>
</template>

<script>
import axios from "axios";
import {baseURL} from "../../public/urlConfig";
import Navi from "../components/Navi";

export default {
  name: "History",
  components: {Navi},
  data(){
    return{
      pageNum:1,
      pageSize:2,
      result:[],
      total:0,
    }
  },
  created() {
    this.load()
  },
  filters: {
    cut(value){
      if (!value) return "商品名为空";
      if (value.length > 10) {
        return value.slice(0, 10) + "...";
      }
      return value;
    },
    ellipsis(value) {
      if (!value) return "暂无说明";
      if (value.length > 13) {
        return value.slice(0, 13) + "...";
      }
      return value;
    }
  },
  methods:{
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    load(){
      axios.get(baseURL+"/history/getByUserId",{
        params:{"userId":localStorage.getItem("userId"),
          "pageNum":this.pageNum,"pageSize":this.pageSize}
      }).then(
          res=>{
            this.result=res.data.records
            this.total=res.data.total
          }
      )
    }
  }

}
</script>

<style scoped>

</style>