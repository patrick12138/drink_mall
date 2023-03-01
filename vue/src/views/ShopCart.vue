<template xmlns="http://www.w3.org/1999/html">
  <div>
    <Navi/>
    <el-table
        element-loading-text="拼命加载中"
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
        ref="multipleTable"
        :data="cartlist.filter(data => !search || data.productName.toLowerCase().includes(search.toLowerCase()))"
        tooltip-effect="dark"
        style="width: 90%;margin:0 auto"
        max-height="420" show-summary :summary-method="summary"
        border @selection-change="handleSelectionChange"
        stripe>
      <el-table-column
          type="selection"
          width="60">
      </el-table-column>
      <el-table-column
          label="图片"
          width="120">
        <template slot-scope="scope">
          <router-link :to="{name:'Product',query:{pid:scope.row.productId}}">
            <el-image :src="'http://localhost:9090/img/'+scope.row.url" fit="fill"></el-image>
          </router-link>
        </template>
      </el-table-column>
      <el-table-column
          label="商品名"
          width="120">
        <template slot-scope="scope">
          <el-link :href="'/product/?pid='+scope.row.productId"
                   :underline="false">{{ scope.row.productName }}
          </el-link>
        </template>
      </el-table-column>
      <el-table-column
          prop="productPrice"
          label="单价"
         >
        <template slot-scope="scope">
          <em style="font-size: 10px;text-decoration: line-through">¥{{ scope.row.originalPrice }}</em>
          <br><em style="font-size:16px;font-weight: 700; ">¥{{ scope.row.sellPrice }}</em>
        </template>
      </el-table-column>
      <el-table-column
          label="数量" >
        <template slot-scope="scope">
          <el-input size="mini" :min="1" :max="scope.row.stock" v-model="scope.row.cartNum"
                    @change="current=>numChange(current,scope.row.cartId)">
          </el-input>
        </template>
      </el-table-column>
      <el-table-column label="金额" >
        <template slot-scope="scope">
          <em style="font-size:16px;font-weight: 700; color: #c81623">¥{{
              scope.row.sellPrice * scope.row.cartNum
            }}</em>
        </template>
      </el-table-column>
      <el-table-column
          :formatter='dateFormat'
          label="添加时间"
          prop="cartTime" sortable></el-table-column>
      <el-table-column
          align="right" fixed="right" width="100">
        <template slot="header" slot-scope="scope">
          <el-input
              v-model="search"
              size="mini"
              placeholder="搜索商品名"/>
        </template>
        <template slot-scope="scope">
          <el-button
              size="mini" @click="deleteOne(scope.row.cartId)"
              type="text" icon="el-icon-delete">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-row type="flex"  justify="space-between">
      <el-col :span="6">
        <el-popconfirm
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red" @confirm="batchDelete"
            title="这是一段内容确定删除吗？"
        >
          <el-button slot="reference" icon="el-icon-delete" type="danger">批量删除</el-button>
        </el-popconfirm>
      </el-col>
      <el-col :span="6"></el-col>
<!--      <el-col :span="6">-->
<!--        <el-button style="background-color: #ff5000;color: white" @click="submit">结算</el-button>-->
<!--      </el-col>-->
    </el-row>
  </div>
</template>

<script>
import {baseURL} from "../../public/urlConfig";
import axios from "axios";
import Navi from "../components/Navi";

let token

export default {
  name: "ShopCart",
  components: {Navi},
  data() {
    return {
      userId: "",
      cartlist: [],
      search: '',
      multipleSelect: [],
    }
  },
  created() {
    this.userId = this.$route.query.uid;
    token = localStorage.getItem("token")
    this.load();
  },
  methods: {
    load() {
      axios.get(baseURL + "/shopping-cart/list/" + this.userId, {
        headers: {
          token: token
        }
      }).then(
          res => {
            if (res.data.code === 10001)
              this.$message.error(res.data.msg)
            else {
              this.cartlist = res.data
            }
          }
      )
    },
    handleSelectionChange(val) {
      this.multipleSelect = val
    },
    numChange(current, index) {
      axios.get(baseURL + "/shopping-cart/update/" + index + "/" + current, {
        headers: {token: token}
      }).then(res => {
            if (res.data.code === 10001)
              this.$message.error(res.data.msg)
          }
      )
    },
    summary(p) {
      const sums = []
      let temp = 0
      for (let i = 0; i < this.multipleSelect.length; i++) {
        temp = temp + this.multipleSelect[i].sellPrice * Number(this.multipleSelect[i].cartNum)
      }
      sums[7] = temp
      sums[1] = this.multipleSelect.length
      sums[1] += '件商品'
      sums[7] += '元'
      sums[0] = '总计'
      return sums
    },
    deleteOne(id) {
      this.$confirm('是否删除此购物车中的商品？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.delete(baseURL + "/shopping-cart/" + id, {
          headers: {token: token}
        }).then(
            res => {
              if (res.data === true) {
                this.$message.success("删除成功"), this.load()
              } else this.$message.error("删除失败")
            }
        )
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });
    },
    batchDelete() {
      let ids = this.multipleSelect.map(v => v.cartId)
      axios.post(baseURL + "/shopping-cart/ids", ids, {headers: {token: token}}).then(
          res => {
            if (res.data.code === 10000) {
              this.$message.success("删除成功"), this.load()
            } else this.$message.error(res.data.msg)
          }
      )
    },
    submit() {
      let cids = this.multipleSelect.map(v => v.cartId)
      if (cids == "") this.$message.error("至少选择一件商品")
      else this.$router.push("/order?cids=" + cids)
    },
    dateFormat(row, col) {
      let data = row[col.property]
      if (data == null) {
        return ""
      }
      let currDate = new Date(data)
      return currDate.getFullYear() + '-' + (currDate.getMonth() + 1) + '-' + currDate.getDate() + ' ' + currDate.getHours() + ':' + currDate.getMinutes() + ':' + currDate.getSeconds()
    }
  }
}
</script>

<style scoped>
</style>
