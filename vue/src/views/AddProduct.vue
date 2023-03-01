<template>
  <div>
    <Navi/>
    <el-tabs v-model="activeName" tab-position="left" style="width: 80%;margin: 0 auto">
      <el-tab-pane label="商品列表" name="first">
        <ProductList></ProductList>
      </el-tab-pane>
      <el-tab-pane label="增加商品" name="second">
        <div style="width: 60%;margin: 0 auto">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
            <el-form-item label="商品名称" prop="productName">
              <el-input v-model="ruleForm.productName"></el-input>
            </el-form-item>
            <el-form-item label="商品描述" prop="content">
              <el-input type="textarea" v-model="ruleForm.content"></el-input>
            </el-form-item>
          </el-form>
          <el-row>
            <el-col style="width: 30%;float: left">
              <span>商品分类：</span>
            </el-col>
            <el-col style="width: 30%;float: left">
              <el-cascader
                  v-model="values"
                  style="float: left;margin: 5px"
                  placeholder="试试搜索：男装"
                  size="medium"
                  :props="props" @focus="getlist"
                  :options="categories" @change="handleChange"
                  filterable clearable></el-cascader>
            </el-col>
          </el-row>
          <el-row>
            <el-button type="primary" @click="addProduct">添加商品</el-button>
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
            <el-form :model="params" :rules="paramsRules" ref="params" label-width="100px">
              <el-form-item label="商品生产地" prop="productPlace">
                <el-input v-model="params.productPlace"></el-input>
              </el-form-item>
              <!--      <el-form-item label="商品有效期" prop="footPeriod">-->
              <!--        <el-input v-model="params.footPeriod"></el-input>-->
              <!--      </el-form-item>-->
              <el-form-item label="商品品牌" prop="brand">
                <el-input v-model="params.brand"></el-input>
              </el-form-item>
              <!--      <el-form-item label="生产厂名" prop="factoryName">-->
              <!--        <el-input v-model="params.factoryName"></el-input>-->
              <!--      </el-form-item>-->
              <!--      <el-form-item label="产地详细地址" prop="factoryAddress">-->
              <!--        <el-input v-model="params.factoryAddress"></el-input>-->
              <!--      </el-form-item>-->
              <el-form-item label="包装方式" prop="packagingMethod">
                <el-input v-model="params.packagingMethod"></el-input>
              </el-form-item>
              <el-form-item label="规格重量" prop="weight">
                <el-input v-model="params.weight"></el-input>
              </el-form-item>
              <el-form-item label="储存方式" prop="storageMethod">
                <el-input v-model="params.storageMethod"></el-input>
              </el-form-item>
              <el-form-item label="食用方式" prop="eatMethod">
                <el-input v-model="params.eatMethod"></el-input>
              </el-form-item>
            </el-form>
          </el-row>
          <el-row>
            <el-button type="primary" @click="addParams">保存商品参数</el-button>
          </el-row>
          <el-row>
            <el-form :model="dynamicValidateForm" ref="dynamicValidateForm" label-width="100px" class="demo-dynamic">
<!--              <el-form-item-->
<!--                  prop="skuName"-->
<!--                  label="套餐名"-->
<!--              ><el-input v-model="dynamicValidateForm.skuName"></el-input>-->
<!--              </el-form-item>-->
              <!--        <el-form-item-->
              <!--            prop="sku"-->
              <!--            label="属性名"-->
              <!--        >-->
              <!--          <el-input v-model="dynamicValidateForm.sku"></el-input>-->
              <!--      </el-form-item>-->
              <el-form-item
                  prop="originalPrice"
                  label="原价"
              >
                <el-input v-model="dynamicValidateForm.originalPrice"></el-input>
              </el-form-item>
              <el-form-item
                  prop="sellPrice"
                  label="现价"
              >
                <el-input v-model="dynamicValidateForm.sellPrice"></el-input>
              </el-form-item>
              <el-form-item
                  prop="stock"
                  label="库存"
              >
                <el-input v-model="dynamicValidateForm.stock"></el-input>
              </el-form-item>
<!--              <el-form-item-->
<!--                  prop="sku"-->
<!--                  label="属性集合"-->
<!--              >-->
<!--                <el-input v-model="dynamicValidateForm.sku" :placeholder="str"></el-input>-->
<!--              </el-form-item>-->
              <el-form-item>
                <el-button type="primary" @click="submitForm('dynamicValidateForm')">提交</el-button>
                <el-button @click="resetForm('dynamicValidateForm')">重置</el-button>
              </el-form-item>
            </el-form>
          </el-row>
        </div>
      </el-tab-pane>
      <el-tab-pane label="客户信息" name="third">
        <User></User>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import axios from "axios";
import {baseURL} from "../../public/urlConfig";
import Navi from "../components/Navi";
import ProductList from "@/components/ProductList";
import User from "@/components/User";

export default {
  name: "AddProduct",
  components: {
    Navi,
    ProductList,
    User
  },
  data() {
    return {
      values: [],
      categories: [],
      categoryId: "",
      rootCategoryId: "",
      productImg: [],
      limit: 5,
      str: "格式示例:{口味:[\"原味\",\"烧烤\"],包装:[\"新包装\",\"老包装\"]}（全为英文符号）",
      dialogImageUrl: '',
      dialogVisible: false,
      params: {
        "productPlace": "",
        "footPeriod": "",
        "brand": "",
        "factoryName": "",
        "factoryAddress": "",
        "packagingMethod": "",
        "weight": "",
        "storageMethod": "",
        "eatMethod": "",
      },
      paramsRules: {},
      props: {
        expandTrigger: 'hover',
        value: 'categoryId',
        label: 'categoryName',
        children: 'categories'
      },
      ruleForm: {
        "productName": "",
        "content": "",
      },
      rules: {
        productName: [
          {required: true, message: '请输入商品名称', trigger: 'blur'},
          {min: 1, max: 16, message: '长度在 1 到 16 个字符', trigger: 'blur'}
        ],
        content: [
          {required: true, message: '请输入商品描述', trigger: 'blur'},
          {min: 1, message: '不能为空', trigger: 'blur'}
        ]
      },
      action: "",
      dynamicValidateForm: {
        sku: "",
        skuName: '',
        originalPrice: 0,
        sellPrice: 0,
        stock: 0,
      },
      activeName: 'first'
    }
  },
  created() {
    this.action = baseURL
    localStorage.removeItem("productId")
    if (localStorage.getItem("userId") === null) {
      this.$message.error("登录后才可添加商品")
      this.$router.push('/login')
    }
  },
  methods: {
    getlist() {
      axios.get(baseURL + "/category/categories").then((response) => {
        let a = response.data
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
    handleSuccess(response, file, fileList) {
      this.productImg.push(response)
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    addProduct() {
      var product = {
        "productName": this.ruleForm.productName,
        "content": this.ruleForm.content,
        "categoryId": this.categoryId,
        "rootCategoryId": this.rootCategoryId,
        "soldNum": 0,
        "productStatus": 1,
      }
      axios.post(baseURL + "/products/addProduct", product).then(
          res => {
            localStorage.setItem("productId", res.data.toString())
            this.$message.success("添加成功")
          }
      )
    },
    addParams() {
      var params = {
        "productId": localStorage.getItem("productId"),
        "productPlace": this.params.productPlace,
        "footPeriod": this.params.footPeriod,
        "brand": this.params.brand,
        "factoryName": this.params.factoryName,
        "factoryAddress": this.params.factoryAddress,
        "packagingMethod": this.params.packagingMethod,
        "weight": this.params.weight,
        "storageMethod": this.params.storageMethod,
        "eatMethod": this.params.eatMethod,
      }
      axios.post(baseURL + "/product-params/addParams", params).then(
          res => {
            if (res.data === true) this.$message.success("保存成功")
            else this.$message.error("保存失败")
          }
      )
      for (let i = 0; i < this.productImg.length; i++) {
        if (i === 0) {
          var productImg = {
            "itemId": localStorage.getItem("productId"),
            "url": this.productImg[i],
            "sort": 1,
            "isMain": 1
          }
          axios.post(baseURL + "/product-img/addProductImg", productImg)
        } else {
          var productimg = {
            "itemId": localStorage.getItem("productId"),
            "url": this.productImg[i],
            "sort": i + 1,
            "isMain": 0
          }
          axios.post(baseURL + "/product-img/addProductImg", productimg)
        }
      }

    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.addSku()
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    addSku() {
      var sku = {
        "productId": localStorage.getItem("productId"),
        "skuName": this.dynamicValidateForm.skuName,
        "originalPrice": this.dynamicValidateForm.originalPrice,
        "sellPrice": this.dynamicValidateForm.sellPrice,
        "stock": this.dynamicValidateForm.stock,
        "untitled": this.dynamicValidateForm.sku,
        "discounts": 1.0
      }
      axios.post(baseURL + "/product-sku/addSku", sku).then(
          res => {
            if (res.data === true) this.$message.success("添加成功")
            else this.$message.error("添加失败")
          }
      )
    }
  }
}
</script>

<style scoped>

</style>
