<template>
<div>
  <MyHeader />

  <div style="display: flex">
    <Aside />
    <router-view />
  </div>
</div>
</template>

<script>
import MyHeader from "../components/MyHeader";
import Aside from "../components/Aside";

export default {
  components: {
    MyHeader,
    Aside
  },
  name: "Layout"
}

</script>

<style scoped>

</style>