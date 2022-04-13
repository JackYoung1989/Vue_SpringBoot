<template>
<div>
  <el-menu
      :default-active="path"
      router
      class="el-menu-vertical-demo"
      style="width: 200px; height: calc(100vh - 50px);">
    <el-sub-menu index="1">
      <template #title>
        <el-icon><location /></el-icon>
        <span>系统管理</span>
      </template>
      <el-menu-item index="/user">用户管理</el-menu-item>
    </el-sub-menu>
    <el-menu-item index="/book">数据管理</el-menu-item>
  </el-menu>
</div>
</template>

<script>

export default {
  name: "Aside",
  data() {
    return {
      path: this.$route.path // 设置默认高亮菜单
    }
  },
  created() {
    console.log(this.$route.path)
  }
}
</script>

<style scoped>

</style>