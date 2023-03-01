<template>
<div>
  <el-row>
    <el-col>
      <span style="float: left;margin: 5px">收货人名：</span>
      <el-input v-model="name" placeholder="请输入内容" style="float: left;margin: 5px;width: 20%"></el-input>
    </el-col>
    <el-col>
      <span style="float: left;margin: 5px">手机号码：</span>
      <el-input v-model="phone" placeholder="11位手机号" style="float: left;margin: 5px;width: 20%"></el-input>
    </el-col>
    <el-col >
      <span style="float: left;margin: 5px">地址信息：</span>
      <el-cascader
          v-model="values"
          style="float: left;margin: 5px"
          placeholder="试试搜索：南昌"
          size="medium"
          :props="props" @focus="getlist"
          :options="districts" @change="handleChange"
          filterable clearable></el-cascader>
    </el-col>
    <el-col>
      <span
          style="float: left;margin: 5px">详细地址：</span>
      <el-autocomplete
          v-model="checkedItem" style="float: left;margin: 5px"
          :fetch-suggestions="querySearch"
          placeholder="请输入详细地址"
          :trigger-on-focus="false"
          @select="handleSelect"
      ></el-autocomplete>
    </el-col>
    <el-col>
      <span style="float: left;margin: 5px">邮政编码：</span>
      <el-input v-model="code" placeholder="6位邮编" style="float: left;margin: 5px;width: 20%"></el-input>
    </el-col>
<!--    <el-col>
      <el-switch v-model="dafault" style="float: left" active-text="默认地址"></el-switch>
    </el-col>-->
    <el-col>
      <el-button type="primary" plain @click="add" style="float: left;margin: 10px">保存</el-button>
    </el-col>
  </el-row>

</div>
</template>

<script>
import axios from "axios";
import {baseURL} from "../../public/urlConfig";

export default {
  name: "Address",
  props:['userAddr','dialogFormVisible'],
  data(){
    return{
      values:[],
      districts:[],
      props:{
        expandTrigger: 'hover',
        value:'name',
        label:'name',
        children:'districts'
      },
      province: "",
      city: "",
      area: "",
      name:"",
      phone:"",
      code:"",
      checkedItem:"",
      list:[],
      addr:"",
      userId:localStorage.getItem("userId"),
      dafault:true,
    }
  },

  methods:{
    load(){
      axios.get(baseURL+"/user-addr/addr/"+this.userId).then(
          res=>{this.userAddr=res.data})
    },
    querySearch(queryString, cb) {
      axios.get("https://restapi.amap.com/v3/place/text",{
        params:{
          key: '1983be331c48ef28867f61e61f878987',   // 这个是申请的 Key，自行写入
          keywords:queryString,
          city:this.city?this.city:null
        }
      }).then((res) => {
            // 获得返回的数据
            let lists = res.data.pois;
            // 组装成autoComplete组件所需要的数据结构
            let results = [];
            lists.map((item) => {
              results.push({
                value: item.address,
                name: item.name,
                "province":item.pname,
                "city":item.cityname,
                "area":item.adname,
              });
            });
            // 调用 callback 返回建议列表的数据
            cb(results);
          });
    },
    handleSelect(item){
      this.province=item.province
      this.city=item.city
      this.area=item.area
      this.addr=item.value
    },
    getlist(){
      axios.get('https://restapi.amap.com/v3/config/district',{   // 高德地图的API
        params:{
          key: '1983be331c48ef28867f61e61f878987',   // 这个是申请的 Key，自行写入
          keywords: '中国',
          subdistrict: 3,
          extensions: 'base'
        }
      }).then((response) => {
        let a=response.data.districts[0].districts
        this.districts=this.recur(a)
      })
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
    handleChange(value){
      this.province=value[0]
      this.city=value[1]
      this.area=value[2]
    },
    add(){
      var addr={
        "userId":localStorage.getItem("userId"),
        "receiverName":this.name,
        "receiverMobile":this.phone,
        "province":this.province,
        "city":this.city,
        "area":this.area,
        "addr":this.addr,
        "postCode":this.code,
        "status":1,
        "commonAddr":0,
      }
      axios.post(baseURL+"/user-addr/addAddr",addr).then(
          res=>{
            if(res.data===true)this.$message.success("添加成功")
            else this.$message.error("添加失败")
            this.load()
            this.dialogFormVisible=false
            this.$emit("itemclick",this.userAddr)
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

<style scoped>

</style>