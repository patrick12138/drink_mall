<template>
  <!-- 商品分类导航 -->
  <div>

<!--  <div>
    <el-row style="width: 80%;margin: 0 auto">
      <el-col :span="4" style="background-color: rgb(250,250,250)">
        <div v-for="c1 in cata" style="display: block;">
          <span class="span">
            <i><img :src="require('../img/'+c1.categoryIcon)" style="vertical-align:middle;width: 30px; border-radius: 50%; position: relative; "></i>
              <a>{{c1.categoryName}}</a>
          </span>
          <dl v-for="c2 in c1.categories" style="display: none;top: 0px">
            <dt><a>{{c2.categoryName}}</a></dt>
            <dd>
              <em v-for="c3 in c2.categories">
                <a :href="'/search/?cid='+c3.categoryId">{{c3.categoryName}}</a>
              </em>
            </dd>
          </dl>
        </div>
      </el-col>
      <el-col :span="20">

      </el-col>
    </el-row>
  </div>-->

  <div class="type-nav">
    <div class="container">
      <div class="sort">
        <div class="all-sort-list2">
          <div class="item" v-for="c1 in cata">
            <h3>
              <i><img :src="'http://localhost:9090/img/'+c1.categoryIcon"
                      style="vertical-align:middle;width: 30px; border-radius: 50%; position: relative; "></i>
              <a>{{c1.categoryName}}</a>
            </h3>
            <div class="item-list clearfix">
              <div class="subitem" v-for="c2 in c1.categories">
                <dl class="fore" >
                  <dt><a>{{c2.categoryName}}</a></dt>
                  <dd>
                    <em v-for="c3 in c2.categories">
                      <a :href="'/search/?cid='+c3.categoryId">{{c3.categoryName}}</a>
                    </em>
                  </dd>
                </dl>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import axios from "axios";
import {baseURL} from "../../public/urlConfig";

export default {
  name: "Category",
  data(){
    return{
      cata:[],
/*      selectedOptions:[],
      defaults:{
        label:'categoryName',
        value:'categoryId',
        children:'categories',
        expandTrigger: 'hover'
      }*/
    }
  },
  mounted() {
    this.load()
  },
  methods:{
    load(){
      axios.get(baseURL+"/category/categories").then(
          res=>{
            this.cata=res.data
          }
      )
    }
  }
}
</script>

<style  lang="less" scoped>
.dsad:hover{
  display: block;
}
.type-nav {
  border-bottom: 2px solid  ;width: 80%;margin: 0 auto;

  .container {
    margin: 0 auto;
    display: flex;
    position: relative;

    .sort {
      position: absolute;
      left: 0;
      top: 0px;
      width: 130px;
      height: 300px;
      background: #fafafa;
      z-index: 999;

      .all-sort-list2 {
        .item:hover{
          background-color: skyblue;
        }
        .item {
          h3 {
            line-height: 20px;
            font-size: 14px;
            font-weight: 400;
            overflow: hidden;
            margin: 0;

            a {
              color: #333;
            }
          }

          .item-list {
            display: none;
            position: absolute;
            min-height: 300px;
            _height: 200px;
            background: #f7f7f7;
            left: 130px;
            border: 1px solid #ddd;
            top: 0;
            z-index: 9999 !important;

            .subitem{
              float: left;
              width:450px;
              padding: 0 4px 0 8px;

              dl {
                border-top: 1px solid #eee;
                padding: 6px 0;
                overflow: hidden;
                zoom: 1;

                &.fore {
                  border-top: 0;
                }

                dt {
                  float: left;
                  width: 54px;
                  line-height: 22px;
                  text-align: right;
                  padding: 3px 6px 0 0;
                  font-weight: 700;
                }

                dd {
                  float: left;
                  width: 415px;
                  padding: 3px 0 0;
                  overflow: hidden;

                  em {
                    float: left;
                    height: 14px;
                    line-height: 14px;
                    padding: 0 8px;
                    margin-top: 5px;
                    border-left: 1px solid #ccc;
                    a{
                      color: #333333;
                    }
                  }
                }
              }
            }
          }

          &:hover {
            .item-list {
              display: block;
            }
          }
        }
      }
    }
  }
}
</style>