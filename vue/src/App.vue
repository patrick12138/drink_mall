<template>
  <div id="app" >
    <router-view></router-view>
    <Footer></Footer>
    <el-backtop target="#app">
      <i class="el-icon-caret-top"></i>
    </el-backtop>
  </div>
</template>
<script>

import Footer from "./components/Footer";

export default {
  components:{
    Footer
  }
}
</script>
<style>

#app{
  overflow-y: scroll;
  height: 100vh;
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  background-color: #e8e2e0;
  background-image: linear-gradient(to bottom , #e8e2e0, #E2E9EC);
}
#nav a {
  font-weight: bold;
  color: #2c3e50;
}

</style>
