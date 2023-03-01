<template>
<div>
  <Navi></Navi>
  <Recommend></Recommend>
  <CategoryRecommend></CategoryRecommend>
</div>
</template>
<script>
import Category from "@/components/Category";
import Banner from "@/components/Banner";
import Recommend from "@/components/Recommend";
import CategoryRecommend from "@/components/CategoryRecommend";
import Navi from "../components/Navi";

export default {
  name:"Index",
  components:{
    Navi,
    Category,
    Banner,
    Recommend,
    CategoryRecommend
  }
}
</script>

<style scoped>

</style>
