<template>
  <div>
    <el-table
        element-loading-text="拼命加载中"
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
        ref="multipleTable"
        :data="userList.filter(data => !search || data.username.toLowerCase().includes(search.toLowerCase()))"
        tooltip-effect="dark"
        style="width: 90%;margin:0 auto"
        @selection-change="handleSelectionChange"
        stripe>
      <el-table-column
          label="图片"
          width="120">
        <template slot-scope="scope">
            <el-image :src="'http://localhost:9090/img/'+scope.row.userImg" fit="fill"></el-image>
        </template>
      </el-table-column>
      <el-table-column
          label="用户名"
          width="120">
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>
      <el-table-column prop="nickName" label="昵称" width="100">
      </el-table-column>
      <el-table-column
          prop="userMobile"
          label="用户手机号"
          width="120">
      </el-table-column>
      <el-table-column
          prop="userEmail"
          label="用户邮箱">
      </el-table-column>
      <el-table-column
          prop="userSex"
          label="性别"
          width="60">
      </el-table-column>
      <el-table-column
          :formatter='dateFormat'
          property="userBirth"
          label="生日" width="160">
      </el-table-column>
      <el-table-column
          label="操作"
          align="right" fixed="right" width="100">
        <template slot-scope="scope" >
          <el-button
              v-if="scope.row.username != 'admin' ? true : false"
              size="mini" @click="deleteOne(scope.row.userId)"
              type="text" icon="el-icon-delete">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
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
</template>

<script>
import axios from "axios";
import {baseURL} from "../../public/urlConfig";

let token;
export default {
  name: "User",
  data() {
    return {
      userId: "",
      userList: [],
      pageNum: 1,
      pageSize: 5,
      total: 0,
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
      axios.get(baseURL + "/user/list/",
          {params: {"pageNum": this.pageNum, "pageSize": this.pageSize}})
          .then(res => {
                if (res.data.code === 10001)
                  this.$message.error(res.data.msg)
                else {
                  this.userList = res.data.records
                  this.total = res.data.total
                }
              }
          )
    },
    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.pageNum = pageNum
      this.load()
    },
    handleSelectionChange(val) {
      this.multipleSelect = val
    },
    numChange(current, old, index) {
      axios.get(baseURL + "/shopping-cart/update/" + index + "/" + current, {
        headers: {
          token: token
        }
      }).then(
          res => {
            if (res.data.code === 10001)
              this.$message.error(res.data.msg)
            else {
            }
          }
      )
    },
    deleteOne(id) {
      this.$confirm('是否删除此用户？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.delete(baseURL + "/user/delete", {params: {"userId": id}}).then(
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
    dateFormat(row, col) {
      let data = row[col.property]
      if (data == null) {
        return ""
      }
      let currDate = new Date(data)
      return currDate.getFullYear() + '-' + (currDate.getMonth() + 1) + '-' + currDate.getDate()
    }
  }
}
</script>

<style scoped>

</style>
