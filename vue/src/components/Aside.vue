<template>
    <el-menu :default-openeds="['1', '3']" style=" overflow-x: hidden"
             active-text-color="#ffd04b"
             :collapse-transition="false"
             :collapse="isCollapse"
    >
      <div style="height: 60px; line-height: 60px; text-align: center">
        <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; ">
        <b v-show="logoTextShow">零售系统</b>
      </div>
      <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px" @click="collapses"></span>
      <el-submenu index="1">
        <template slot="title">
          <i class="el-icon-message"></i>
          <span slot="title">导航一</span>
        </template>
        <el-menu-item-group title="分组2">
          <el-menu-item index="1-3">选项3</el-menu-item>
        </el-menu-item-group>
        <el-submenu index="1-4">
          <template slot="title">选项4</template>
          <el-menu-item index="1-4-1">选项4-1</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title">
          <i class="el-icon-menu"></i>
          <span slot="title">导航二</span>
        </template>
        <el-submenu index="2-4">
          <template slot="title">选项4</template>
          <el-menu-item index="2-4-1">选项4-1</el-menu-item>
        </el-submenu>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">
          <i class="el-icon-setting"></i>
          <span slot="title">导航三</span>
        </template>
        <el-submenu index="3-4">
          <template slot="title">选项4</template>
          <el-menu-item index="3-4-1">选项4-1</el-menu-item>
        </el-submenu>
      </el-submenu>
    </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {

  },
  data(){
    return{
      collapseBtnClass: 'el-icon-s-fold',
      isCollapse: true,
      logoTextShow: false
    }
  },
  methods:{
    collapses() {  // 点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) {  // 收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {   // 展开
        this.sideWidth = 120
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
  }
}}
</script>

<style scoped>

</style>