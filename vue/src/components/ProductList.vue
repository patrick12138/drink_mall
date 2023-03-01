<template>
  <div>
    <el-table

        element-loading-text="拼命加载中"
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
        ref="multipleTable"
        :data="productlist.filter(data => !search || data.productName.toLowerCase().includes(search.toLowerCase()))"
        tooltip-effect="dark"
        style="width: 90%;margin:0 auto"
        @selection-change="handleSelectionChange"
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
          prop="soldNum"
          label="销量"
          width="120">
      </el-table-column>
      <el-table-column
          prop="originalPrice"
          label="原价">
        <template slot-scope="scope">
          ¥{{ scope.row.originalPrice }}
        </template>
      </el-table-column>
      <el-table-column
          label="现价"
          width="60">
        <template slot-scope="scope">
          ¥{{ scope.row.sellPrice }}
        </template>
      </el-table-column>
      <el-table-column
          prop="content"
          label="商品描述">
      </el-table-column>
      <el-table-column
          prop="stock"
          label="库存">
      </el-table-column>
      <el-table-column
          prop="productStatus"
          label="上架状态">
      </el-table-column>
      <el-table-column label="操作" align="right">
        <template slot-scope="scope">
          <el-button
              size="mini" @click="editOne(scope.row.productId)"
              type="text" icon="el-icon-edit">修改
          </el-button>
          <el-button
              size="mini" @click="deleteOne(scope.row.productId)"
              type="text" icon="el-icon-delete">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-row>
      <el-col :span="6">
        <el-popconfirm
            confirm-button-text='好的'
            cancel-button-text='不用了'
            icon="el-icon-info"
            icon-color="red" @confirm="batchDelete"
            title="这是一段商品确定删除吗？"
        >
          <el-button slot="reference" icon="el-icon-delete" type="danger">批量删除</el-button>
        </el-popconfirm>
      </el-col>
    </el-row>

    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[2, 5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>

    <!-- 添加或修改商品信息管理对话框 -->
    <el-dialog title="修改商品信息" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form"
               :rules="rules" label-width="80px">
        <el-form-item label="商品名称" prop="productName">
          <el-input v-model="form.productName" placeholder="请输入商品名称"/>
        </el-form-item>
        <el-form-item label="上架状态" prop="productStatus">
          <el-input v-model="form.productStatus" placeholder="请输入销量"/>
        </el-form-item>
        <el-form-item label="商品描述" prop="content">
          <el-input v-model="form.content" placeholder="请输入销量"/>
        </el-form-item>
        <el-form-item label="销量" prop="stock">
          <el-input v-model="form.soldNum" placeholder="请输入库存"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import axios from "axios";
import {baseURL} from "../../public/urlConfig";

let token;
export default {
  name: "ProductList",
  data() {
    return {
      categories: [],
      categoryName: "",
      values: [],
      categoryId: "",
      rootCategoryId: "",
      userId: "",
      pageNum: 1,
      pageSize: 10,
      total: 0,
      search: '',
      productlist: [],
      multipleSelect: [],
      dialogImageUrl: '',
      dialogVisible: false,
      action: baseURL,
      limit: 5,
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {
        productId: "",
        productName: "",
        rootCategoryId: 0,
        catalogId: 0,
        soldNum: 0,
        productStatus: 0,
        content: ""
      },
      props: {
        expandTrigger: 'hover',
        value: 'categoryId',
        label: 'categoryName',
        children: 'categories'
      },
      //表单检验规则
      rules: {}
    }
  },
  created() {
    this.userId = this.$route.query.uid;
    token = localStorage.getItem("token")
    this.load();
  },
  methods: {
    load() {
      axios.get(baseURL + "/products/list/",
          {params: {"pageNum": this.pageNum, "pageSize": this.pageSize}})
          .then(res => {
                if (res.data.code === 10001)
                  this.$message.error(res.data.msg)
                else {
                  this.productlist = res.data.records
                  this.total = res.data.total
                }
              }
          )
    },
    getCategoryName(id) {
      axios.get(baseURL + "/category/" + id).then(res => {
        console.log(res.data)
        this.categoryName = res.data
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.resetForm("form");
    },
    resetForm(formName) {
      if (this.$refs[formName]) {
        this.$refs[formName].resetFields();
      }
    },
    getList() {
      axios.get(baseURL + "/category/categories").then(resp => {
        let a = resp.data
        this.categories = this.recur(a)
      })
    },
    recur(data) {
      for (let i = 0; i < data.length; i++) {
        for (let j = 0; j < data[i].categories.length; j++) {
          for (let k = 0; k < data[i].categories[j].categories.length; k++) {
            data[i].categories[j].categories[k].categories = undefined
          }
        }
      }
      return data
    },
    handleChange(value) {
      this.categoryId = value[2]
      this.rootCategoryId = value[0]
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
    submitForm(formName) {
      this.updateProduct()
    },
    editOne(id) {
      this.reset();
      axios.get(baseURL + "/products/productList/" + id).then(
          res => {
            // console.log(res.data)
            this.form = res.data
            this.open = true;
          }
      )
    },
    updateProduct() {
      var product = {
        "productId": this.form.productId,
        "productName": this.form.productName,
        "rootCategoryId": this.form.rootCategoryId,
        "catalogId": this.form.catalogId,
        "soldNum": this.form.soldNum,
        "content": this.form.content,
        "productStatus": this.form.productStatus
      }
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.productId != null) {
            axios.put(baseURL + "/products/update", product).then(response => {
              this.$message.success("更新成功")
              this.open = false;
              this.load();
            });
          }
        }
      });
    },
    deleteOne(id) {
      this.$confirm('是否删除此商品？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        axios.delete(baseURL + "/products/delete", {params: {"productId": id}}).then(
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
      let ids = this.multipleSelect.map(v => v.productId)
      axios.post(baseURL + "/products/ids", ids, {headers: {token: token}}).then(
          res => {
            if (res.data.code === 10000) {
              this.$message.success("删除成功"), this.load()
            } else this.$message.error(res.data.msg)
          }
      )
    },
    handleSuccess(response, file, fileList) {
      this.productImg.push(response)
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
  }
}
</script>

<style scoped>

</style>
