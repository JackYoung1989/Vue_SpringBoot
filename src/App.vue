<template>
  <div>
    <MyHeader />
  </div>
  <router-view/>
</template>

<style>

</style>

<script>

import MyHeader from "./components/MyHeader";

export default {
  components: {
    MyHeader
  },
  name: "Layout"
}
</script>
